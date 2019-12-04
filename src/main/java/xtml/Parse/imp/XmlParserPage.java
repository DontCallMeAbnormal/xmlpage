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
	
	
	
	//遍历节点 生成div列表
	public List<Div> parseDiv(Element node){
		List<Element> elements = node.elements();
		List<Div> listDiv=new ArrayList<Div>();
		for (Element element : elements) {
			//仅解析page标签的内容
			if(!element.getName().equals("Conntorneres")) {
				Div div=new Div();
				div.setDivs(parseDiv(element));
				setDivId(div,element);//设置id
				setDivType(div, element);//设置type
				setDivLayout(div,element);//设置layout
				setDivAttr(div,element);//设置属性对列表
				listDiv.add(div);
			}
			
		}
		return listDiv;
	}
	
	
	/**
	 * 设置div的ID,根据节点
	 * @param Div div,Element element
	 * @return
	 */
	private void setDivId(Div div,Element element) {
		div.setId(element.attributeValue(xmlAttr.ID));
	}
	
	
	/**
	 * 设置div的divType,根据节点
	 * @param Div div,Element element
	 * @return
	 */
	private void setDivType(Div div,Element element) {
		div.setDivType(element.getName());
	}
	
	
	
	/**
	 * 
	 * 设置div 的layout属性
	 * @param Div div,Element element
	 * @return
	 */
	private void setDivLayout(Div div,Element element) {
		if(element.attributeValue(xmlAttr.LAYOUT)==null || element.attributeValue(xmlAttr.LAYOUT).equals("null")) {
			//layout 属性为空 则设置属性为 defaule
			div.setLayout(Layout.DEFAULT);
			
		}else {
			//设置layout属性为xml的配置   String类型
			div.setLayout(element.attributeValue(xmlAttr.LAYOUT));
			
		}
	}
	
	
	

	/**
	 * 
	 * 设置div 的其他属性
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
	
	
	
	
	
	
	
	//首字母大写转换
	public String upperCase(String str) {  
        char[] ch = str.toCharArray();  
        if (ch[0] >= 'a' && ch[0] <= 'z') {  
            ch[0] = (char) (ch[0] - 32);  
        }  
        return new String(ch);  
    }



	/**
	 * 初始化Page列表 
	 * 
	 * XmlReader reader	  //用于读取的XmlReader类
	 * String 	 fileName //文件名
	 * 
	 * return Page集合
	 */
	@Override
	public List<Page> initPages(String fileName) throws DocumentException {
		// TODO Auto-generated method stub
		Document document = reader.read(fileName);
		Element rootElement = document.getRootElement();
		return parsePage(rootElement);
	}

}
