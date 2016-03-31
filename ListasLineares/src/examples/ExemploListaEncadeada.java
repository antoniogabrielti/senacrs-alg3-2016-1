package examples;

import datastructures.Iterador;
import datastructures.ListaEncadeada;

public class ExemploListaEncadeada {

	public static void main(String[] args) {
		ListaEncadeada<Integer> lista = new ListaEncadeada<>();
		
		lista.append(3);
		lista.prepend(1);	
		imprimeLista(lista);
		Iterador<Integer> iterador = lista.iterator();
		iterador.next();
		iterador.insert(2);
		imprimeLista(lista);
		iterador.next();
		iterador.insert(4);
		imprimeLista(lista);
		iterador.previous();
		iterador.remove();
		imprimeLista(lista);
	}

	private static <T> void imprimeLista(ListaEncadeada<T> lista) {
		for (T dado : lista)
			System.out.println(dado);
		System.out.println("-----------------");
	}

}
