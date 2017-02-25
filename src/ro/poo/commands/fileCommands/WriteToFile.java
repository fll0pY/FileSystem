package ro.poo.commands.fileCommands;

import ro.poo.errors.Errors;
import ro.poo.files.*;
import ro.poo.system.FileSystem;

/**
 * Class for writing text in a file
 * 
 * @author Alexandru Lincan
 *
 */
public class WriteToFile extends FileCommand {
	private String content;
	private String fileToWrite;

	/**
	 * Constructs a write to file command and sets the parameters
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param path
	 * @param content
	 */
	public WriteToFile(String fullCommand, FileSystem fileSystem, String path,
			String content) {
		super(fullCommand, fileSystem, path);
		this.content = content;
	}

	/**
	 * Writes text to a file
	 */
	@Override
	public void execute() {
		fileToWrite = parsePath();
		Directory location = fileSystem.goToPath(path, fullCommand);
		if (location == null) {
			return;
		}

		File file = null;
		for (Entity it : location.getChildren()) {
			if (it.getName().equals(fileToWrite)) {
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
				Permissions.WRITE)) {
			Errors.throwError(-5, fullCommand);
			return;
		}
		file.setText(content);
	}
}
