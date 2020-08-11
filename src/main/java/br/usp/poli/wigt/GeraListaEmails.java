package br.usp.poli.wigt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeraListaEmails {

	final static String PATH = "src/main/resources/";
	final static String OS_FILE_PATH = PATH + "emails-scopus.csv";
	final static List<String> IS_FILE_NAMES = List.of("Distribuição - RE.csv", "Distribuição - SE Conference.csv",
			"Distribuição - RE Conference 1.csv", "Distribuição - RE Conference 2.csv", "Distribuição - REW.csv",
			"Distribuição - Model-Driven RE Workshop.csv");

	public static void main(String[] Args) {

		Map<String, String> autores = parseAutores();
		printAutores(autores);
	}
	
	public static void filtraAutores(List<String> emails) {

		List<String> linhasFiltradas = new ArrayList<>();
		List<String> linhas = LeitorDeArquivo.readFile(OS_FILE_PATH);

		linhas.forEach(linha -> {
			String email = linha.split(",")[1];

			if (!emails.contains(email)) {
				linhasFiltradas.add(linha + "\n");
			}
		});

		EscritorDeArquivo.escreveArquivoTexto(OS_FILE_PATH, linhasFiltradas);
	}

	public static Map<String, String> parseAutores() {

		Map<String, String> autores = new HashMap<>();
		List<String> nomesRepetidos = new ArrayList<>();
		List<String> linhas = LeitorDeArquivo.readFiles(PATH, IS_FILE_NAMES);
		List<String> linhasFiltradas = new ArrayList<>();

		//System.out.println("\nNomes repetidos:");

		linhas.forEach(linha -> {
			String[] autor = linha.split(",");
			String nome = autor[0];
			String email = autor[1];

			if (!autores.containsKey(email)) {
				linhasFiltradas.add(linha + "\n");

				if (autores.containsValue(nome)) {
					nomesRepetidos.add(nome);
//    				System.out.println(nome);
				}
			}

			autores.put(email, nome.split(" ")[0]);
		});

		System.out.println(nomesRepetidos.size());

		EscritorDeArquivo.escreveArquivoTexto(OS_FILE_PATH, linhasFiltradas);
		
		System.out.println("\nTotal: " + autores.size() + "\n");

		return autores;
	}
	
	public static Map<String, String> parseAutores(int offset, int size) {
		
//		return new HashMap<String, String>() {{
//			put("larissa95.amaral@gmail.com", "Amaral");
//		}};

		Map<String, String> autores = new HashMap<>();
		List<String> linhas = LeitorDeArquivo.readFile(OS_FILE_PATH);

		for(int i = offset; i < linhas.size() && i < offset+size; i++) {
			String linha = linhas.get(i);
			
			String[] autor = linha.split(",");
			StringBuilder nome = new StringBuilder();
			for (String split : autor[0].split(" ")) {
				if(split.contains(".")) {
					break;
				}
				nome.append(" ").append(split);
			}
			String email = autor[1];

			autores.put(email, nome.toString());
		}

		System.out.println("\nTotal: " + autores.size() + "\n");

		return autores;
	}

	public static void printAutores(Map<String, String> autores) {

		System.out.println("\nGrupos de 500 emails únicos:");

		int count = 0;
		StringBuilder sb = new StringBuilder();
		for (String email : autores.keySet()) {
			if (sb.length() != 0) {
				sb.append(";");
			}
			sb.append(email);

			count++;
			if (count == 500) {
				System.out.println(sb.toString());
				count = 0;
				sb = new StringBuilder();
			}
		}

		System.out.println(sb.toString());
	}

}
