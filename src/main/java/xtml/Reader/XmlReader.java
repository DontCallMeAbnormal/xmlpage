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
	 * ��ȡxml�ļ� ����Duoment����
	 * ����¼���ļ�·��
	 */
	public Document read(String fileName) throws DocumentException {
		// TODO Auto-generated method stub
		filePath=fileName;
		return super.read(XmlReader.class.getResourceAsStream(fileName));
		
	}
	
	
	/**
	 * 
	 * ��ȡ��ʷ�����һ�ε�xml�ļ� ����Duoment����
	 * �ļ�·������read(String fileName)�������,
	 * ����������Ҫ����һ��read(String fileName)����
	 * �÷������������õ��ļ�·��
	 * 
	 * @return ����Document����
	 * 
	 */
	public Document read() {
		// TODO Auto-generated method stub
		try {
			if(filePath==null) {
				throw new RuntimeException("�����ٵ���read(String fileName)����һ��,�÷�����Ĭ��ʹ���ϴε��ù���·��");
			}
			return super.read(XmlReader.class.getResourceAsStream(filePath));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
