

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;
import xtml.Parse.imp.ConntornerPaeser;
import xtml.Parse.imp.XmlParserPage;
import xtml.Reader.XmlReader;
import xtml.Writer.HtmlWriterInterface;
import xtml.Writer.imp.HtmlWriter;
import xtml.core.page.Conntorner;
import xtml.core.page.Div;
import xtml.core.page.Page;
import xtml.core.page.Parm;
import xtml.core.page.Tag;

public class TestXmlReader {
		
	
	@Test
	public void testXmlReader() throws Exception {
		
		XmlReader reader = XmlReader.getInstance();
		Document dom = reader.read("/pageOption.xml");
		Element rootElement = dom.getRootElement();
//		List<Element> elements = rootElement.elements();
//		for (Element element : elements) {
//			System.out.println(element.getName()+"  "+element.getText());
//		}
		
		Element element = rootElement.element("Conntorneres").elementByID("login");
		
	}
	
	
	
	
	
	@Test
	public void testXmlParserDiv() throws Exception {
		XmlParserPage pageParser = new XmlParserPage();
		List<Page> parsePage = pageParser.initPages("/pageOption.xml");
		
		
		HtmlWriter hw=new HtmlWriter();
		for (Page page : parsePage) {
			hw.createHtml(page.getPagePath(), HtmlWriterInterface.GB18030, page);
		}
		
		
	}
	
	
	
	
	
	@Test
	public void testTag() throws Exception {
		Tag tag=new Tag();
		tag.setTagName("div");
		tag.setTagId("div1");
		tag.setTagOtherTag("ÄãºÃ");
		tag.addAttrMap("onclik", "init()");
		tag.addAttrMap("id", "21");
		
		System.out.println(tag);
	}
	
	
	
	@Test
	public void testConntorParser() throws Exception {
		XmlReader reader = XmlReader.getInstance();
		Document dom = reader.read("/pageOption.xml");
		Element rootElement = dom.getRootElement();
		ConntornerPaeser cp=new ConntornerPaeser();
		Conntorner contorner = cp.getContorner(rootElement, "login");
		System.out.println(contorner.getId());
		System.out.println(contorner.getUrl());
		for (Parm parm : contorner.getParms()) {
			System.out.println(parm.getParmName()+"|"+parm.getParmType());
		}
		
	}
	
	@Test
	public void testDiv() throws Exception {
		Div div=new Div();
		div.setId("index");
		div.setLayout("center");
		div.addAtts("id", "haha");
		System.out.println(div);
	}
	
	@Test
	public void testInstanceTagWriter() throws Exception {
		Class<?> tw=Class.forName("xtml.Writer.imp.DivTagWriter");
		System.out.println(tw.newInstance());
	}
	
	
}
	

