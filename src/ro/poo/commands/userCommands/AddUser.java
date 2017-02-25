package ro.poo.commands.userCommands;

import ro.poo.errors.Errors;
import ro.poo.files.Directory;
import ro.poo.system.FileSystem;
import ro.poo.system.User;

/**
 * Class for adding a new user
 * 
 * @author Alexandru Lincan
 *
 */
public class AddUser extends UserCommand {

	/**
	 * Calls the constructor of the parent class
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param username
	 */
	public AddUser(String fullCommand, FileSystem fileSystem, String username) {
		super(fullCommand, fileSystem, username);
	}

	/**
	 * Adds a new user to the file system
	 */
	@Override
	public void execute() {
		if (!fileSystem.getCurrentUser().isRoot) {
			Errors.throwError(-10, fullCommand);
			return;
		}
		if (userExists()) {
			Errors.throwError(-9, fullCommand);
			return;
		}

		User user = fileSystem.addUser(username);
		Directory root = fileSystem.getRoot();
		Directory home = new Directory(username, user, root);
		root.addEntity(home);
		user.setHome(home);
	}

	private boolean userExists() {
		for (User user : fileSystem.getUsers()) {
			if (user.getName().equals(username)) {
				return true;
			}
		}
		return false;
	}
}
