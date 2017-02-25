package ro.poo.commands.userCommands;

import java.util.ArrayList;

import ro.poo.errors.Errors;
import ro.poo.files.Directory;
import ro.poo.files.Entity;
import ro.poo.system.FileSystem;
import ro.poo.system.User;

/**
 * Class for deleting an existing user
 * 
 * @author Alexandru Lincan
 *
 */
public class DelUser extends UserCommand {

	/**
	 * Calls the constructor of the parent class
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param username
	 */
	public DelUser(String fullCommand, FileSystem fileSystem, String username) {
		super(fullCommand, fileSystem, username);
	}

	/**
	 * Deletes the user from system and replaces the owner for all files which
	 * have this user as owner
	 */
	@Override
	public void execute() {
		if (!fileSystem.getCurrentUser().isRoot) {
			Errors.throwError(-10, fullCommand);
			return;
		}
		User user = getUser();
		if (user == null) {
			Errors.throwError(-8, fullCommand);
			return;
		}

		User newOwner;
		ArrayList<User> users = fileSystem.getUsers();
		if (users.size() > 1 && users.get(1) != user) {
			newOwner = users.get(1);
		} else {
			newOwner = users.get(0);
		}
		Directory root = fileSystem.getRoot();
		replaceOwner(root, newOwner);

		fileSystem.deleteUser(user);
	}

	private void replaceOwner(Entity root, User newOwner) {
		if (root.getOwner().getName().equals(username)) {
			root.setOwner(newOwner);
		}
		if (root.isDirectory()) {
			ArrayList<Entity> children = ((Directory) root).getChildren();
			for (Entity it : children) {
				replaceOwner(it, newOwner);
			}
		}
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
