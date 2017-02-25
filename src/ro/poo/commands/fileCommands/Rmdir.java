package ro.poo.commands.fileCommands;

import ro.poo.errors.Errors;
import ro.poo.files.Directory;
import ro.poo.files.Entity;
import ro.poo.files.Permissions;
import ro.poo.system.FileSystem;

/**
 * Removes a directory only if it's empty
 * 
 * @author Alexandru Lincan
 *
 */
public class Rmdir extends FileCommand {
	String directoryToDelete;

	/**
	 * Calls the constructor of the parrent class
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param path
	 */
	public Rmdir(String fullCommand, FileSystem fileSystem, String path) {
		super(fullCommand, fileSystem, path);
	}

	/**
	 * Deletes an empty directory
	 */
	@Override
	public void execute() {
		directoryToDelete = parsePath();
		Directory location = fileSystem.goToPath(path, fullCommand);
		if (location == null) {
			return;
		}

		Directory dir = null;
		if (directoryToDelete.equals(".")) {
			dir = location;
		} else if (directoryToDelete.equals("..")) {
			Errors.throwError(-13, fullCommand);
			return;
		} else {
			for (Entity it : location.getChildren()) {
				if (it.getName().equals(directoryToDelete)) {
					if (!it.isDirectory()) {
						Errors.throwError(-3, fullCommand);
						return;
					}
					dir = (Directory) it;
					break;
				}
			}
		}
		if (dir == null) {
			Errors.throwError(-2, fullCommand);
			return;
		}

		Directory parrent = fileSystem.getCurrentDir();
		do {
			if (parrent == dir) {
				Errors.throwError(-13, fullCommand);
				return;
			}
			parrent = parrent.getParrent();
		} while (parrent != fileSystem.getRoot());

		if (dir.getChildren().size() > 0) {
			Errors.throwError(-14, fullCommand);
			return;
		}

		if (!fileSystem.userHasPermission(location, fileSystem.getCurrentUser(),
				Permissions.WRITE)) {
			Errors.throwError(-5, fullCommand);
			return;
		}
		location.DelEntity(dir);
	}
}
