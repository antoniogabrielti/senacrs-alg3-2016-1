package model;

public class Medicamento {

	private String nome;
	private long codigo;

	public void setNome(String nome) {
		if (nome != null)
			this.nome = nome;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		String format = "Medicamento: Codigo: %x, Nome: %s";
		return String.format(format, codigo, nome);
	}
}
