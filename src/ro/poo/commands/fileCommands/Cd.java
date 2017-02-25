package ro.poo.commands.fileCommands;

import ro.poo.files.Directory;
import ro.poo.system.FileSystem;

/**
 * Class for changing the current directory
 * 
 * @author Alexandru Lincan
 *
 */
public class Cd extends FileCommand {

	/**
	 * Calls the constructor of the parrent class
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param path
	 */
	public Cd(String fullCommand, FileSystem fileSystem, String path) {
		super(fullCommand, fileSystem, path);
	}

	/**
	 * Changes the current directory
	 */
	@Override
	public void execute() {
		Directory newDir = fileSystem.goToPath(path, fullCommand);
		if (newDir != null) {
			fileSystem.setCurrentDir(newDir);
		}
	}
}