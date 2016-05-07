package dao;

import model.Paciente;
import util.CSVFile;
import util.Hashtable;

public class PacienteDAOHashtable implements PacienteDAO {

	Hashtable<String, Paciente> dados = new Hashtable<>();
	
	@Override
	public Paciente getPaciente(String RG) {
		return dados.get(RG);
	}

	@Override
	public void addPaciente(Paciente paciente) {
		dados.put(paciente.getRG(), paciente);
	}

	@Override
	public void removePaciente(String RG) {
		dados.remove(RG);
	}

	@Override
	public void loadData(CSVFile<Paciente> arquivo) {
		Paciente m = arquivo.readObject();
		while (m != null) {
			addPaciente(m);
			m = arquivo.readObject();
		}
	}

}
