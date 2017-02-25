package ro.poo.commands.fileCommands;

import ro.poo.commands.Command;
import ro.poo.system.FileSystem;

/**
 * Abstract class for commands which work with files or directories
 * 
 * @author Alexandru Lincan
 *
 */
public abstract class FileCommand implements Command {
	protected String fullCommand;
	protected FileSystem fileSystem;
	protected String path;

	/**
	 * Constructs a File Command
	 * 
	 * @param fullCommand
	 *            the full command from input
	 * 
	 * @param fileSystem
	 *            the file system
	 * @param path
	 *            the path to the file or directory
	 */
	public FileCommand(String fullCommand, FileSystem fileSystem, String path) {
		this.fullCommand = fullCommand;
		this.fileSystem = fileSystem;
		this.path = path;
	}

	protected String parsePath() {
		String file;
		int index = path.lastIndexOf('/');
		if (index == -1) {
			file = new String(path);
			path = "";
			return file;
		}
		if (index == path.length() - 1) {
			StringBuilder sb = new StringBuilder(path);
			sb.deleteCharAt(index);
			path = sb.toString();
			index = path.lastIndexOf('/');
		}
		file = path.substring(index + 1);
		path = path.replace(path.substring(path.lastIndexOf('/') + 1), "");
		return file;
	}
}
