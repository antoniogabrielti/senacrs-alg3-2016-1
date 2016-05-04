package dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.Paciente;
import util.CSVParser;

public class PacienteParser implements CSVParser<Paciente> {

	private static final String DATE_FORMAT = "yyy-MM-dd";

	@Override
	public Paciente parseObject(String csvdata) {
		// garante que o scanner sera fechado ao encerrar.
		try (Scanner scanner = new Scanner(csvdata)) {
			scanner.useDelimiter(";");
			Paciente paciente = new Paciente();
			paciente.setNome(scanner.next());
			paciente.setRG(scanner.next());
			paciente.setDataNascimento(convertDate(scanner.next()));
			return paciente;
		}
	}

	private LocalDate convertDate(String date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return LocalDate.parse(date, format);
	}

}
