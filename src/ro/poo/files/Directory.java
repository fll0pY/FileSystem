package ro.poo.files;

import java.util.ArrayList;

import ro.poo.system.User;

public class Directory extends Entity {
	private ArrayList<Entity> children;

	/**
	 * Constructs a directory with a given owner and default permissions
	 * 
	 * @param owner
	 *            the user who creates the directory
	 */
	public Directory(String name, User owner, Directory parrent) {
		super(name, owner, parrent);
		children = new ArrayList<Entity>();
	}

	/**
	 * Adds an entity to the directory
	 * 
	 * @param entity
	 *            the file or folder to add to the directory
	 */
	public void addEntity(Entity entity) {
		children.add(entity);
	}

	/**
	 * Deletes an entity from its childrens
	 * 
	 * @param entity
	 */
	public void DelEntity(Entity entity) {
		children.remove(entity);
	}

	/**
	 * @return the list of files and folders contained by the directory
	 */
	public ArrayList<Entity> getChildren() {
		return children;
	}

	@Override
	public boolean isDirectory() {
		return true;
	}
}
