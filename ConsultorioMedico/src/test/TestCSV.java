package test;

import java.io.FileNotFoundException;

import dao.MedicamentoParser;
import dao.PacienteParser;
import util.CSVFile;
import util.CSVParser;

/**
 * Teste de leitura do sistema de leitura de CSV.
 */
public class TestCSV {

	private static <T> void readFile(String filename, CSVParser<T> parser) {
		CSVFile<T> reader = new CSVFile<>();
		reader.setParser(parser);
		try {
			reader.open(filename);
			T object = (T) reader.readObject();
			while (object != null) {
				System.out.println(object);
				object = (T) reader.readObject();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo de dados nao encontrado: " + filename);
		}
	}
	
	public static void main(String[] args) {
		readFile("data/pacientes.csv", new PacienteParser());
		readFile("data/medicamentos.csv", new MedicamentoParser());
	}
}
