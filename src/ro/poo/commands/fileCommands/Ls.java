package ro.poo.commands.fileCommands;

import ro.poo.errors.Errors;
import ro.poo.files.*;
import ro.poo.system.FileSystem;

public class Ls extends FileCommand {
	String fileToList;

	/**
	 * Calls the constructor of the parrent class
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param path
	 */
	public Ls(String fullCommand, FileSystem fileSystem, String path) {
		super(fullCommand, fileSystem, path);
	}

	/**
	 * Lists the content of a directory or the specific file
	 */
	@Override
	public void execute() {
		fileToList = parsePath();
		Directory location = fileSystem.goToPath(path, fullCommand);
		if (location == null) {
			return;
		}

		Entity file = null;
		if (fileToList.equals(".") || fileToList.length() == 0) {
			file = location;
		} else if (fileToList.equals("..")) {
			file = location.getParrent();
		} else {
			for (Entity it : location.getChildren()) {
				if (it.getName().equals(fileToList)) {
					file = it;
					break;
				}
			}
		}
		if (file == null) {
			Errors.throwError(-12, fullCommand);
			return;
		}

		if (!fileSystem.userHasPermission(file, fileSystem.getCurrentUser(),
				Permissions.EXECUTE)) {
			Errors.throwError(-6, fullCommand);
			return;
		}

		if (!fileSystem.userHasPermission(file, fileSystem.getCurrentUser(),
				Permissions.READ)) {
			Errors.throwError(-4, fullCommand);
			return;
		}

		if (file.isDirectory()) {
			for (Entity it : ((Directory) file).getChildren()) {
				System.out.println(it);
			}
		} else {
			System.out.println(file);
		}
	}
}
