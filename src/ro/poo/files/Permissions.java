package ro.poo.files;

/**
 * Permissions bit offset
 * 
 * @author Alexandru Lincan
 *
 */
public enum Permissions {
	READ(2),
	WRITE(1),
	EXECUTE(0);

	private final int value;

	Permissions(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}
}
