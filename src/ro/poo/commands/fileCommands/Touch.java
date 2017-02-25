package ro.poo.commands.fileCommands;

import ro.poo.errors.Errors;
import ro.poo.files.*;
import ro.poo.system.FileSystem;

/**
 * Class for creating a new file at a given location
 * 
 * @author Alexandru Lincan
 *
 */
public class Touch extends FileCommand {
	String fileToCreate;

	/**
	 * Calls the constructor of the parrent class
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param path
	 */
	public Touch(String fullCommand, FileSystem fileSystem, String path) {
		super(fullCommand, fileSystem, path);
	}

	/**
	 * Adds a new file to the specific path
	 */
	@Override
	public void execute() {
		fileToCreate = parsePath();
		Directory location = fileSystem.goToPath(path, fullCommand);
		if (location == null) {
			return;
		}

		if (!fileSystem.userHasPermission(location, fileSystem.getCurrentUser(),
				Permissions.WRITE)) {
			Errors.throwError(-5, fullCommand);
			return;
		}

		for (Entity it : location.getChildren()) {
			if (it.getName().equals(fileToCreate)) {
				if (it.isDirectory()) {
					Errors.throwError(-1, fullCommand);
					return;
				} else {
					Errors.throwError(-7, fullCommand);
					return;
				}
			}
		}

		location.addEntity(
				new File(fileToCreate, fileSystem.getCurrentUser(), location));
	}
}
