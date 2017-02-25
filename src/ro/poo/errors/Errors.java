package ro.poo.errors;

import java.util.HashMap;
import java.util.Map;

/**
 * Static class for errors management
 * 
 * @author Alexandru Lincan
 *
 */
public final class Errors {
	private static final Map<Integer, String> MSG;
	static {
		MSG = new HashMap<Integer, String>();
		MSG.put(-1, "Is a directory");
		MSG.put(-2, "No such directory");
		MSG.put(-3, "Not a directory");
		MSG.put(-4, "No rights to read");
		MSG.put(-5, "No rights to write");
		MSG.put(-6, "No rights to execute");
		MSG.put(-7, "File already exists");
		MSG.put(-8, "User does not exist");
		MSG.put(-9, "User already exists");
		MSG.put(-10, "No rights to change user status");
		MSG.put(-11, "No such file");
		MSG.put(-12, "No such file or directory");
		MSG.put(-13, "Cannot delete parent or current directory");
		MSG.put(-14, "Non empty directory");
	}
	
	/**
	 * Prints to std out an error
	 * 
	 * @param errorCode 
	 * @param command
	 */
	public static void throwError(int errorCode, String command) {
		System.out.println(errorCode + ": " + command + ": " 
				+ MSG.get(errorCode));
	}
}
