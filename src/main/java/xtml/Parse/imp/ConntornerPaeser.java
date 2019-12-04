package xtml.Parse.imp;

import java.util.List;

import org.dom4j.Element;
import xtml.Parse.ConntornerPaeserInterface;
import xtml.core.page.Conntorner;
import xtml.core.page.Parm;

/**
 * ����xml�ĵ��е�Conntorner
 * 
 * @author Administrator
 *
 */
public class ConntornerPaeser implements ConntornerPaeserInterface{
	
	
	/**
	 * ����Conntorner��ǩ��Element����,������һ��javaBean��Conntorner��
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
	 * ͨ����Ԫ��,��conntorner��id �����Ҳ�������
	 */
	@Override
	public Conntorner getContorner(Element rootElement, String id) {
		// TODO Auto-generated method stub
		return  getContorner(rootElement.element("Conntorneres").elementByID(id));
	}

}
