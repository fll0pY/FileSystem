package ro.poo.commands.fileCommands;

import ro.poo.errors.Errors;
import ro.poo.files.Directory;
import ro.poo.files.Entity;
import ro.poo.files.File;
import ro.poo.files.Permissions;
import ro.poo.system.FileSystem;

/**
 * Class for displaying the content from a file
 * 
 * @author Alexandru Lincan
 *
 */
public class Cat extends FileCommand {
	String fileToPrint;

	/**
	 * Calls the constructor of the parrent class
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param path
	 */
	public Cat(String fullCommand, FileSystem fileSystem, String path) {
		super(fullCommand, fileSystem, path);
	}

	/**
	 * Prints the content of a file
	 */
	@Override
	public void execute() {
		fileToPrint = parsePath();
		Directory location = fileSystem.goToPath(path, fullCommand);
		if (location == null) {
			return;
		}

		File file = null;
		for (Entity it : location.getChildren()) {
			if (it.getName().equals(fileToPrint)) {
				if (it.isDirectory()) {
					Errors.throwError(-1, fullCommand);
					return;
				}
				file = (File) it;
				break;
			}
		}
		if (file == null) {
			Errors.throwError(-11, fullCommand);
			return;
		}
		if (!fileSystem.userHasPermission(file, fileSystem.getCurrentUser(),
				Permissions.READ)) {
			Errors.throwError(-4, fullCommand);
			return;
		}
		System.out.println(file.getText());
	}
}
