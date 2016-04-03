public class JavaToken {

	// Delimitadores para o Scanner. O '+' no final pula
	// vários delimitadores juntos em uma única leitura.
	public static final String DELIMITERS = "[ ()\n;]+";
	
	private boolean stringStarted = false;

	public String identify(String token) {
		if (token.isEmpty())
			return "WHITESPACE";
		if (isString(token))
			return "STRING";
		if (isStringStart(token))
			return "STRING_START";
		if (isStringEnd(token))
			return "STRING_END";
		if (isReserved(token))
			return "RESERVED";
		if (stringStarted)
			return "PART_OF_STRING";
		if (isOperator(token))
			return "OPERATOR";
		if (isArray(token))
			return "ARAY_TYPE";
		if (isIdentifier(token))
			return "IDENTIFIER";
		return "UNKNOWN";
	}

	private boolean isArray(String token) {
		if (token.endsWith("]"))
			return true;
		return false;
	}

	private boolean isOperator(String token) {
		switch (token) {
			case "{":
			case "}":
			case "+":
			case "=":
				return true;
		}
		return false;
	}

	private boolean isIdentifier(String token) {
		if (!Character.isJavaIdentifierStart(token.charAt(0))) {
			return false;
		}
		for (char c : token.toCharArray())
			if (!Character.isJavaIdentifierPart(c) && c != '.') {
				return false;
			}
		return true;
	}

	private boolean isReserved(String token) {
		switch (token) {
			case "class":
			case "public":
			case "private":
			case "protected":
			case "import":
			case "static":
			case "final":
			case "return":
			case "boolean":
			case "int":
			case "void":
			case "switch":
			case "case":
			case "break":
			case "default":
			case "if":
			case "try":
			case "catch":
			case "finally":
			case "new":
				return true;
		}
		return false;
	}

	private boolean isStringStart(String token) {
		if (token.startsWith("\"") && !stringStarted) {
			stringStarted = true;
			return true;
		}
		return false;
	}

	private boolean isStringEnd(String token) {
		if (token.endsWith("\"") && stringStarted) {
			stringStarted = false;
			return true;
		}
		return false;
	}

	private boolean isString(String token) {
		if (token.startsWith("\"") && token.endsWith("\"") && token.length() > 2)
			return true;
		return false;
	}
	
}
