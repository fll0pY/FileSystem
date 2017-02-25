package ro.poo.commands.userCommands;

import ro.poo.errors.Errors;
import ro.poo.system.FileSystem;
import ro.poo.system.User;

/**
 * Class for changing the current user
 * 
 * @author Alexandru Lincan
 *
 */
public class ChUser extends UserCommand {

	/**
	 * Calls the constructor of the parent class
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param username
	 */
	public ChUser(String fullCommand, FileSystem fileSystem, String username) {
		super(fullCommand, fileSystem, username);
	}

	/**
	 * Change the current user
	 */
	@Override
	public void execute() {
		User user = getUser();
		if (user == null) {
			Errors.throwError(-8, fullCommand);
			return;
		}
		fileSystem.setCurrentUser(user);
		fileSystem.setCurrentDir(user.getHome());
	}

	private User getUser() {
		for (User user : fileSystem.getUsers()) {
			if (user.getName().equals(username)) {
				return user;
			}
		}
		return null;
	}
}
