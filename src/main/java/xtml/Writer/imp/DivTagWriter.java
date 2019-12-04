package xtml.Writer.imp;


import xtml.Writer.TagWriter;
import xtml.core.layout.Layout;
import xtml.core.page.Div;
import xtml.core.page.Page;
import xtml.core.page.Tag;

public class DivTagWriter implements TagWriter,Layout{

	@Override
	public Tag writerTag(Div div,Page page) {
		// TODO Auto-generated method stub
		Tag tag=new Tag();
		tag.setTagName("div");
		return writerModule(div.getLayout(), tag,page);
	}

	@Override
	public Tag defaultLayoutWay(Tag tag,Page page) {
		// TODO Auto-generated method stub
		tag.setTagClass("container-fluid");
		
		return tag;
	}

	@Override
	public Tag centerLayoutWay(Tag tag,Page page) {
		// TODO Auto-generated method stub
		
		return tag;
	}

}
