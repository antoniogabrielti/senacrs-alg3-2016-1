package test;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;

import dao.PacienteParser;
import model.Paciente;
import util.CSVFile;

/**
 * Teste de leitura do sistema de leitura de CSV.
 */
public class TestCSV {

	public static void main(String[] args) {
		CSVFile<Paciente> reader = new CSVFile<>();
		reader.setParser(new PacienteParser());
		try {
			reader.open("data/pacientes.csv");
			Paciente paciente = reader.readObject();
			while (paciente != null) {
				printPaciente(paciente);
				paciente = reader.readObject();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo de dados nao encontrado.");
		}
		
	}

	private static void printPaciente(Paciente paciente) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = paciente.getDataNascimento().format(formatter);
		String format = "Paciente: Nome = %s, RG = %s, Nasc.= %s";
		String out = String.format(format,
		                           paciente.getNome(),
		                           paciente.getRG(),
		                           date);
		System.out.println(out);
	}

}
