package dao;

import model.Medicamento;
import model.Paciente;
import util.CSVFile;
import util.Hashtable;

public class MedicamentoDAOHashtable implements MedicamentoDAO {

	Hashtable<Long, Medicamento> dados = new Hashtable<>();

	@Override
	public Medicamento getMedicamento(long codigo) {
		return dados.get(codigo);
	}

	@Override
	public void addMedicamento(Medicamento medicamento) {
		dados.put(medicamento.getCodigo(), medicamento);
	}

	@Override
	public void removeMedicamento(long codigo) {
		dados.remove(codigo);
	}

	@Override
	public void loadData(CSVFile<Medicamento> arquivo) {
		Medicamento m = arquivo.readObject();
		while (m != null) {
			addMedicamento(m);
			m = arquivo.readObject();
		}
	}

}
