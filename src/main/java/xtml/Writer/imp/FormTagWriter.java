package xtml.Writer.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.dom4j.Document;
import org.dom4j.Element;
import xtml.Parse.imp.ConntornerPaeser;
import xtml.Reader.XmlReader;
import xtml.Writer.TagWriter;
import xtml.core.layout.Layout;
import xtml.core.page.Conntorner;
import xtml.core.page.Div;
import xtml.core.page.Page;
import xtml.core.page.Parm;
import xtml.core.page.Tag;

public class FormTagWriter implements TagWriter,Layout{

	private XmlReader xmlReader=XmlReader.getInstance();
	private ConntornerPaeser conntornerPaeser = new ConntornerPaeser();
	private Page page;//��ҳ�������
	
	private Conntorner contorner=null;//Conntorne��  ����url parm����Ϣ
	private String btnName="action";//�ύ����ť���ı� ��Ϊ��ʱ Ĭ��action
	
	
	/**
	 * ʹ����ʷxml�ļ�
	 */
	
	@Override
	public Tag writerTag(Div div,Page page) {
		// TODO Auto-generated method stub
		Tag tag=new Tag();
		tag.setTagName("form");
		this.page=page;
		return fromBuilder(tag,div);
		
	}

	
	/**
	 * ��tag���мӹ�,����һ��<from>��html�����
	 * @param tag
	 * @param div
	 */
	private Tag fromBuilder(Tag tag,Div div){
		Document document = xmlReader.read();
		return fromBuilder(tag,div,document);
	}
	
	
	/**
	 * ��tag���мӹ�,����һ��<from>��html�����
	 * ����Ҫ�ı�xml�ļ�Ϊ������һ��ʹ�õ��ļ�,���һ��ʹ��xml�ļ�ʱ
	 * �ṩDocument ����������writer����
	 * @param tag
	 * @param div
	 */
	private Tag fromBuilder(Tag tag,Div div,Document document){
		Element rootElement = document.getRootElement();
		//�Ȼ�ȡfrom�����������
		HashMap<String,String> attrs = div.getAttrs();
		for (Entry<String, String> entry : attrs.entrySet()) {
			if(entry.getKey().equals("type")) {
				tag.setTagMethod(entry.getValue());
				continue;
			}
			if(entry.getKey().equals("ref")) {
				contorner = conntornerPaeser.getContorner(rootElement, entry.getValue());
				tag.setTagId(entry.getValue());
				tag.setTagAction(contorner.getUrl());
				
				continue;
			}
			
			if(entry.getKey().equals("btnName")) {
				btnName=entry.getValue();
				continue;
			}
			
		}
		
		
		if(contorner==null) {
			try {
				throw new Exception("û���ҵ�contorner��ǩ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return writerModule(div.getLayout(), tag,page);//�ӿ�Ĭ�Ϸ���,ѡ���Ӧ�Ĳ���
		
	}


	

	@Override
	public Tag defaultLayoutWay(Tag tag,Page page) {
		// TODO Auto-generated method stub
		StringBuilder tagOther=new StringBuilder();
		List<Parm> parms = contorner.getParms();
		for (Parm parm : parms) {
			tagOther.append("<div class=\"form-group\">\r\n" + 
					"			   <label for=\""+parm.getParmName()+"\">"+parm.getParmName()+"</label>\r\n" + 
					"			   <input type=\""+parm.getParmType()+"\" class=\"form-control\" id=\""+parm.getParmName()+"\" placeholder=\""+parm.getParmName()+"\" name=\""+parm.getParmName()+"\" >\r\n" + 
					"			</div>	");
		}
		tagOther.append("<div class=\"form-group\" style=\"text-align: center;\" >\r\n" + 
				"			<button type=\"submit\" class=\"btn btn-primary\" style=\"width:100%\">"+btnName+"</button>\r\n" + 
				"		</div>");
		tag.setTagOtherTag(tagOther.toString());
		
		addJsByPage(parms, page);
		
		return tag;
	}


	
	@Override
	public Tag centerLayoutWay(Tag tag,Page page) {
		// TODO Auto-generated method stub
		Tag tagCenter = new Tag();
		tagCenter.setTagName("div");
		tagCenter.setTagClass("col-sm-4 col-md-4 col-lg-3");
		tagCenter.addAttrMap("style", "margin: auto;float: none;");
		defaultLayoutWay(tag,page);
		tagCenter.setTagOtherTag(tag.toString());
		return tagCenter;
	}


	/**
	 * Ϊҳ�����js���� ,ʹ�õ���jquery���
	 * @param parms
	 * @param page
	 */
	private void addJsByPage(List<Parm> parms,Page page) {
		
		List<Parm> parmsTrue=new ArrayList<Parm>();
		for (Parm parm : parms) {
			//���parm �� patter ���Բ��ǿ�����뵽parmsTrue��,��ȥдjs
			if(parm.getPattern()!=null && !parm.getPattern().equals("null")) {
				parmsTrue.add(parm);
			}
		}
		//���parms�����е�parm��patter���Զ��ǿ�,��дjs,ֱ�ӷ���
		if(parmsTrue.size()==0) {
			return;
		}

		page.appendJsText("$('#login').submit(function(e){");
		for (Parm parm : parmsTrue) {
			
			page.appendJsText("var patt"+parm.getParmName()+"=/"+parm.getPattern()+"/;\r\n");
			
		}
		page.appendJsText("function f(patt,obj){\r\n" + 
				"			//����ύ��ť,�����ƥ�����У��,Ȼ����ӻ�ȡ�����¼�,��ȡ������ȡ������״̬,ʧȥ�������ٴ�У��\r\n" + 
				"			//����һ��ƥ�����,�������������,����������һ��ֱ�Ӹ�Ԫ��\r\n" + 
				"			//ʧ�ܷ���false\r\n" + 
				"			//�ɹ�����true\r\n" + 
				"			if(!patt.test(obj.val())){\r\n" + 
				"				obj.parent().addClass('has-error');\r\n" + 
				"				obj.focus(function(){\r\n" + 
				"					  $(this).parent().removeClass(\"has-error\");\r\n" + 
				"				});\r\n" + 
				"				obj.blur(function(){\r\n" + 
				"					if(patt.test(obj.val())){\r\n" + 
				"						$(this).parent().removeClass(\"has-error\");\r\n" + 
				"					}else{\r\n" + 
				"						obj.parent().addClass('has-error');\r\n" + 
				"					}\r\n" + 
				"				});\r\n" + 
				"				return false;\r\n" + 
				"			}else{\r\n" + 
				"				return true;\r\n" + 
				"			}\r\n" + 
				"		}");
		for (Parm parm : parmsTrue) {
			page.appendJsText("var "+parm.getParmName()+"=f(patt"+parm.getParmName()+",$('#"+parm.getParmName()+"'));\r\n");
		}
		page.appendJsText("return ");
		for (Parm parm : parmsTrue) {
			if(parms.get(parms.size()-1)==parm) {
				page.appendJsText(parm.getParmName()+";\r\n");
			}else {
				page.appendJsText(parm.getParmName()+"&&");
			}
		}
		page.appendJsText("});");
	}
	

	
}
