package util;

import java.util.Comparator;

public class ListaEncadeada<T> implements Iterable<T>, Sortable<T> {

	class Node {
		T data;
		public Node previous;
		public Node next;
		
		Node(T data) {
			this.data = data;
		}		
		void insert(Node before, Node after) {
			this.previous = before;
			this.next = after;
			if (before != null)
				before.next = this;
			if (after != null)
				after.previous = this;
		}
		void remove() {
			if (previous != null)
				previous.next = next;
			if (next != null)
				next.previous = previous;
			next = null;
			previous = null;
		}
	}

	class ListaIterator implements Iterator<T> {

		ListaEncadeada<T> container;
		Node current;
		
		public ListaIterator(ListaEncadeada<T> container) {
			this.container = container;
			this.current = container.head;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public boolean hasPrevious() {
			return current == null || current.previous != null;
		}

		@Override
		public T next() {
			T data = current.data;
			current = current.next;
			return data;
		}

		@Override
		public T previous() {
			if (current == null)
				current = container.tail;
			else
				current = current.previous;
			return current.data;
		}

		@Override
		public void insertBefore(T data) {
			Node novo = new Node(data);
			if (current.previous == null)
				head = novo;
			novo.insert(current.previous, current);
		}

		@Override
		public void insertAfter(T data) {
			Node novo = new Node(data);
			if (current == null)
				current = tail;
			if (current.next == null)
				tail = novo;
			novo.insert(current, current.next);
		}

		@Override
		public void remove() {
			if (current == null)
				current = tail;
			Node novo = current.next;
			if (current.next == null) {
				tail = current.previous;
				novo = current.previous;
			}
			if (current.previous == null)
				head = current.next;
			current.remove();
			current = novo;
		}
		
	}
	
	private Node head;
	private Node tail;
	
	public void append(T data) {
		Node novo = new Node(data);
		if (tail == null) {
			head = novo;
		} else {
			novo.previous = tail;
			tail.next = novo;
		}
		tail = novo;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ListaIterator(this);
	}

	// Insertion sort
	@Override
	public void sort(Comparator<T> cmp) {
		if (head == null) return;
		Node novo = head.next;
		while (novo != null) {
			Node current = novo.previous, next = novo.next;
			while (current!=null && cmp.compare(current.data, novo.data) > 0)
				current = current.previous;
			// swap nodes
			swapNodes(novo, current);
			novo = next;
		}
	}

	private void swapNodes(Node novo, Node current) {
		if (current != null && current.next == novo)
			return;
		Node previous = novo.previous;
		Node next = novo.next;
		previous.next = next;
		// remove novo
		if (novo == tail)
			tail = previous;
		novo.remove();
		// Insere depois do current.
		if (current != null) {
			novo.insert(current,current.next);
		} else {
			novo.insert(null,head);
			head = novo;
		}
	}
		
	public static void main(String...strings) {
		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		lista.append(4);
		lista.append(2);
		lista.append(3);
		lista.append(1);
		lista.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
			
		});

		for (Integer n : lista)
			System.out.println(n);
	}
}
