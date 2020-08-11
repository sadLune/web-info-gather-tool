package br.usp.poli.wigt.selenium.ids;

public class ScopusSearchIds {
	
	public static final String LINK_DOCUMENT_TITLE_CSS = ".ddmDocTitle";

	public static final String LINKS_EMAIL_AUTHOR_XP = "//a[@id = 'absAuthEmail']";

	public static final String SPAN_NAME_AUTHOR_WITH_EMAIL_XP = "//a[@id = 'absAuthEmail']/parent::li//span[@class='anchorText']";
	
	public static final String LINK_NEXT_PAGE_XP = "//a[@title = 'Next page']";
	
	public static final String SPAN_RESULTS_COUNT_CSS = ".resultsCount";
	
	public static final String BTN_CLOSE_SIGNIN_XP = "//div[@id = 'pendo-guide-container']//button[contains(text(),'×')]";
	
	public static final String SELECT_NUM_RESULTS_ID = "resultsPerPage-button";
	
	public static final String OPT_200_NUM_RESULTS_XP = "//ul[@id = 'resultsPerPage-menu']//li//div[contains(text(),'200')]";
	
	public static final String BTN_NEXT_CLASS = "nextLink";
}
