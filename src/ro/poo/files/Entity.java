package ro.poo.files;

import ro.poo.system.User;

/**
 * Class for files and directories
 * 
 * @author Alexandru Lincan
 *
 */
public abstract class Entity {
	private String name;
	private Directory parrent;
	private User owner;
	private int ownerPermissions;
	private int othersPermissions;

	/**
	 * Constructs an entity with a given owner and default permissions
	 * 
	 * @param owner
	 *            the user who creates the entity
	 */
	public Entity(String name, User owner, Directory parrent) {
		this.setName(name);
		this.setOwner(owner);
		this.setParrent(parrent);
		ownerPermissions = 7;
		othersPermissions = 0;
	}

	/**
	 * Constructs a string with a friendlier format of the permissions
	 * 
	 * @return the permissions string
	 */
	public String getPermissions() {
		char[] perm = { 'r', 'w', 'x' };

		String permissions = new String();
		if (this.isDirectory()) {
			permissions += "d";
		} else {
			permissions += "f";
		}

		for (int i = 0; i < perm.length; i++) {
			int bit = perm.length - i - 1;
			if ((ownerPermissions & (1 << bit)) != 0) {
				permissions += perm[i];
			} else {
				permissions += "-";
			}
		}
		for (int i = 0; i < perm.length; i++) {
			int bit = perm.length - i - 1;
			if ((othersPermissions & (1 << bit)) != 0) {
				permissions += perm[i];
			} else {
				permissions += "-";
			}
		}
		return permissions;
	}

	/**
	 * Returns a ls file format to print
	 */
	@Override
	public String toString() {
		String toPrint = new String();
		toPrint = name + " " + getPermissions() + " " + owner.getName();
		return toPrint;
	}

	/**
	 * 
	 * @return true if this entity is a directory and false otherwise
	 */
	abstract public boolean isDirectory();

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @return the permissions for other users
	 */
	public int getOthersPermissions() {
		return othersPermissions;
	}

	/**
	 * @param othersPermissions
	 *            the permissions to set for other users
	 */
	public void setOthersPermissions(int othersPermissions) {
		this.othersPermissions = othersPermissions;
	}

	/**
	 * @return the owner's permissions
	 */
	public int getOwnerPermissions() {
		return ownerPermissions;
	}

	/**
	 * @param ownerPermissions
	 *            the permissions to set for the owner
	 */
	public void setOwnerPermissions(int ownerPermissions) {
		this.ownerPermissions = ownerPermissions;
	}

	/**
	 * @return the parrent
	 */
	public Directory getParrent() {
		return parrent;
	}

	/**
	 * @param parrent
	 *            the parrent to set
	 */
	public void setParrent(Directory parrent) {
		this.parrent = parrent;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}