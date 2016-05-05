package util;

import java.util.Comparator;

public class Vetor<T> implements Iterable<T>, Sortable<T>
{
	private Object[] data = new Object[10];
	private int count = 0;
	
	public void append(T value) {
		insert(count,value);
	}
	
	public void insert(int index, T value) {
		if (index < 0 || index > count)
			throw new ArrayIndexOutOfBoundsException("Indice invalido.");
		ensureSpace();
		System.arraycopy(data, index, data, index+1, count-index);
		data[index] = value;
		count++;
	}

	public void remove(int index) {
		if (index < 0 || index > count-1)
			throw new ArrayIndexOutOfBoundsException("Indice invalido.");
		System.arraycopy(data, index+1, data, index, count-index);
		count--;
		data[count] = null;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index > count-1)
			throw new ArrayIndexOutOfBoundsException("Indice invalido.");
		return (T) data[index];
	}
	
	private void ensureSpace() {
		if (count == data.length) {
			Object[] novo = new Object[newSize()];
			System.arraycopy(data, 0, novo, 0, data.length);
			data = novo;
		}
	}

	private int newSize() {
		if (data.length < 2048)
			return data.length * 2;
		return data.length + 1024;
	}
	
	public int getSize() {
		return count;
	}
	
	// Selection sort.
	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparator<T> cmp) {
		for (int i = 0; i < count-1; i++) {
			int index = i;
			for (int j = i+1; j < count; j++) {
				if (cmp.compare((T)data[j],(T)data[index]) < 0)
					index = j;
			}
			T t = (T)data[index];
			data[index] = data[i];
			data[i] = t;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new VectorIterator<T>(this);
	}
}
