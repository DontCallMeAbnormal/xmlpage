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
	private Page page;//该页面的引用
	
	private Conntorner contorner=null;//Conntorne类  包含url parm等信息
	private String btnName="action";//提交表单按钮的文本 当为空时 默认action
	
	
	/**
	 * 使用历史xml文件
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
	 * 对tag进行加工,生成一个<from>的html代码段
	 * @param tag
	 * @param div
	 */
	private Tag fromBuilder(Tag tag,Div div){
		Document document = xmlReader.read();
		return fromBuilder(tag,div,document);
	}
	
	
	/**
	 * 对tag进行加工,生成一个<from>的html代码段
	 * 当需要改变xml文件为不是上一次使用的文件,或第一次使用xml文件时
	 * 提供Document 对象而来完成writer操作
	 * @param tag
	 * @param div
	 */
	private Tag fromBuilder(Tag tag,Div div,Document document){
		Element rootElement = document.getRootElement();
		//先获取from表单的相关属性
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
				throw new Exception("没有找到contorner标签");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return writerModule(div.getLayout(), tag,page);//接口默认方法,选择对应的布局
		
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
	 * 为页面添加js代码 ,使用的是jquery框架
	 * @param parms
	 * @param page
	 */
	private void addJsByPage(List<Parm> parms,Page page) {
		
		List<Parm> parmsTrue=new ArrayList<Parm>();
		for (Parm parm : parms) {
			//如果parm 的 patter 属性不是空则加入到parmsTrue中,拿去写js
			if(parm.getPattern()!=null && !parm.getPattern().equals("null")) {
				parmsTrue.add(parm);
			}
		}
		//如果parms中所有的parm的patter属性都是空,则不写js,直接返回
		if(parmsTrue.size()==0) {
			return;
		}

		page.appendJsText("$('#login').submit(function(e){");
		for (Parm parm : parmsTrue) {
			
			page.appendJsText("var patt"+parm.getParmName()+"=/"+parm.getPattern()+"/;\r\n");
			
		}
		page.appendJsText("function f(patt,obj){\r\n" + 
				"			//点击提交按钮,则根据匹配规则校验,然后添加获取焦点事件,获取焦点则取消错误状态,失去焦点则再次校验\r\n" + 
				"			//参数一是匹配规则,参数二是输入框,输入框必须有一个直接父元素\r\n" + 
				"			//失败返回false\r\n" + 
				"			//成功返回true\r\n" + 
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
