package xtml.Writer.imp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.List;

import xtml.Writer.HtmlWriterInterface;
import xtml.Writer.TagWriter;
import xtml.core.page.Div;
import xtml.core.page.Page;
import xtml.core.page.Tag;

public class HtmlWriter implements HtmlWriterInterface{


	public void createHtml(Page page) {
		createHtml(page.getPagePath(), "utf-8", page);
	}
	
	
	@Override
	public void createHtml(String filePath,String charser,Page page) {
		if(page==null) {
			return;
		}
		
		 StringBuilder stringHtml = new StringBuilder();
	        //��ʼ���ļ�����
	        PrintStream printStream =null;
	        try{
	            //���ļ�
	             printStream = new PrintStream(new FileOutputStream(filePath));
	        }catch(FileNotFoundException e){
	            e.printStackTrace();
	        }
	        stringHtml.append("<!DOCTYPE html>\n<html><head>");
	        stringHtml.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset="+charser+"\">");
	        stringHtml.append("<title>"+page.getTitle()+"</title>\n");
	        stringHtml.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
	        
	        String link="<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\" />\n" + 
	        		" <script src=\"http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.9.1.js\"> </script>\n" + 
	        		"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>\n" + 
	        		"";
	        
	        stringHtml.append(link);
	        stringHtml.append("</head>");
	        stringHtml.append("\n<body>");
		
	        /*---------------���¶�div���� html����ת��------------------*/
	        
	       
	        try {
				stringHtml.append(writerHtml( page.getThisDiv(),page));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        
	        /*------------------------------------------------*/
	        stringHtml.append("</body>\r\n");
	        stringHtml.append("<script type=\"text/javascript\">\r\n");
	        if(page.getJsText()!=null && !page.getJsText().equals("null")) {
	        	stringHtml.append(page.getJsText());
	        }
	        
	        stringHtml.append("\r\n</script>");
	        stringHtml.append("</html>");
	        
	        try{
	            //��HTML�ļ�����д���ļ���
	        	String htmlText=new String(stringHtml.toString().getBytes(),charser);
	        	
	            printStream.println(htmlText);
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	}








	@Override
	public String writerHtml(Div div,Page page) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class<?> tw=Class.forName("xtml.Writer.imp."+upperCase(div.getDivType())+"TagWriter");
		Tag tag=null;
		try {
			TagWriter object = (TagWriter)tw.newInstance();//����divTypeʵ������Ӧ����д��
			
			tag=object.writerTag(div,page);	//����TagWriter�ӿڵ�writer���� ����tag
			
			List<Div> dis = div.getDivs();
			StringBuilder strTag=new StringBuilder();
			for (Div item : dis) {
				strTag.append(writerHtml(item,page));
			}
			
			tag.appendTagOtherTag(strTag.toString());
		} catch (InstantiationException | IllegalAccessException  | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return tag.toString();
	}
	
	
	
	
	
	
	/**
	 * ����ĸ��д
	 * @param str ��Ҫ�������ַ�
	 * @return	��������ĸ�Ǵ�д���ַ���
	 */
	public String upperCase(String str) {  
        char[] ch = str.toCharArray();  
        if (ch[0] >= 'a' && ch[0] <= 'z') {  
            ch[0] = (char) (ch[0] - 32);  
        }  
        return new String(ch);  
    }
	
	
}
