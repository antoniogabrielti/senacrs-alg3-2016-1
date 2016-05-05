package test;

import java.io.FileNotFoundException;
import java.util.Comparator;

//import dao.MedicamentoDAO;
import dao.MedicamentoDAOVetor;
import dao.MedicamentoParser;
import dao.PacienteDAOLista;
import dao.PacienteDAOVetor;
import dao.PacienteParser;
import model.Medicamento;
import model.Paciente;
import util.CSVFile;

public class TestDAO {

	private static final Comparator<Medicamento> indexMedicamentoNome =
			new Comparator<Medicamento>() {
				@Override
				public int compare(Medicamento o1, Medicamento o2) {
					return o1.getNome().compareTo(o2.getNome());
				}
			};
		
	private static final Comparator<Medicamento> indexMedicamentoCodigo =
			new Comparator<Medicamento>() {
				@Override
				public int compare(Medicamento o1, Medicamento o2) {
					Long c1 = (Long)o1.getCodigo();
					Long c2 = (Long)o2.getCodigo();
					return c1.compareTo(c2);
				}
			};
				

	private static final Comparator<Paciente> indexPacienteNome =
			new Comparator<Paciente>() {
				@Override
				public int compare(Paciente o1, Paciente o2) {
					return o1.getNome().compareTo(o2.getNome());
				}
			};
		
	private static final Comparator<Paciente> indexPacienteRG =
			new Comparator<Paciente>() {
				@Override
				public int compare(Paciente o1, Paciente o2) {
					return o1.getRG().compareTo(o2.getRG());
				}
			};
				

			
	public static void main(String[] args) throws FileNotFoundException {
		testaMedicamentoDAO();
		testaPacienteDAO();		
	}

	private static void testaMedicamentoDAO() throws FileNotFoundException {
		CSVFile<Medicamento> reader = new CSVFile<>();
		reader.setParser(new MedicamentoParser());
		reader.open("data/medicamentos.csv");

		MedicamentoDAOVetor medDAO = new MedicamentoDAOVetor();
		System.out.println("Loading data...");
		medDAO.loadData(reader);
		System.out.println("Sorting...");
		medDAO.sort(indexMedicamentoNome);
		medDAO.list();
		System.out.println("Sorting...");
		medDAO.sort(indexMedicamentoCodigo);
		medDAO.list();
	}

	private static void testaPacienteDAO() throws FileNotFoundException {
		CSVFile<Paciente> reader = new CSVFile<>();
		reader.setParser(new PacienteParser());
		reader.open("data/pacientes.csv");

		PacienteDAOVetor vecDAO = new PacienteDAOVetor();
		System.out.println("Loading data...");
		vecDAO.loadData(reader);
		System.out.println("Sorting...");
		vecDAO.sort(indexPacienteNome);
		vecDAO.list();
		System.out.println("Sorting...");
		vecDAO.sort(indexPacienteRG);
		vecDAO.list();

		PacienteDAOLista listDAO = new PacienteDAOLista();
		System.out.println("Loading data...");
		listDAO.loadData(reader);
		System.out.println("Sorting...");
		listDAO.sort(indexPacienteNome);
		listDAO.list();
		System.out.println("Sorting...");
		listDAO.sort(indexPacienteRG);
		listDAO.list();
	}
}
