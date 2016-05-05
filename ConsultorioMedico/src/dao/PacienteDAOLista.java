package dao;

import java.util.Comparator;

import model.Paciente;
import util.CSVFile;
import util.ListaEncadeada;

public class PacienteDAOLista implements PacienteDAO {

	private ListaEncadeada<Paciente> lista = new ListaEncadeada<>();
	private boolean sorted = false;
	
	@Override
	public Paciente getPaciente(String RG) {
		for (Paciente m : lista) {
			if (m.getRG().equals(RG))
				return m;
			if (m.getRG().compareTo(RG) > 0 && sorted)
				break;
		}
		return null;
	}

	@Override
	public void addPaciente(Paciente Paciente) {
		lista.append(Paciente);
		sorted = false;
	}

	@Override
	public void removePaciente(String RG) {
		// TODO
		sorted = false;
	}

	@Override
	public void loadData(CSVFile<Paciente> arquivo) {
		Paciente m = arquivo.readObject();
		while (m != null) {
			addPaciente(m);
			m = arquivo.readObject();
		}
	}
	
	public void sort(Comparator<Paciente> cmp) {
		lista.sort(cmp);
		sorted = true;
	}
	
	public void list() {
		for (Paciente m : lista)
			System.out.println(m);
	}
}
