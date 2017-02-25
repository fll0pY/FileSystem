package ro.poo.system;

import ro.poo.files.Directory;

/**
 * User for the file system
 * 
 * @author Alexandru Lincan
 *
 */
public class User {
	private String name;
	private Directory home;

	/**
	 * true if the user is root
	 */
	public boolean isRoot;

	/**
	 * Constructs a user with the specified name
	 * 
	 * @param name
	 *            the user name
	 */
	public User(String name) {
		this.setName(name);
		isRoot = false;
	}

	/**
	 * Gives to this user root Access
	 */
	public void giveRootAccess() {
		this.isRoot = true;
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

	/**
	 * @return the home
	 */
	public Directory getHome() {
		return home;
	}

	/**
	 * @param home
	 *            the home to set
	 */
	public void setHome(Directory home) {
		this.home = home;
	}
}
