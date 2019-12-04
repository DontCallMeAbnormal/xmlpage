package xtml.core.page;

import java.util.HashMap;
import java.util.Map.Entry;

import com.sun.javafx.collections.MappingChange.Map;

import java.util.Set;

public class Tag {
	
	private String tagName;
	private String tagClass;
	private String tagId;
	private String tagSrc;
	private String tagHref;
	private String tagType;
	private String tagValue;
	private String tagAction;
	private String tagMethod;
	/**
	 * ����HashMap ������,key��Ψһ��
	 * ���� ��׷������ʱ tag��ǩҲ����Ψһ�� �����ظ�
	 */
	private HashMap<String, String> tagAttrMap;
	
	private String tagOtherTag;//���ڱ�ǩ��֮����ı�,��<a> {tagOtherTag} </a>
	
	
	public Tag() {}
	
	public Tag(Tag tag) {
		tagOtherTag=tag.toString();
	}

	
	
	public String getTagAttrMaptoString() {
		if(tagAttrMap==null || tagAttrMap.size()==0) {
			return "";
		}
		StringBuilder str=new StringBuilder();
		for (Entry<String,String> entrySet : tagAttrMap.entrySet()) {
			str.append(" "+entrySet.getKey()+"=\""+entrySet.getValue()+"\" ");			
		}
		return str.toString();
	}
	
	
	
	public HashMap<String, String> getTagAttrMap() {
		return tagAttrMap;
	}
	public void setTagAttrMap(HashMap<String, String> tagAttrMap) {
		this.tagAttrMap = tagAttrMap;
	}
	
	/**
	 * Ϊdiv׷�����Զ�,ʹ��HashMap,���д洢,����ÿ�����Զ���Ψһ��
	 * @param key ������
	 * @param value ����ֵ
	 */
	public void addAttrMap(String key,String value) {
		if(tagAttrMap==null) {
			tagAttrMap=new HashMap<String,String>();
		}
		tagAttrMap.put(key, value);
	}
	
	/** 
	 * ׷�����Լ��� ,ʵ����ʧ��������addAttrMap(String key,String value)����
	 * @param attrs ���Լ���
	 */
	
	public void addAttrMaps(HashMap<String,String> attrs) {
		for (Entry<String, String> entry : attrs.entrySet()) {
			addAttrMap(entry.getKey(), entry.getValue());
		}
		
	}
	
	
	
	public String getTagOtherTag() {
		if(tagOtherTag==null || tagOtherTag.equals("null")) {
			return "";
		}
		return tagOtherTag;
	}
	
	
	public void setTagOtherTag(String tagOtherTag) {
		this.tagOtherTag = tagOtherTag;
	}
	
	
	public void appendTagOtherTag(String tagOtherTag) {
		setTagOtherTag(getTagOtherTag()+tagOtherTag);
	}
	
	public String getTagName() {
		if(tagName==null) {
			return "";
		}
		return tagName;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
	public String getTagClass() {
		return tagClass;
	}
	
	public void setTagClass(String tagClass) {
		addAttrMap("Class", tagClass);
		this.tagClass = tagClass;
	}
	
	public String getTagId() {
		return tagId;
	}
	
	public void setTagId(String tagId) {
		addAttrMap("id", tagId);
		this.tagId = tagId;
	}
	
	public String getTagSrc() {
		return tagSrc;
	}
	public void setTagSrc(String tagSrc) {
		addAttrMap("src", tagSrc);
		this.tagSrc = tagSrc;
	}
	
	public String getTagHref() {
		return tagHref;
	}
	public void setTagHref(String tagHref) {
		addAttrMap("href", tagHref);
		this.tagHref = tagHref;
	}
	public String getTagType() {
		return tagType;
	}
	public void setTagType(String tagType) {
		addAttrMap("type", tagType);
		this.tagType = tagType;
	}
	public String getTagValue() {
		return tagValue;
	}
	public void setTagValue(String tagValue) {
		addAttrMap("value", tagValue);
		this.tagValue = tagValue;
	}
	public String getTagAction() {
		return tagAction;
	}
	public void setTagAction(String tagAction) {
		addAttrMap("action", tagAction);
		this.tagAction = tagAction;
	}
	public String getTagMethod() {
		return tagMethod;
	}
	public void setTagMethod(String tagMethod) {
		addAttrMap("method", tagMethod);
		this.tagMethod = tagMethod;
	}
	@Override
	public String toString() {
		return "<"+getTagName()+getTagAttrMaptoString()+">"+getTagOtherTag()+
				"</"+getTagName()+">";
	}
	
	
	
}
