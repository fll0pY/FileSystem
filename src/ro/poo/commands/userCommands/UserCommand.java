package ro.poo.commands.userCommands;

import ro.poo.commands.Command;
import ro.poo.system.FileSystem;

/**
 * Abstract class for commands which work with users
 * 
 * @author Alexandru Lincan
 *
 */
public abstract class UserCommand implements Command {
	protected String fullCommand;
	protected FileSystem fileSystem;
	protected String username;

	/**
	 * Constructs a User Command
	 * 
	 * @param fullCommand
	 *            the full command from input
	 * @param fileSystem
	 *            the file system
	 * @param username
	 *            the name of a user
	 */
	public UserCommand(String fullCommand, FileSystem fileSystem,
			String username) {
		this.fullCommand = fullCommand;
		this.fileSystem = fileSystem;
		this.username = username;
	}

}
