package br.usp.poli.wigt.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

	private Messages() {
	}

	public static String getString(String pacote, String nomeArquivo, String key) {
		try {
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle(geraBundleName(pacote, nomeArquivo));
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static List<String> getAllValues(String pacote, String nomeArquivo) {
		ResourceBundle resourceBundle = ResourceBundle
				.getBundle(geraBundleName(pacote, nomeArquivo));
		
		List<String> allValues = new ArrayList<String>();
		
		resourceBundle.keySet().forEach(k -> {
			allValues.add(resourceBundle.getString(k));
		});
		
		return allValues;
	}

	private static String geraBundleName(String pacote, String nomeArquivo) {
		StringBuilder sb = new StringBuilder();
		sb.append(pacote).append(".").append(nomeArquivo);
		return sb.toString();
	}
	
}
