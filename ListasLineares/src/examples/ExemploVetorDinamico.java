package examples;

import datastructures.Iterador;
import datastructures.Vetor;

public class ExemploVetorDinamico {

	public static void main(String[] args) {
		Vetor<Integer> lista = new Vetor<>();
		
		lista.append(1);
		lista.append(3);
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

	private static <T> void imprimeLista(Vetor<T> lista) {
		for (T dado : lista)
			System.out.println(dado);
		System.out.println("-----------------");
	}

}
