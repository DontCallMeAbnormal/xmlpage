package xtml.Parse;
import org.dom4j.Element;
import xtml.core.page.Conntorner;

/**
 * ���ݽ���ĳ�������ڵ�
 * @author Administrator
 *
 */
public interface ConntornerPaeserInterface {
	
	public Conntorner getContorner(Element conntorner);
	public Conntorner getContorner(Element rootElement,String id);
	
	
}
