package dao;

import java.util.Comparator;

import model.Medicamento;
import util.CSVFile;
import util.Vetor;

public class MedicamentoDAOVetor implements MedicamentoDAO {

	private Vetor<Medicamento> dados = new Vetor<>();
	private boolean sorted = false;
	
	@Override
	public Medicamento getMedicamento(long codigo) {
		if (sorted)
			return binarySearch(codigo);
		return linearSearch(codigo);
	}

	private Medicamento binarySearch(long codigo) {
		Medicamento medicamento = null;
		int s = 0, e = dados.getSize();
		while (e-s > 1) {
			int m = (e-s)/2;
			medicamento = dados.get(m);
			long cod = medicamento.getCodigo();
			if (cod < codigo)
				s = m;
			else
			if (cod > codigo)
				e = m;
			else
				return medicamento;
		}
		return null;
	}

	private Medicamento linearSearch(long codigo) {
		for (int i = 0; i < dados.getSize(); i++) {
			Medicamento m = dados.get(i);
			if (m.getCodigo() == codigo)
				return m;
		}
		return null;
	}

	@Override
	public void addMedicamento(Medicamento medicamento) {
		dados.append(medicamento);
		sorted = false;
	}

	@Override
	public void removeMedicamento(long codigo) {
		// TODO
		sorted = false;
	}

	@Override
	public void loadData(CSVFile<Medicamento> arquivo) {
		Medicamento m = arquivo.readObject();
		while (m != null) {
			addMedicamento(m);
			m = arquivo.readObject();
		}
	}
	
	public void sort(Comparator<Medicamento> cmp) {
		dados.sort(cmp);
		sorted = true;
	}
	
	public void list() {
		for (Medicamento m : dados)
			System.out.println(m);
	}
}
