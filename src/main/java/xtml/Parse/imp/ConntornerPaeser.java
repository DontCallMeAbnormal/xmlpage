package xtml.Parse.imp;

import java.util.List;

import org.dom4j.Element;
import xtml.Parse.ConntornerPaeserInterface;
import xtml.core.page.Conntorner;
import xtml.core.page.Parm;

/**
 * 解析xml文档中的Conntorner
 * 
 * @author Administrator
 *
 */
public class ConntornerPaeser implements ConntornerPaeserInterface{
	
	
	/**
	 * 输入Conntorner标签的Element类型,来返回一个javaBean的Conntorner类
	 */
	@Override
	public Conntorner getContorner(Element conntorner) {
		// TODO Auto-generated method stub
		Conntorner coner=new Conntorner();
		coner.setId(conntorner.attributeValue("ID"));
		Element urlElement = conntorner.element("url");
		coner.setUrl(urlElement.getTextTrim());
		Element parmsElement = conntorner.element("parms");
		List<Element> parmElements = parmsElement.elements();
		for (Element parm : parmElements) {
			Parm p=new Parm();
			p.setParmType(parm.attributeValue("type"));
			p.setParmName(parm.getTextTrim());
			p.setPattern(parm.attributeValue("pattern"));
			
			
			coner.addParm(p);
		}
		
		return coner;
	}
	
	
	/**
	 * 通过根元素,与conntorner的id 来查找并解析它
	 */
	@Override
	public Conntorner getContorner(Element rootElement, String id) {
		// TODO Auto-generated method stub
		return  getContorner(rootElement.element("Conntorneres").elementByID(id));
	}

}
