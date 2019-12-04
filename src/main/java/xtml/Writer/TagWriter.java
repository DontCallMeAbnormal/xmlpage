package xtml.Writer;

import xtml.core.page.Div;
import xtml.core.page.Page;
import xtml.core.page.Tag;

public interface TagWriter {
	/**
	 * 根据div 生成相应的html代码,并将需要的js,css代码 插入到page对象中
	 * @param div 作为转化源的div
	 * @param page	该方法会将js,css等文本加入到page对象的引用中
	 * @return	Tag	返回由div转换而成的Tag对象
	 */
	Tag writerTag(Div div,Page page);
}
