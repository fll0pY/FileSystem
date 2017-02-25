package ro.poo.commands.fileCommands;

import ro.poo.errors.Errors;
import ro.poo.files.*;
import ro.poo.system.FileSystem;

/**
 * Class for creating a new direcotry at a given path
 * 
 * @author Alexandru Lincan
 *
 */
public class Mkdir extends FileCommand {
	private String newDirName;

	/**
	 * Calls the constructor of the parrent class
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param path
	 */
	public Mkdir(String fullCommand, FileSystem fileSystem, String path) {
		super(fullCommand, fileSystem, path);
	}

	/**
	 * Adds a new directory on a specific path
	 */
	@Override
	public void execute() {
		newDirName = parsePath();
		Directory location = fileSystem.goToPath(path, fullCommand);
		if (location == null) {
			return;
		}
		if (newDirName.length() == 0) {
			Errors.throwError(-1, fullCommand);
			return;
		}
		if (!fileSystem.userHasPermission(location, fileSystem.getCurrentUser(),
				Permissions.WRITE)) {
			Errors.throwError(-5, fullCommand);
			return;
		}

		for (Entity it : location.getChildren()) {
			if (it.getName().equals(newDirName)) {
				if (it.isDirectory()) {
					Errors.throwError(-1, fullCommand);
					return;
				} else {
					Errors.throwError(-3, fullCommand);
					return;
				}
			}
		}
		location.addEntity(new Directory(newDirName,
				fileSystem.getCurrentUser(), location));
	}
}
