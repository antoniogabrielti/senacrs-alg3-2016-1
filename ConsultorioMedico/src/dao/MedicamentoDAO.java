package dao;

import model.Medicamento;
import util.CSVFile;

public interface MedicamentoDAO {
	Medicamento getMedicamento(long codigo);
	void addMedicamento(Medicamento medicamento);
	void removeMedicamento(long codigo);
	void loadData(CSVFile<Medicamento> arquivo);
}
