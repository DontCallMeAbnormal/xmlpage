package xtml.Writer;

import xtml.core.page.Div;
import xtml.core.page.Page;


public interface HtmlWriterInterface {
	
	String UTF8="utf-8";
	String GBK="gbk";
	String GB2312="gb2312";
	String GB18030="gb18030";
	
	void createHtml(String filePath, String charser,Page page);
	String writerHtml(Div div,Page page) throws ClassNotFoundException;//�÷����᷵��һ��String����,�洢divת������Ϣ
	
	
}
