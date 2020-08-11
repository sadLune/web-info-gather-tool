package br.usp.poli.wigt.selenium.ids;

public enum ExampleIds {
	
	LINK_DOCUMENT_TITLE_CLASS("ddmDocTitle"),

	LINKS_EMAIL_AUTHOR_ID("absAuthEmail"),

	SPAN_NAME_AUTHOR_CSS("#authorlist span.anchorText"),
	
	TXT_SAP("codigosap"), // name
	
	TXT_RAZAO("razaosocial"), // name
	
	TXT_CUST("custcode"), // name
	
	SLC_MODAL("modalidade"), // name
	
	SLC_INT("integrador"), // name
	
	SLC_MODULO("modulo"), // name
	
	TXT_CBI("codCBI"), //name
	
	TXT_ORIGEM("codDistribuidor"), //name
	
	TXT_CBA("codCBA"), //name
	
	CHK_ATIVO("flagativo"), //name

	BTN_CRIA_SAP("image_oksap"), // name
	
	TABELA("divDaTabela"), // id
	
	TR_QIWI("//tbody[@id=\"tbodyDoDetalhe\"]" + 
			"//tr[" +
			".//td[.//input[@name=\"desc_integrador\"]] and " +
			".//td[contains(text(), \"01001\")] and " +
			".//td[contains(text(), \"03092\")] and " +
			".//td[contains(text(), \"IS_GWCEL\")] and " +
			".//td[contains(text(), \"3092\")]" +
			"]"),
	
	BTN_ADD_INTEGRADOR("//td[" +
			"@class=\"tabdin_cabecalho\" and " +
			"./img[@title=\"incluir\"]" +
			"]"),
	
	BTN_CRIA_SEL("image_ok"), // name

	BTN_CANCEL_SEL("Image_cancel"), // name

	ALERT_NOME_VAZIO("//li[text()=\"Preencha o campo Nome\"]"),
	
	ALERT_NOME_REPETIDO("//li[text()=\"Erro Nome de Distribuidor já cadastrado!\"]"),
	
	ALERT_RAZAO("//li[text()=\"Preencha o campo Razão Social\"]"),
	
	ALERT_CODIGO("//li[text()=\"Preencha o campo Cust Code\"]"),
	
	ALERT_MODALIDADE("//li[text()=\"Preencha o campo Modalidade\"]"),
	
	ALERT_INTEGRADOR("//li[text()=\"Selecione pelo menos 1 Integrador\"]"),
	
	MSG_CREATED("//li[text()=\"Distribuidor inserido com sucesso!\"]"),
	
	MSG_UPDATED("//li[text()=\"Distribuidor alterado com sucesso!\"]")
	;
	
	private String selector;

	ExampleIds(String selector) {
		this.selector = selector;
	}

	public String getSelector() {
		return selector;
	}

}
