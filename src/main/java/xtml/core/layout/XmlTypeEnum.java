package xtml.core.layout;

public class XmlTypeEnum {
	

	/*
	 * 	支持的元素类型
	 * 如 xml:<div>\<from>\<img>
	 * 对应html:<div>\<from>\<img>
	 * 
	 * 但不仅仅是一一对应,xml中的<from>代表的是一张完整的表单,而不需要
	 * 	项html那样在标签之间写<button>\<input>等繁琐的标签
	 * 系统根据配置好的Conntorner 来自动为<from>添加必要的组件,
	 * 且根据设置好的layout属性进行组件的布局
	 */
	public  static final String DIV="div";
	public  static final String FROM="from";
	public  static final String IMG="img";

	
}
