package br.usp.poli.wigt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeitorDeArquivo {

	public static List<String> readFiles(String path, List<String> fileNames) {
		List<String> lines = new ArrayList<>();
		fileNames.forEach(name -> {
			lines.addAll(readFile(path + name));
		});
		return lines;
	}
	
	public static String readFileTextUnformatted(String path, Map<String, String> dictionary) {
		List<String> linhas = readFile(path);
		StringBuilder sb = new StringBuilder();
		linhas.forEach(linha -> {
			sb.append(linha);
		});
		return replaceDictionary(sb.toString(), dictionary);
	}

	public static String readFileTextUnformatted(String path) {
		List<String> linhas = readFile(path);
		StringBuilder sb = new StringBuilder();
		linhas.forEach(linha -> {
			sb.append(linha);
		});
		return sb.toString();
	}

	public static List<String> readFile(String path) {
		List<String> linhas = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			while (br.ready()) {
				String linha = br.readLine();
				linhas.add(linha);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return linhas;
	}
	
	private static String replaceDictionary(String text, Map<String, String> dictionary) {
		for (String key : dictionary.keySet()) {
			text = text.replace("{" + key + "}", dictionary.get(key));
		}
		return text;
	}

}
