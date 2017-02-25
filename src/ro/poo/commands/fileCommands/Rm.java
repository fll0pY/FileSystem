package ro.poo.commands.fileCommands;

import ro.poo.errors.Errors;
import ro.poo.files.Directory;
import ro.poo.files.Entity;
import ro.poo.files.Permissions;
import ro.poo.system.FileSystem;

/**
 * Class for removing a file or directory
 * 
 * @author Alexandru Lincan
 *
 */
public class Rm extends FileCommand {
	private boolean recursive;
	String fileToDelete;

	/**
	 * Constructs a remove command and sets the parameters
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param path
	 * @param recursive
	 */
	public Rm(String fullCommand, FileSystem fileSystem, String path,
			boolean recursive) {
		super(fullCommand, fileSystem, path);
		this.recursive = recursive;
	}

	/**
	 * Deletes a file
	 */
	@Override
	public void execute() {
		fileToDelete = parsePath();
		Directory location = fileSystem.goToPath(path, fullCommand);
		if (location == null) {
			return;
		}

		Entity file = null;
		if (fileToDelete.equals(".") || fileToDelete.length() == 0) {
			if (!recursive) {
				Errors.throwError(-1, fullCommand);
				return;
			}
			file = location;
		} else if (fileToDelete.equals("..")) {
			if (!recursive) {
				Errors.throwError(-1, fullCommand);
				return;
			} else {
				Errors.throwError(-13, fullCommand);
				return;
			}
		} else {
			for (Entity it : location.getChildren()) {
				if (it.getName().equals(fileToDelete)) {
					file = it;
					if (!recursive && file.isDirectory()) {
						Errors.throwError(-1, fullCommand);
						return;
					}
					break;
				}
			}
		}
		if (file == null) {
			if (!recursive) {
				Errors.throwError(-11, fullCommand);
				return;
			} else {
				Errors.throwError(-12, fullCommand);
				return;
			}
		}

		if (!fileSystem.userHasPermission(location, fileSystem.getCurrentUser(),
				Permissions.WRITE)) {
			Errors.throwError(-5, fullCommand);
			return;
		}

		if (!recursive) {
			location.DelEntity(file);
			return;
		}

		Directory parrent = fileSystem.getCurrentDir();
		do {
			if (parrent == file) {
				Errors.throwError(-13, fullCommand);
				return;
			}
			parrent = parrent.getParrent();
		} while (parrent != fileSystem.getRoot());

		location.DelEntity(file);
	}
}
