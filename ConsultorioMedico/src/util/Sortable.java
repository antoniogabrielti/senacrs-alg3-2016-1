package util;

import java.util.Comparator;

public interface Sortable<T> {
	public void sort(Comparator<T> cmp);
}
