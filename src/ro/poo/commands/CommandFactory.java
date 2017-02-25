package ro.poo.commands;

import ro.poo.commands.fileCommands.*;
import ro.poo.commands.userCommands.*;
import ro.poo.system.FileSystem;

/**
 * Class for creating commands instances for a given command type
 * 
 * @author Alexandru Lincan
 *
 */
public class CommandFactory {
	FileSystem fileSystem;

	/**
	 * Constructs a Commands Factory
	 * 
	 * @param fileSystem
	 */
	public CommandFactory(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	/**
	 * Constructs a command with the given arguments
	 * 
	 * @param fullCommand
	 *            the full command from input
	 * @param command
	 *            the command name
	 * @param args
	 *            the parameters
	 * @return a instance of the proper command
	 */
	public Command getCommand(String fullCommand, String command,
			String[] args) {
		switch (command) {
			case "adduser":
				return getAddUser(fullCommand, command, args);
			case "deluser":
				return getDelUser(fullCommand, command, args);
			case "chuser":
				return getChUser(fullCommand, command, args);
			case "cd":
				return getCd(fullCommand, command, args);
			case "mkdir":
				return getMkdir(fullCommand, command, args);
			case "ls":
				return getLs(fullCommand, command, args);
			case "chmod":
				return getChmod(fullCommand, command, args);
			case "touch":
				return getTouch(fullCommand, command, args);
			case "rm":
				return getRm(fullCommand, command, args);
			case "rmdir":
				return getRmdir(fullCommand, command, args);
			case "writetofile":
				return getWriteToFile(fullCommand, command, args);
			case "cat":
				return getCat(fullCommand, command, args);
			default:
				return null;
		}
	}

	private AddUser getAddUser(String fullCommand, String command,
			String[] args) {
		AddUser addUser = new AddUser(fullCommand, fileSystem, args[0]);
		return addUser;
	}

	private DelUser getDelUser(String fullCommand, String command,
			String[] args) {
		DelUser delUser = new DelUser(fullCommand, fileSystem, args[0]);
		return delUser;
	}

	private ChUser getChUser(String fullCommand, String command,
			String[] args) {
		ChUser chUser = new ChUser(fullCommand, fileSystem, args[0]);
		return chUser;
	}

	private Cd getCd(String fullCommand, String command, String[] args) {
		Cd cd = new Cd(fullCommand, fileSystem, args[0]);
		return cd;
	}

	private Mkdir getMkdir(String fullCommand, String command, String[] args) {
		Mkdir mkdir = new Mkdir(fullCommand, fileSystem, args[0]);
		return mkdir;
	}

	private Ls getLs(String fullCommand, String command, String[] args) {
		Ls ls = new Ls(fullCommand, fileSystem, args[0]);
		return ls;
	}

	private Chmod getChmod(String fullCommand, String command, String[] args) {
		Chmod chmod = new Chmod(fullCommand, fileSystem, args[1], args[0]);
		return chmod;
	}

	private Touch getTouch(String fullCommand, String command, String[] args) {
		Touch touch = new Touch(fullCommand, fileSystem, args[0]);
		return touch;
	}

	private Rm getRm(String fullCommand, String command, String[] args) {
		Rm rm;
		if (args[0].equals("-r")) {
			rm = new Rm(fullCommand, fileSystem, args[1], true);
		} else {
			rm = new Rm(fullCommand, fileSystem, args[0], false);
		}
		return rm;
	}

	private Rmdir getRmdir(String fullCommand, String command, String[] args) {
		Rmdir rmdir = new Rmdir(fullCommand, fileSystem, args[0]);
		return rmdir;
	}

	private WriteToFile getWriteToFile(String fullCommand, String command,
			String[] args) {
		WriteToFile write = new WriteToFile(fullCommand, fileSystem, args[0],
				args[1]);
		return write;
	}

	private Cat getCat(String fullCommand, String command, String[] args) {
		Cat cat = new Cat(fullCommand, fileSystem, args[0]);
		return cat;
	}
}