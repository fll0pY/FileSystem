package ro.poo.system;

import java.util.ArrayList;
import java.util.StringTokenizer;

import ro.poo.commands.Command;
import ro.poo.errors.Errors;
import ro.poo.files.Directory;
import ro.poo.files.Entity;
import ro.poo.files.Permissions;

/**
 * Simple file system with users and permissions
 * 
 * @author Alexandru Lincan
 *
 */
public class FileSystem {
	private Directory root;
	private Directory currentDir;
	private ArrayList<User> users;
	private User currentUser;

	/**
	 * Constructs a File System with / as root directory and root user as owner
	 */
	public FileSystem() {
		users = new ArrayList<User>();
		users.add(new User("root"));
		users.get(0).giveRootAccess();

		root = new Directory("/", users.get(0), null);
		root.setOthersPermissions(5);
		root.setParrent(root);
		users.get(0).setHome(root);
		currentDir = root;
		setCurrentUser(users.get(0));
	}

	/**
	 * Checks if a user has a specific permission for a file / folder
	 * 
	 * @param file
	 *            the file or directory to check for
	 * @param user
	 * @param perm
	 *            the specific permission
	 * @return true if the user has permission, false otherwise
	 */
	public boolean userHasPermission(Entity file, User user, Permissions perm) {
		User owner = file.getOwner();
		int permissions;

		if (user.isRoot) {
			return true;
		}
		if (user == owner) {
			permissions = file.getOwnerPermissions();
		} else {
			permissions = file.getOthersPermissions();
		}

		if ((permissions & (1 << perm.getValue())) != 0) {
			return true;
		}
		return false;
	}

	/**
	 * Goes to a specific path and returns a reference for that directory
	 * 
	 * @param path
	 * @param command
	 *            the command that needs this
	 * @return a reference to that
	 */
	public Directory goToPath(String path, String command) {
		StringTokenizer pathSt = new StringTokenizer(path, "/");
		Directory dir;

		if (path.startsWith("/")) {
			dir = root;
		} else {
			dir = currentDir;
		}
		if (!userHasPermission(dir, currentUser, Permissions.EXECUTE)) {
			Errors.throwError(-6, command);
			return null;
		}
		while (pathSt.hasMoreTokens() && dir != null) {
			String nextDirName = pathSt.nextToken();

			if (nextDirName.equals(".")) {
				continue;
			} else if (nextDirName.equals("..")) {
				dir = currentDir.getParrent();
				continue;
			}

			boolean dirFound = false;
			for (Entity it : dir.getChildren()) {
				dirFound = false;
				if (it.getName().equals(nextDirName)) {
					if (!it.isDirectory()) {
						Errors.throwError(-3, command);
						return null;
					}
					dir = (Directory) it;
					dirFound = true;
					break;
				}
			}
			if (!dirFound) {
				Errors.throwError(-2, command);
				return null;
			}
			if (!userHasPermission(dir, currentUser, Permissions.EXECUTE)) {
				Errors.throwError(-6, command);
				return null;
			}
		}
		if (dir == null) {
			Errors.throwError(-2, command);
		}
		return dir;
	}

	/**
	 * Executes the specific command
	 * 
	 * @param command
	 *            the command to execute
	 */
	public void executeCommand(Command command) {
		command.execute();
	}

	/**
	 * Prints the file system structure
	 */
	public void printFilesTree() {
		printHierarchy(root, 0);
	}

	/**
	 * @return the root directory
	 */
	public Directory getRoot() {
		return root;
	}

	/**
	 * @return the current directory
	 */
	public Directory getCurrentDir() {
		return currentDir;
	}

	/**
	 * @param currentDir
	 *            the current directory to set
	 */
	public void setCurrentDir(Directory currentDir) {
		this.currentDir = currentDir;
	}

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser
	 *            the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * Adds a new user
	 * 
	 * @param username
	 *            the name of the new user
	 * @return a reference to the new added user
	 */
	public User addUser(String username) {
		User user = new User(username);
		users.add(user);
		return user;
	}

	/**
	 * Deletes a user
	 * 
	 * @param user
	 *            the user to delete
	 */
	public void deleteUser(User user) {
		users.remove(user);
	}

	private void printHierarchy(Entity root, int level) {
		String toPrint = new String();
		toPrint = "";
		for (int i = 0; i < level; i++) {
			toPrint += '\t';
		}
		toPrint += root.toString();
		System.out.println(toPrint);
		if (root.isDirectory()) {
			ArrayList<Entity> children = ((Directory) root).getChildren();
			for (Entity it : children) {
				printHierarchy(it, level + 1);
			}
		}
	}

}