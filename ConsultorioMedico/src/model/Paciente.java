package model;

import java.time.LocalDate;

public class Paciente {

	private String nome;
	private String rg;
	private LocalDate dataNascimento;

	public void setNome(String nome) {
		if (nome != null)
			this.nome = nome;
	}

	public void setRG(String rg) {
		if (rg != null)
			this.rg = rg;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		if (dataNascimento.isBefore(LocalDate.now()) &&
			dataNascimento.getYear() > 1850)
				this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public String getRG() {
		return rg;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

}
