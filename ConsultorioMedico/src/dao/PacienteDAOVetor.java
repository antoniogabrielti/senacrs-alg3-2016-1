package dao;

import java.util.Comparator;

import model.Paciente;
import util.CSVFile;
import util.Vetor;

public class PacienteDAOVetor implements PacienteDAO {

	private Vetor<Paciente> dados = new Vetor<>();
	private boolean sorted = false;
	
	@Override
	public Paciente getPaciente(String RG) {
		if (sorted)
			return binarySearch(RG);
		return linearSearch(RG);
	}

	private Paciente binarySearch(String RG) {
		Paciente Paciente = null;
		int s = 0, e = dados.getSize();
		while (e-s > 1) {
			int m = (e-s)/2;
			Paciente = dados.get(m);
			int cmp = RG.compareTo(Paciente.getRG());
			if (cmp > 0)
				s = m;
			else
			if (cmp < 0)
				e = m;
			else
				return Paciente;
		}
		return null;
	}

	private Paciente linearSearch(String RG) {
		for (int i = 0; i < dados.getSize(); i++) {
			Paciente m = dados.get(i);
			if (m.getRG() == RG)
				return m;
		}
		return null;
	}

	@Override
	public void addPaciente(Paciente paciente) {
		dados.append(paciente);
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
		dados.sort(cmp);
		sorted = true;
	}
	
	public void list() {
		for (Paciente m : dados)
			System.out.println(m);
	}
}
