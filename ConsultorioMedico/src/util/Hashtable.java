package util;


public class Hashtable<K, V> {

	private class Hashdata {
		final K key;
		final V value;
		Hashdata(K key, V value) {
			this.key = key;
			this.value = value;
		}
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Hashtable.Hashdata))
				return false;
			// independente do tipo de dados utilizados para a Hashtable...
			return key.equals(((Hashtable<?,?>.Hashdata)o).key);
		}
		@Override
		public String toString() {
			return this.key + ":" + this.value;
		}
	}

	// Armazenara apenas objetos Hashdata
	private Hashtable<K,V>.Hashdata[] table;
	private int elements = 0;
	
	// Utilizado para gerar hashes.
	private java.util.Random rand = new java.util.Random();

	
	public Hashtable() {
		this(10);
	}
	
	@SuppressWarnings("unchecked")
	public Hashtable(int size) {
		this.table = new Hashtable.Hashdata[size];
	}
	
	public double loadFactor() {
		return elements / (1.0 * table.length);
	}
	
	private int[] getHashes(K key) {
		// calcula duas hashes para a chave.
		rand.setSeed(key.hashCode());
		int[] h = { Math.abs(key.hashCode()), Math.abs(rand.nextInt()) };
		return h;
	}
	
	private int getIndex(int h1, int h2, int i) {
		return Math.abs(h1 + i * h2) % table.length;
	}
	
	public V get(K key) {
		int[] hashes = getHashes(key);
		for (int i = 0; i < table.length; i++) {
			// double hash probing
			int pos = getIndex(hashes[0], hashes[1], i);
			if (table[pos] != null && table[pos].key.equals(key))
				return table[pos].value;
		}
		return null;
	}
	
	public void put(K key, V value) {
		Hashdata dado = new Hashdata(key, value);
		int[] hashes = getHashes(key);
		if (! insert(dado,hashes[0],hashes[1])) {
			rehash();
			insert(dado,hashes[0],hashes[1]);
		}
	}

	private boolean insert(Hashtable<K, V>.Hashdata dado, int h1, int h2) {
		for (int i = 0; i < table.length; i++) {
			// double hash probing
			int pos = getIndex(h1, h2, i);
			if (table[pos] == null || table[pos].key.equals(dado.key)) {
				table[pos] = dado;
				elements++;
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void rehash() {
		Hashdata[] table = this.table;
		this.table = new Hashtable.Hashdata[this.table.length * 2];
		for (Hashtable.Hashdata e : table) {
			if (e == null) continue;
			int[] hashes = getHashes((K)e.key);
			insert(e,hashes[0],hashes[1]);			
		}
	}
}
