
import ro.poo.commands.Command;
import ro.poo.system.FileSystem;
import ro.poo.system.InputParser;

/**
 * Class for running commands from a file
 * 
 * @author Alexandru Lincan
 *
 */
public class Test {
	private FileSystem fileSystem;
	private InputParser input;

	/**
	 * Constructs a command runner with the given input file
	 * 
	 * @param filePath
	 *            the input file
	 */
	public Test(String filePath) {
		fileSystem = new FileSystem();
		input = new InputParser(filePath, fileSystem);
	}

	/**
	 * Runs all the commands from the input file
	 */
	public void execCommands() {
		Command command;

		command = input.getCommand(); // skip the first mkdir command
		command = input.getCommand();
		while (command != null) {
			fileSystem.executeCommand(command);
			command = input.getCommand();
		}
		fileSystem.printFilesTree();
	}

	public static void main(String[] args) {
		Test cmdRunner = new Test(args[0]);
		cmdRunner.execCommands();
	}
}