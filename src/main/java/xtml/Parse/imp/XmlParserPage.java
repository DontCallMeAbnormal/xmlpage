package xtml.Parse.imp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import xtml.Parse.XmlParserInterface;
import xtml.core.layout.Layout;
import xtml.core.page.Div;
import xtml.core.page.Page;


public class XmlParserPage implements XmlParserInterface{
	
	
	private List<Page> pages;
	
	public List<Page> parsePage(Element node){
		List<Page> listPage=new ArrayList<Page>();	
		List<Div> divs=parseDiv(node);
		for (Div div : divs) {
			listPage.add(new Page(div));
		}
		List<Element> elements = node.elements();
		
		
		for (Element element : elements) {
			String textId=element.attributeValue(xmlAttr.ID);
			for (Page page: listPage) {
				if(textId!=null && textId.equals(page.getThisDiv().getId())) {
					page.setTitle(element.attributeValue(xmlAttr.TITLE));
					page.setPagePath(element.attributeValue(xmlAttr.PATH));
				}
			}
		}
		
		return listPage;
	}
	
	
	
	//�����ڵ� ����div�б�
	public List<Div> parseDiv(Element node){
		List<Element> elements = node.elements();
		List<Div> listDiv=new ArrayList<Div>();
		for (Element element : elements) {
			//������page��ǩ������
			if(!element.getName().equals("Conntorneres")) {
				Div div=new Div();
				div.setDivs(parseDiv(element));
				setDivId(div,element);//����id
				setDivType(div, element);//����type
				setDivLayout(div,element);//����layout
				setDivAttr(div,element);//�������Զ��б�
				listDiv.add(div);
			}
			
		}
		return listDiv;
	}
	
	
	/**
	 * ����div��ID,���ݽڵ�
	 * @param Div div,Element element
	 * @return
	 */
	private void setDivId(Div div,Element element) {
		div.setId(element.attributeValue(xmlAttr.ID));
	}
	
	
	/**
	 * ����div��divType,���ݽڵ�
	 * @param Div div,Element element
	 * @return
	 */
	private void setDivType(Div div,Element element) {
		div.setDivType(element.getName());
	}
	
	
	
	/**
	 * 
	 * ����div ��layout����
	 * @param Div div,Element element
	 * @return
	 */
	private void setDivLayout(Div div,Element element) {
		if(element.attributeValue(xmlAttr.LAYOUT)==null || element.attributeValue(xmlAttr.LAYOUT).equals("null")) {
			//layout ����Ϊ�� ����������Ϊ defaule
			div.setLayout(Layout.DEFAULT);
			
		}else {
			//����layout����Ϊxml������   String����
			div.setLayout(element.attributeValue(xmlAttr.LAYOUT));
			
		}
	}
	
	
	

	/**
	 * 
	 * ����div ����������
	 * @param Div div,Element element
	 * @return
	 */
	private void setDivAttr(Div div,Element element) {
		List<Attribute> attributes = element.attributes();
		HashMap<String,String> attrs=new HashMap<String,String>();
		for (Attribute attribute : attributes) {
			div.addAtts(attribute.getName(), attribute.getValue());
		}
		
	}
	
	
	
	
	
	
	
	//����ĸ��дת��
	public String upperCase(String str) {  
        char[] ch = str.toCharArray();  
        if (ch[0] >= 'a' && ch[0] <= 'z') {  
            ch[0] = (char) (ch[0] - 32);  
        }  
        return new String(ch);  
    }



	/**
	 * ��ʼ��Page�б� 
	 * 
	 * XmlReader reader	  //���ڶ�ȡ��XmlReader��
	 * String 	 fileName //�ļ���
	 * 
	 * return Page����
	 */
	@Override
	public List<Page> initPages(String fileName) throws DocumentException {
		// TODO Auto-generated method stub
		Document document = reader.read(fileName);
		Element rootElement = document.getRootElement();
		return parsePage(rootElement);
	}

}
