package dao;

import model.Paciente;
import util.CSVFile;

public interface PacienteDAO {
	Paciente getPaciente(String RG);
	void addPaciente(Paciente paciente);
	void removePaciente(String RG);
	void loadData(CSVFile<Paciente> arquivo);
}
