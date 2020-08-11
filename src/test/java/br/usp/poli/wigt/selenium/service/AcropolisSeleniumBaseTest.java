package br.usp.poli.wigt.selenium.service;

import org.junit.After;
import org.junit.Before;

import br.usp.poli.wigt.selenium.Messages;
import br.usp.poli.wigt.selenium.SeleniumHelper;

public class AcropolisSeleniumBaseTest {

	protected SeleniumHelper selenium;
	
	protected final String URL = getMessage("url");

	@Before
	public void setUpBase() throws Exception {
		selenium = new SeleniumHelper(URL);
		selenium.createDriverChrome();
	}
	
	@After
	public void tearDownBase() {
		selenium.quitDriver();
	}

	public SeleniumHelper getSelenium() {
		return selenium;
	}

	public String getMessage(String id) {
		return Messages.getString(
				AcropolisSeleniumBaseTest.class.getPackage().getName(), "messages",
				id);
	}

}
