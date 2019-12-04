package xtml.Parse;
import org.dom4j.Element;
import xtml.core.page.Conntorner;

/**
 * 根据解析某个单独节点
 * @author Administrator
 *
 */
public interface ConntornerPaeserInterface {
	
	public Conntorner getContorner(Element conntorner);
	public Conntorner getContorner(Element rootElement,String id);
	
	
}
