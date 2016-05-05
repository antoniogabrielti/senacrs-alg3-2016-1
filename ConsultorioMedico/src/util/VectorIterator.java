package util;

public class VectorIterator<T>
				implements Iterator<T>
{

	private Vetor<T> container;
	private int current;

	public VectorIterator(Vetor<T> vetor) {
		this.container = vetor;
	}

	@Override
	public boolean hasNext() {
		return current < (container.getSize()-1);
	}

	@Override
	public boolean hasPrevious() {
		return current > 0;
	}

	@Override
	public T next() {
		if (!hasNext())
			return null;
		current++;
		return container.get(current);
	}

	@Override
	public T previous() {
		if (!hasPrevious())
			return null;
		current--;
		return container.get(current);
	}

	@Override
	public void insertBefore(T data) {
		container.insert(current, data);
		current++;
	}

	@Override
	public void insertAfter(T data) {
		container.insert(current+1, data);
	}

	@Override
	public void remove() {
		container.remove(current);
		if (current == container.getSize())
			current--;
	}

}
