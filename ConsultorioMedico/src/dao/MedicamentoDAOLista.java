package dao;

import java.util.Comparator;

import model.Medicamento;
import util.CSVFile;
import util.ListaEncadeada;

public class MedicamentoDAOLista implements MedicamentoDAO {

	private ListaEncadeada<Medicamento> lista = new ListaEncadeada<>();
	private boolean sorted = false;
	
	@Override
	public Medicamento getMedicamento(long codigo) {
		for (Medicamento m : lista) {
			if (m.getCodigo() == codigo)
				return m;
			if (m.getCodigo() > codigo && sorted)
				break;
		}
		return null;
	}

	@Override
	public void addMedicamento(Medicamento medicamento) {
		lista.append(medicamento);
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
		lista.sort(cmp);
		sorted = true;
	}
	
	public void list() {
		for (Medicamento m : lista)
			System.out.println(m);
	}
}
