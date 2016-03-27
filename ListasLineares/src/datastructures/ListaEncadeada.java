package datastructures;

/**
 * Classe que implementa um no da lista encadeada. Esta
 * classe esta definida com visibilidade de "pacote" para
 * que seja utilizada apenas internamente pelas classes
 * ListaEncdeada e IteradorListaEncadeada.
 *
 * @param <T> O tipo de dado armazenado na lista encadeada.
 */
class Node<T> {
	private T data;
	private Node<T> next;
	private Node<T> previous;
	
	/**
	 * Inicializa um novo no com um dado fornecido.
	 * @param data O dado a ser armazenado no novo no.
	 */
	public Node(T data) {
		this.data = data;
		this.next = null;
		this.previous = null;
	}

	/**
	 * Retorna o objeto atualmente armazenado no no'.
	 * @return O objeto armazenado.
	 */
	public T getData() {
		return data;
	}

	/**
	 * Retorna o proximo no apontado por esse no, ou null
	 * caso nao exista um no posterior.
	 * @return O proximo no, ou null.
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Retorna o no anterior ao no atual, ou null caso nao exista.
	 * @return O no anterior, ou null.
	 */
	public Node<T> getPrevious() {
		return previous;
	}

	/**
	 * Define o no que sera o no anterior a esse no.
	 * @param prev O no que sera o no anterior.
	 */
	public void setPrevious(Node<T> prev) {
		this.previous = prev;
	}

	/**
	 * Define o no que sera o proximo no a esse no.
	 * @param next O proximo no.
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * Insere um elemento antes do atual. Este metodo so
	 * deve ser utilizado quando o no nao for o inicial ou
	 * final da lista.
	 * @param dado O dado a ser inserido na lista.
	 */
	public void insertBefore(T dado) {
		Node<T> novo = new Node<T>(dado);
		Node<T> previous = this.getPrevious();
		novo.setPrevious(previous);
		novo.setNext(this);
		this.setPrevious(novo);
		previous.setNext(novo);
	}
	
}

/**
 * Implementa uma lista duplamente encadeada, que pode ser
 * iterada em um "for-each".
 *
 * @param <T> O tipo de dado armazenado na lista.
 */
public class ListaEncadeada<T>
	implements java.lang.Iterable<T>
{

	private Node<T> head;
	private Node<T> tail;

	/**
	 * Cria um iterador para a lista encadeada.
	 * 
	 */
	public IteradorListaEncadeada<T> iterator() {
		return new IteradorListaEncadeada<T>(this);
	}

	/**
	 * Retorna o primeiro elemento da lista. O metodo deve ter
	 * visibilidade de pacote, para limitar o acesso e diminuir
	 * as dependencias externas e aumentar o encapsulamento.
	 * @return
	 */
	Node<T> getHead() {
		return head;
	}

	/**
	 * Insere um elemento no fim da lista.
	 * @param dado Elemento a ser inserido no final da lista.
	 */
	public void append(T dado) {
		Node<T> novo = new Node<T>(dado);
		if (tail == null) {
			head = novo;
		} else {
			novo.setPrevious(tail);
			tail.setNext(novo);
		}
		tail = novo;
	}

	/**
	 * Insere um elemento no inicio da lista.
	 * @param dado Elemento a ser inserido no inicio da lista.
	 */
	public void prepend(T dado) {
		Node<T> novo = new Node<T>(dado);
		if (head == null) {
			tail = novo;
		} else {
			novo.setNext(head);
			head.setPrevious(novo);
		}
		head = novo;
	}

	/**
	 * Remove um no da lista.
	 * @param iter O no a ser removido.
	 */
	void remove(Node<T> iter) {
		if (iter == null)
			return;
		if (iter == tail) {
			tail = tail.getPrevious();
			tail.setNext(null);
		} else
		if (iter == head) {
			head = head.getNext();
			head.setPrevious(null);
		} else {
			Node<T> previous = iter.getPrevious();
			Node<T> next = iter.getNext();
			next.setPrevious(previous);
			previous.setNext(next);
		}
	}

}
