package ro.poo.system;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import ro.poo.commands.Command;
import ro.poo.commands.CommandFactory;

/**
 * Class for reading commands from a file
 * 
 * @author Alexandru Lincan
 *
 */
public class InputParser {
	private BufferedReader buffReader;
	private CommandFactory cmdFactory;
	private final static int MAX_ARGS = 2;

	/**
	 * Constructs an Input Parser for the given input file
	 * 
	 * @param filePath
	 *            the input file
	 */
	public InputParser(String filePath, FileSystem fileSystem) {
		try {
			buffReader = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		cmdFactory = new CommandFactory(fileSystem);
	}

	/**
	 * Parses a line from input file and creates a new command
	 * 
	 * @return the command
	 */
	public Command getCommand() {
		try {
			if (!buffReader.ready()) {
				buffReader.close();
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String command = new String();
		String[] args = new String[MAX_ARGS];
		String line = new String();

		try {
			line = buffReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringTokenizer st = new StringTokenizer(line);
		command = st.nextToken();
		int nr = 0;
		while (st.hasMoreTokens()) {
			if (nr < MAX_ARGS) {
				args[nr++] = st.nextToken();
			} else {
				args[nr - 1] += " " + st.nextToken();
			}
		}

		return cmdFactory.getCommand(line, command, args);
	}
}
