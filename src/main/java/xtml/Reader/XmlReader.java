package xtml.Reader;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XmlReader extends SAXReader{
	
	private static XmlReader xmlReader=null;
	private static String filePath=null;
	
	private XmlReader() {}
	
	public static XmlReader getInstance() {
		if(xmlReader==null) {
			xmlReader =  new XmlReader();
		}
		return xmlReader;
	}
	

	/**
	 * 
	 * 读取xml文件 返回Duoment对象
	 * 并记录该文件路径
	 */
	public Document read(String fileName) throws DocumentException {
		// TODO Auto-generated method stub
		filePath=fileName;
		return super.read(XmlReader.class.getResourceAsStream(fileName));
		
	}
	
	
	/**
	 * 
	 * 读取历史上最近一次的xml文件 返回Duoment对象
	 * 文件路径是由read(String fileName)保存过的,
	 * 所以至少需要调用一次read(String fileName)方法
	 * 该方法才能正常得到文件路径
	 * 
	 * @return 返回Document对象
	 * 
	 */
	public Document read() {
		// TODO Auto-generated method stub
		try {
			if(filePath==null) {
				throw new RuntimeException("请至少调用read(String fileName)方法一次,该方法是默认使用上次调用过的路径");
			}
			return super.read(XmlReader.class.getResourceAsStream(filePath));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
