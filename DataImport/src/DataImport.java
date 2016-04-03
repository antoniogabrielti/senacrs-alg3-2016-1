import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DataImport {

	public static void main(String[] args) {
		try {
			(new DataImport()).run();
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
		}
	}

	private void run()
		throws FileNotFoundException
	{
		// Objeto para ler o arquivo.
		FileReader file = new FileReader("src/DataImport.java");
		// Objeto para quebrar o arquivo em 'tokens'
		Scanner scanner = new Scanner(file);
		// Objeto para analizar os tokens.
		JavaToken java = new JavaToken();
		
		// Configura o scanner.
		scanner.useDelimiter(JavaToken.DELIMITERS);
		
		while (scanner.hasNext()) {
			String token = scanner.next().trim();
			String type = java.identify(token);
			System.out.println(token + ": " + type);
		}
		
		scanner.close();
	}

}
