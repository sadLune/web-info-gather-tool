package br.usp.poli.wigt.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelper {

	private String urlDefault;

	private WebDriver driver;
	private WebDriverWait wait;

	public SeleniumHelper(String urlDefault) {
		this.urlDefault = urlDefault;
	}

	public WebElement find(String id) {
		return driver.findElement(getBy(id));
	}
	
	public WebElement findByName(String name) {
		return  driver.findElement(By.name(name));
	}
	
	public WebElement findByClassName(String className) {
		return  driver.findElement(By.className(className));
	}

	public WebElement findLinkText(String text) {
		return findByXPath("//*[contains(text(),'" + text + "')]");
	}
	
	public WebElement findByText(String text) {
		return driver.findElement(By.linkText(text));
	}
	
	public WebElement findByCssSelector(String text) {
		return driver.findElement(By.cssSelector(text));
	}
	
	public WebElement findByCssSelector(String text, int index) {
		List<WebElement> elements = driver.findElements(By.cssSelector(text));
		if(index >= elements.size()) {
			return null;
		}
		return elements.get(index);
	}
	
	public WebElement findByXPath(String xPath) {
		return  driver.findElement(By.xpath(xPath));
	}

	public List<WebElement> findElements(String id) {
		return driver.findElements(getBy(id));
	}
	
	public List<WebElement> findElementsByCssSelector(String text) {
		return driver.findElements(By.cssSelector(text));
	}
	
	public List<WebElement> findElementsByXPath(String xPath) {
		return  driver.findElements(By.xpath(xPath));
	}

	public WebElement waitElementToBeClickable(String id) {
		return wait.until(ExpectedConditions.elementToBeClickable(getBy(id)));
	}
	
	public WebElement waitElementToBeClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}	
	
	public WebElement waitElementToBeClickableByCssSelector(String text) {
		return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(text)));
	}
	
	public Boolean waitElementToBeNotClickable(WebElement element) {
		return wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
	}

	public WebElement waitElementPresence(String id) {
		return wait.until(ExpectedConditions
				.presenceOfElementLocated(getBy(id)));
	}
	
	public WebElement waitElementPresenceByCssSelector(String selector) {
		return wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector(selector)));
	}
	
	public WebElement waitElementPresenceByXPath(String xpath) {
		return wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath(xpath)));
	}
	
	public WebElement waitElementPresenceByClassName(String className) {
		return wait.until(ExpectedConditions
				.presenceOfElementLocated(By.className(className)));
	}
	
	public WebElement waitElementPresenceByName(String name) {
		return wait.until(ExpectedConditions
				.presenceOfElementLocated(By.name(name)));
	}
	
	public WebElement waitElementPresenceByLinkText(String text) {
		return wait.until(ExpectedConditions
				.presenceOfElementLocated(By.linkText(text)));
	}
	
	public Boolean waitElementNotPresence(String id) {
		return wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(getBy(id))));
	}
	
	public Boolean waitElementNotPresenceByXPath(String xpath) {
		return wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))));
	}
	
	public Alert waitAlertPresence() {
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void click(String id) {
		waitElementToBeClickable(id);
		executeScript(id, "arguments[0].click()");
	}
	
	public void click(WebElement element) {
		waitElementToBeClickable(element);
		element.click();
	}
	
	public void clickHref(String id) {
		driver.findElement(By.xpath("//a[contains(@href,'"+id+"')]")).click();
	}
	
	public void clickByCssSelector(String text) {
		waitElementToBeClickableByCssSelector(text);
		clickByCssSelectorNoWait(text);
	}
	
	public void clickByCssSelectorNoWait(String text) {
		executeScriptCssSelector(text, "arguments[0].click()");
	}
	
	public void clickByXPathNoWait(String text) {
		executeScriptXPath(text, "arguments[0].click()");
	}
	
	public void clickByJavaScript(String script) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript(script);
	}
	public WebElement waitElementVisibilityPresence(String id) {
		return wait.until(ExpectedConditions
				.visibilityOf(find(id)));
	}
	
	public void selectByText(String id, String... texts) {
		Select select = new Select(find(id));
		select(select, texts);
	}
	
	public void selectByTagName(String name, String... texts) {
		Select select = new Select(findByName(name));
		select(select, texts);
		
	}
	
	public void selectByXPath(String name, String... texts) {
		WebElement element = driver.findElement(By.xpath(name));
		Select select = new Select(element);
		select(select, texts);
	}
	
	public void select(Select select, String... texts) {
		if (select.isMultiple()) {
			select.deselectAll();
		}

		for (String text : texts) {
			select.selectByVisibleText(text);
		}
	}
	
	public void checkboxByName(String name) {
		driver.findElement(By.name(name)).click();
	}

	public void writeInTxt(String id, String text) {
		WebElement txt = find(id);
		writeInText(txt, text);	
	}
	
	public void writeInText(WebElement element, String text) {
		element.clear();
		waitTimeInMs(200);
		element.sendKeys(text);
	}
	
	public void writeInTxt(WebElement txt, String text) {
		txt.clear();
		txt.sendKeys(text);
	}

	public void executeScript(String idElement, String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script, find(idElement));
	}
	
	public void executeScriptCssSelector(String text, String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script, findByCssSelector(text));
	}
	
	public void scrollTo(String xpos,String ypos) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo("+xpos+","+ypos+");");
	}
	
	public void executeScriptXPath(String text, String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script, findByXPath(text));
	}	

	public void validateSelect(String idElement, List<String> values) {
		List<String> list = validateSelect(idElement);

		Collection<String> similar = new HashSet<String>(values);
		Collection<String> different = new HashSet<String>();
		different.addAll(values);
		different.addAll(list);

		similar.retainAll(list);
		different.removeAll(similar);

		if (different.size() > 0) {
			throw new UnsupportedOperationException("Componente: " + idElement
					+ " possui os elementos diferentes do esperado: "
					+ different);
		}

	}

	public List<String> validateSelect(String idElement) {
		Select sel = new Select(find(idElement));
		List<WebElement> options = sel.getOptions();
		List<String> list = new LinkedList<String>();

		for (WebElement el : options) {
			if (!StringUtils.isAllUpperCase(el.getText())) {
				list.add(el.getText());
			} else {
				throw new UnsupportedOperationException("Componente: "
						+ idElement + " possui elemento de ENUM: "
						+ el.getText());
			}
		}
		return list;
	}
	
	public void mouseOverElement(String locator) {
		Actions action = new Actions(driver);
		WebElement we = findLinkText(locator);
		action.moveToElement(we).build().perform();
		waitTimeInMs(500);
	}

	public void start() {
		driver.get(urlDefault);
	}

	public void quitDriver() {
		driver.quit();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getUrlDefault() {
		return urlDefault;
	}
	
	public void setUrlDefault(String urlDefault) {
		this.urlDefault = urlDefault;
	}

	public void navigateToUrl(String url) {
		driver.navigate().to(url);
	}
	
	private By getBy(String id) {
		String[] idParts = new String[0];
		if (id.contains("*")) {
			idParts = id.split("\\*");
		}
		if (idParts.length > 1) {
			return By.cssSelector("[id^='" + idParts[0] + "'][id$='" + idParts[1] + "']"); 
		}

		return By.id(id);
	}
	
	public void fechaModal(String id) {
		driver.findElement(By.xpath("//div[@id='"+id+"']//a[@class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all']")).click();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void createDriverFirefox() {
//		File resurceProfile = new File(this.getClass()
//				.getResource("/FirefoxProfile").getFile());
//
//		FirefoxProfile profile = new FirefoxProfile(resurceProfile);
//		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
//
//		driver = new FirefoxDriver(capabilities);
//		createWait();
	}

	public void createRemoteDriverFirefox() {
//		File resurceProfile = new File(this.getClass()
//				.getResource("/FirefoxProfile").getFile());
//
//		FirefoxProfile profile = new FirefoxProfile(resurceProfile);
//		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
//
//		// java -jar selenium-server-standalone-2.40.0.jar -role hub -port 4436
//
//		// java -jar selenium-server-standalone-2.40.0.jar -role node -hub
//		// http://192.168.52.184:4436/grid/register
//		try {
//			driver = new RemoteWebDriver(new URL(
//					"http://192.168.53.126:5555/wd/hub"), capabilities);
//
//			createWait();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
	}
	
	public void createDriverIE(String urlServer) {
		System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, urlDefault);
		capabilities.setJavascriptEnabled(true);
	    try {
			driver = new RemoteWebDriver( new URL(urlServer),capabilities);
		} 
	    catch(UnreachableBrowserException e) {
	    	throw new UnreachableBrowserException ("Verifique se o selenium server foi iniciado");
	    }
	    catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		PageFactory.initElements(driver, this);

		createWait();
	}

	public void createDriverChrome() {		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=pt-BR");
		driver = new ChromeDriver(options);
		driver.get("http://www.google.com");
		
		PageFactory.initElements(driver, this);

		createWait();
	}

	public void createRemoteDriverChrome() {
		try {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setJavascriptEnabled(true);

			driver = new RemoteWebDriver(new URL(
					"http://localhost:8088/sitefgw_1"), capabilities);
//					"http://192.168.52.184:5555/wd/hub"), capabilities);					
			driver.get("http://www.google.com");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		createWait();
	}

	public void waitTime(int seconds) {
		waitTimeInMs(seconds * 1000);
	}
	
	public void waitTimeInMs(int miliseconds) {
		synchronized (driver) {
			try {
				driver.wait(miliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	
	public void waitForElementClickable (String id,int time) {		
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }

	private void createWait() {
		wait = new WebDriverWait(driver, 20);
		wait.withTimeout(20, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getWait() {
		return wait;
	}
	
	public void pesquisarClick(String idPesquisar, String idToWaitFor) {
		click(idPesquisar);
		waitElementPresence(idToWaitFor);
	}
	
	public void clickMenuAndWaitPage(String menuId, String elementInsidePageId) {
		// Precisa ser feito dessa maneira devido ao submenu nao estar visivel
		executeScript(menuId, "arguments[0].click()");
		
		waitElementPresence(elementInsidePageId);
	}
	
	public void returnToPreviousPage() {
		((JavascriptExecutor) driver).executeScript("window.history.go(-1)");
	}

}
