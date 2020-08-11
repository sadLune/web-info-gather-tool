package br.usp.poli.wigt.selenium.pages;

import static br.usp.poli.wigt.selenium.ids.ScopusSearchIds.BTN_CLOSE_SIGNIN_XP;
import static br.usp.poli.wigt.selenium.ids.ScopusSearchIds.LINKS_EMAIL_AUTHOR_XP;
import static br.usp.poli.wigt.selenium.ids.ScopusSearchIds.LINK_DOCUMENT_TITLE_CSS;
import static br.usp.poli.wigt.selenium.ids.ScopusSearchIds.LINK_NEXT_PAGE_XP;
import static br.usp.poli.wigt.selenium.ids.ScopusSearchIds.OPT_200_NUM_RESULTS_XP;
import static br.usp.poli.wigt.selenium.ids.ScopusSearchIds.SELECT_NUM_RESULTS_ID;
import static br.usp.poli.wigt.selenium.ids.ScopusSearchIds.SPAN_NAME_AUTHOR_WITH_EMAIL_XP;
import static br.usp.poli.wigt.selenium.ids.ScopusSearchIds.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import br.usp.poli.wigt.selenium.SeleniumHelper;

public class ScopusSearchPage {
	
	private SeleniumHelper selenium;
	
	public ScopusSearchPage(SeleniumHelper selenium) {
		this.selenium = selenium;
	}
	
	public void fecharModalSignIn() {
		try {
			WebElement element = selenium.findByXPath(BTN_CLOSE_SIGNIN_XP);
			selenium.waitElementToBeClickable(element);
			selenium.click(element);
		} catch (NoSuchElementException e) {
			//Se não estiver presente, só ignora o modal
		}
	}
	
	public int getQtDocumentosPesquisados() {
		WebElement element = selenium.findByCssSelector(SPAN_RESULTS_COUNT_CSS);
		return Integer.valueOf(element.getAttribute("innerHTML").trim());
	}
	
	public String getNomeDocumentosPesquisados(int index) {
		try {
			WebElement element = selenium.findByCssSelector(LINK_DOCUMENT_TITLE_CSS, index);
			return element.getAttribute("innerHTML");
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public void selecionaDocumentoPesquisado(int index) {
		WebElement element = selenium.findByCssSelector(LINK_DOCUMENT_TITLE_CSS, index);
		selenium.click(element);
		selenium.findElementsByXPath(LINKS_EMAIL_AUTHOR_XP);
	}
	
	public List<String> listaNomesAutores() {
		try {
			List<WebElement> elements = selenium.findElementsByXPath(SPAN_NAME_AUTHOR_WITH_EMAIL_XP);
			return elements.stream().map(element -> element.getAttribute("innerHTML").replace(",", "").split("<sup>")[0]).collect(Collectors.toList());
		} catch (NoSuchElementException e) {
			return new ArrayList<>();
		}
	}
	
	public List<String> listaEmailsAutores() {
		try {
			List<WebElement> elements = selenium.findElementsByXPath(LINKS_EMAIL_AUTHOR_XP);
			return elements.stream().map(element -> element.getAttribute("href").replace("mailto:", "").toLowerCase()).collect(Collectors.toList());
		} catch (NoSuchElementException e) {
			return new ArrayList<>();
		}
	}
	
	public void seleciona200ResultadosPorPagina() {
		WebElement select = selenium.find(SELECT_NUM_RESULTS_ID);
		selenium.click(select);
		WebElement option = selenium.findByXPath(OPT_200_NUM_RESULTS_XP);
		selenium.click(option);
	}
	
	public void clicaProximaPagina() {
		WebElement element = selenium.findByXPath(LINK_NEXT_PAGE_XP);
		selenium.click(element);
	}

	public void clicaProximoDocumento() {
		WebElement element = selenium.findByClassName(BTN_NEXT_CLASS);
		selenium.click(element);
	}
}
