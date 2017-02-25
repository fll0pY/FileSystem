package ro.poo.files;

import ro.poo.system.User;

/**
 * A simple text file
 * 
 * @author Alexandru Lincan
 *
 */
public class File extends Entity {
	private String text;

	/**
	 * Constructs a file with a given owner and default permissions
	 * 
	 * @param owner
	 *            the user who creates the file
	 */
	public File(String name, User owner, Directory parrent) {
		super(name, owner, parrent);
	}

	/**
	 * @return the text contained by the file
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text of the file
	 * 
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean isDirectory() {
		return false;
	}
}
