package test;

import java.io.FileNotFoundException;
import java.util.Comparator;

//import dao.MedicamentoDAO;
import dao.MedicamentoDAOVetor;
import dao.MedicamentoParser;
import model.Medicamento;
import util.CSVFile;

public class TestDAO {

	private static final Comparator<Medicamento> indexNome =
			new Comparator<Medicamento>() {
				@Override
				public int compare(Medicamento o1, Medicamento o2) {
					return o1.getNome().compareTo(o2.getNome());
				}
			};
		
	private static final Comparator<Medicamento> indexCodigo =
			new Comparator<Medicamento>() {
				@Override
				public int compare(Medicamento o1, Medicamento o2) {
					return ((Long)o1.getCodigo()).compareTo(((Long)o2.getCodigo()));
				}
			};
				
	public static void main(String[] args) throws FileNotFoundException {
		CSVFile<Medicamento> reader = new CSVFile<>();
		reader.setParser(new MedicamentoParser());
		reader.open("data/med2.csv");

		MedicamentoDAOVetor medDAO = new MedicamentoDAOVetor();
		System.out.println("Loading data...");
		medDAO.loadData(reader);
		System.out.println("Sorting...");
		medDAO.sort(indexNome);
		medDAO.list();
		System.out.println("Sorting...");
		medDAO.sort(indexCodigo);
		medDAO.list();
	}
}
