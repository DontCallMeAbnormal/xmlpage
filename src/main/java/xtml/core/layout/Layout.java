package xtml.core.layout;

import java.util.HashMap;

import xtml.core.page.Page;
import xtml.core.page.Tag;

/**
 * ���ݲ��ֶ�������Ӧ��html����,���Ὣ�ض���js��css������뵽page������
 * @author Administrator
 *
 */
public interface Layout {
	/*
	 * ���ֵ�����ö��
	 */
	public  static final String DEFAULT="default";
	public  static final String center="center";
	
	 
	
	 /*
	 *	дһ��ģ��,��һ��from��ģ��,������ť,�ı������ɵ�ģ�� 
	 *	���еĲ����ɲ��� layout����
	 */
	default Tag writerModule(String layout,Tag tag,Page page) {
		
		switch(layout) {
			case DEFAULT:
				return defaultLayoutWay(tag,page);
			case center:
				return centerLayoutWay(tag,page);
			default: return null;
		}
		
	
	}
	
	/**
	 * Ĭ�ϲ��ַ���
	 * @param tag ���в�����Tag����
	 * @param page	���в�����Page����
	 * @return	����Tag����,���һ��Page������б�Ҫ��js,css�ַ���д��
	 */
	Tag defaultLayoutWay(Tag tag,Page page);
	
	/**
	 * ���в��ַ���
	 * @param tag ���в�����Tag����
	 * @param page	���в�����Page����
	 * @return	����Tag����,���һ��Page������б�Ҫ��js,css�ַ���д��
	 */
	Tag centerLayoutWay(Tag tag,Page page);
	
	
	
}
