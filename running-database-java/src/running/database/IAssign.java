package running.database;

public interface IAssign {
	boolean assign(String name, boolean value, boolean old);
	boolean assign(String name, byte value, byte old);
	boolean assign(String name, short value, short old);
	boolean assign(String name, int value, int old);
	boolean assign(String name, long value, long old);
	boolean assign(String name, String value, String old);
}