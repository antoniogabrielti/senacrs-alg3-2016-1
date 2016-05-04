package dao;

import java.util.Scanner;

import model.Medicamento;
import util.CSVParser;

public class MedicamentoParser implements CSVParser<Medicamento> {
	@Override
	public Medicamento parseObject(String csvdata) {
		// garante que o scanner sera fechado ao encerrar.
		try (Scanner scanner = new Scanner(csvdata)) {
			scanner.useDelimiter(";");
			Medicamento medicamento = new Medicamento();
			medicamento.setCodigo(scanner.nextLong(16));
			medicamento.setNome(scanner.next());
			return medicamento;
		}
	}
}
