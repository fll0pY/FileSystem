package ro.poo.commands.fileCommands;

import ro.poo.errors.Errors;
import ro.poo.files.Directory;
import ro.poo.files.Entity;
import ro.poo.files.Permissions;
import ro.poo.system.FileSystem;

/**
 * Class for changing the permission of a file or directory
 * 
 * @author Alexandru Lincan
 *
 */
public class Chmod extends FileCommand {
	private String permissionsString;
	private String fileToChange;
	private int ownerPermissions;
	private int othersPermissions;

	/**
	 * Constructs a chmod command and sets the parameters
	 * 
	 * @param fullCommand
	 * @param fileSystem
	 * @param path
	 * @param permissions
	 */
	public Chmod(String fullCommand, FileSystem fileSystem, String path,
			String permissions) {
		super(fullCommand, fileSystem, path);
		this.permissionsString = permissions;
	}

	/**
	 * Changes the permissions of a file
	 */
	@Override
	public void execute() {
		parsePermissions();
		fileToChange = parsePath();
		Directory location = fileSystem.goToPath(path, fullCommand);
		if (location == null) {
			return;
		}

		Entity file = null;
		if (fileToChange.equals(".")) {
			file = location;
		} else if (fileToChange.equals("..")) {
			file = location.getParrent();
		} else {
			for (Entity it : location.getChildren()) {
				if (it.getName().equals(fileToChange)) {
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
				Permissions.WRITE)) {
			Errors.throwError(-5, fullCommand);
			return;
		}
		file.setOwnerPermissions(ownerPermissions);
		file.setOthersPermissions(othersPermissions);
	}

	private void parsePermissions() {
		ownerPermissions = Integer.parseInt(permissionsString.substring(0, 1));
		othersPermissions = Integer.parseInt(permissionsString.substring(1));
	}
}
