import java.util.*;
import java.io.*;

public class Scanner {

	public List<String> tokens;
	public String inValidFirstStringEncountered;

	public Scanner() {
		tokens = new ArrayList<String>();
		inValidFirstStringEncountered = "";
	}

	public boolean isMetaStatement(String currentLine) {
		if (currentLine.startsWith("#") || currentLine.startsWith("//")) {
			//tokens.add(currentLine);
			return true;
		} else
			return false;
	}

	public boolean isLineEmpty(String currentLine) {
		if (currentLine.length() == 0) {
			return true;
		} else
			return false;
	}

	public boolean isReservedWord(String str) {
		if (str.equals("int") || str.equals("void") || str.equals("if") || str.equals("while") || str.equals("return")
				|| str.equals("read") || str.equals("write") || str.equals("print") || str.equals("continue")
				|| str.equals("break") || str.equals("binary") || str.equals("decimal")) {
			tokens.add(str);
			return true;
		} else
			return false;
	}

	public boolean isSymbol(String str) {
		char c;
		if (str.length() == 1) {
			c = str.charAt(0);
			if (c == '(' || c == ')' || c == '{' || c == '}' || c == '[' || c == ']' || c == ',' || c == ';' || c == '+'
					|| c == '-' || c == '*' || c == '/') {
				tokens.add(str);
				return true;

			}
		}

		if (str.equals("==") || str.equals("!=") || str.equals(">=") || str.equals(">") || str.equals("<=")
				|| str.equals("<") || str.equals("=") || str.equals("&&") || str.equals("||")) {
			tokens.add(str);
			return true;
		}

		return false;
	}

	public boolean isString(String str) {
		str = str.trim();
		if (str.startsWith("\"") && str.endsWith("\"")) {
			tokens.add(str);
			return true;
		} else
			return false;
	}

	public boolean isNumber(String str) {
		if (str.matches("[0-9]+")) {
			tokens.add(str);
			return true;
		} else
			return false;

	}

	public void tokenize(String currentLine) throws Exception {

		String str = currentLine;
		str = str.trim();
		if (str.startsWith("\"")) {
			int j = 2;
			String substr = "";
			if (j <= str.length())
				substr = str.substring(0, j);

			while (!substr.endsWith("\"")) {
				j++;
				substr = str.substring(0, j);
			}
			if (isString(substr)) {
				{
					tokenize(str.substring(j, str.length()));
					return;
				}
			}
		}

		if (!isReservedWord(str) && !isSymbol(str) && !isString(str) && !isNumber(str)) {
			if (str.matches("([A-Z]|[a-z]|_)([A-Z]|[a-z]|[0-9]|_)*")) {
				if (str.equals("main")) {
					tokens.add(str);
				} else {
					tokens.add("cs512" + str);
				}
			} else {

				if (Character.isDigit(str.charAt(0))) {

					int tempCount = 0;
					while (Character.isDigit(str.charAt(tempCount)) && tempCount <= str.length()) {
						tempCount++;
					}
					tokens.add(str.substring(0, tempCount));
					str = str.substring(tempCount, str.length());
					tokenize(str);
					return;
				}

				if (str.startsWith("==") || str.startsWith("!=") || str.startsWith(">=") || str.startsWith("<=")
						|| str.startsWith("&&") || str.startsWith("||")) {
					tokens.add(str.substring(0, 2));
					str = str.substring(2, str.length());
					tokenize(str);
					return;
				} else if (str.startsWith("=") || str.startsWith(">") || str.startsWith("<")) {
					tokens.add(str.substring(0, 1));
					str = str.substring(1, str.length());
					tokenize(str);
					return;
				}

				int i = 1;
				int flag = -1;
				String temp = "";

				if (str.length() > 0) {
					temp = str.substring(0, i);
				}

				while (temp.matches("([A-Z]|[a-z]|_)([A-Z]|[a-z]|[0-9]|_)*")) {
					i++;
					flag = 0;
					if (i <= str.length())
						temp = str.substring(0, i);

				}

				if (i == 1 && str.length() == 1) {
					inValidFirstStringEncountered = str;
					throw new Exception();
				}

				if (flag == -1 && i <= str.length()) {
					tokenize(str.substring(0, i));
					tokenize(str.substring(i, str.length()));
				}

				if (flag == 0 && i - 1 <= str.length()) {

					tokenize(str.substring(0, i - 1));
					tokenize(str.substring(i - 1, str.length()));
				}
			}
		}

	}

	public void readAndTokenize(String inputFile) throws Exception {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String currentLine = "";
			while ((currentLine = br.readLine()) != null) {
				currentLine = currentLine.trim();
				if (!isMetaStatement(currentLine)) {
					if (!isLineEmpty(currentLine)) {
						tokenize(currentLine);

					}
				}

			}
			br.close();
		} catch (IOException e) {
			System.out.println("File Not Found");
		}

	}

	public List<String> getAllTokens(String[] args){
		if (args.length != 1) {
			System.out.println("Incorrect Input Format: java Scanner <filename>");
			return null;
		}

		String inputFile = args[0];
		int flag = 1;
		try {
			readAndTokenize(inputFile);
		} catch (Exception e) {
			flag = 0;
			System.out.println(
					"Error in Scanner: Invalid token in source file: " + inValidFirstStringEncountered);
			return null;
		}
		
		return tokens;
		
	}
	
	
	public static void main(String[] args) {
		Scanner obj = new Scanner();
		obj.getAllTokens(args);
	}
}
