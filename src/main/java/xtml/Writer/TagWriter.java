package xtml.Writer;

import xtml.core.page.Div;
import xtml.core.page.Page;
import xtml.core.page.Tag;

public interface TagWriter {
	/**
	 * ����div ������Ӧ��html����,������Ҫ��js,css���� ���뵽page������
	 * @param div ��Ϊת��Դ��div
	 * @param page	�÷����Ὣjs,css���ı����뵽page�����������
	 * @return	Tag	������divת�����ɵ�Tag����
	 */
	Tag writerTag(Div div,Page page);
}
