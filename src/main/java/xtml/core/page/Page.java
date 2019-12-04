package xtml.core.page;

public class Page{
	private String title;
	private Div thisDiv;
	private String pagePath;
	private String jsText;
	private String cssText;
	public String getJsText() {
		return jsText;
	}
	public void setJsText(String jsText) {
		this.jsText = jsText;
	}
	public String getCssText() {
		return cssText;
	}
	public void setCssText(String cssText) {
		this.cssText = cssText;
	}
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Div getThisDiv() {
		return thisDiv;
	}
	public void setThisDiv(Div thisDiv) {
		this.thisDiv = thisDiv;
	}


	@Override
	public String toString() {
		return "{\"title\":\"" + title + "\", \"thisDiv\":\"" + thisDiv + "\", \"pagePath\":\"" + pagePath + "\"}";
	}
	public Page() {

	}
	public Page(Div thisDiv) {
		super();
		this.thisDiv = thisDiv;
		this.thisDiv.setDivType("div");
	}
	
	public void appendJsText(String jsText) {
		if(getJsText()==null || getJsText().equals("null")) {
			setJsText(jsText);
		}else {
			this.jsText=getJsText()+jsText;
		}
		
	}
}
