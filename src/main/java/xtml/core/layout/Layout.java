package xtml.core.layout;

import java.util.HashMap;

import xtml.core.page.Page;
import xtml.core.page.Tag;

/**
 * 根据布局而生成相应的html代码,并会将特定的js或css代码插入到page对象中
 * @author Administrator
 *
 */
public interface Layout {
	/*
	 * 布局的类型枚举
	 */
	public  static final String DEFAULT="default";
	public  static final String center="center";
	
	 
	
	 /*
	 *	写一个模块,如一个from表单模块,包含按钮,文本框等组成的模块 
	 *	其中的布局由参数 layout决定
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
	 * 默认布局方法
	 * @param tag 进行操作的Tag对象
	 * @param page	进行操作的Page对象
	 * @return	返回Tag对象,并且会对Page对象进行必要的js,css字符的写入
	 */
	Tag defaultLayoutWay(Tag tag,Page page);
	
	/**
	 * 居中布局方法
	 * @param tag 进行操作的Tag对象
	 * @param page	进行操作的Page对象
	 * @return	返回Tag对象,并且会对Page对象进行必要的js,css字符的写入
	 */
	Tag centerLayoutWay(Tag tag,Page page);
	
	
	
}
