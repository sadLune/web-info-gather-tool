package br.usp.poli.wigt.service.ieee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.usp.poli.wigt.selenium.pages.ScopusSearchPage;
import br.usp.poli.wigt.selenium.service.AcropolisSeleniumBaseTest;

public class ScopusSearchTest extends AcropolisSeleniumBaseTest {
	
	private ScopusSearchPage rel;
	
	private final int DEFAULT_PAGE_SIZE = 20;
	
	@Before
	public void customSetUpAndStartSelenium() {	
		rel = new ScopusSearchPage(selenium);
		selenium.start();
	}
	
	@Test
	public void listaDadosPesquisadores() {
		
		Map<String, String> autores = new HashMap<>();
		
		rel.fecharModalSignIn();
		
//		int qtDocumentosPesquisados = rel.getQtDocumentosPesquisados();
//		System.out.println("Quantidade total: " + qtDocumentosPesquisados);
//		
		int pageSize = DEFAULT_PAGE_SIZE;
//		if(qtDocumentosPesquisados < 100) {
//			rel.seleciona200ResultadosPorPagina();
//			pageSize = 200;
//		}
		
		//fazer page 6
		int index = 0;		
		for (int contador = 0; contador < 42; contador++) {
			
			try {
				if(contador != 0 && contador % pageSize == 0) {
					index = 0;
					rel.clicaProximaPagina();
				}
				
				rel.selecionaDocumentoPesquisado(index);
				adicionaAutores(autores, index);
				selenium.returnToPreviousPage();
			
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Falha na obtenção dos dados do artigo " +
						index + " " + rel.getNomeDocumentosPesquisados(index));
			}
			
			index++;
		}
		
		Assert.assertTrue(true);
	}
	
	private void adicionaAutores(Map<String, String> autores, int index) {
		
		List<String> nomes = rel.listaNomesAutores();
		if(nomes.isEmpty()) {
			return;
		}
		List<String> emails = rel.listaEmailsAutores();
		if(nomes.size() != emails.size()) {
			System.out.println("Quantidade de nomes diferente da quantidade de emails obtidos no item: " +
					index + " " + rel.getNomeDocumentosPesquisados(index));
			return;
		}
		
		for (int i = 0; i < emails.size(); i++) {
			String nome = nomes.get(i);
			String email = emails.get(i);
			if(autores.containsKey(email)) {
				continue;
			}
			autores.put(email, nome);
			System.out.println(nome + "," + email);
		}
	}
}
