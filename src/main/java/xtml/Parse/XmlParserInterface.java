package xtml.Parse;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import xtml.Reader.XmlReader;
import xtml.core.layout.XmlAttributeEnum;
import xtml.core.page.Page;

public interface XmlParserInterface {
	XmlAttributeEnum xmlAttr= new XmlAttributeEnum();
	XmlReader reader = XmlReader.getInstance();
	//����Dom����,����Page�б�
	List<Page> initPages(String fileName) throws DocumentException;



}
