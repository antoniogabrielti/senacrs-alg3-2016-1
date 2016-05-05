package util;

public interface Iterator<T> extends java.util.Iterator<T> {
	boolean hasNext();
	boolean hasPrevious();
	T next();
	T previous();
	void insertBefore(T data);
	void insertAfter(T data);
	void remove();
}
