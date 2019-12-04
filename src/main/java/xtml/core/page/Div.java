package xtml.core.page;

import java.util.HashMap;
import java.util.List;

public class Div {
	private String id;
	private String layout;
	private List<Div> divs;
	private String divType;
	private HashMap<String,String> attrs;//Ԫ�� �� ���ԶԼ���
	
	/**
	 * Ϊdiv׷�����Զ�,ʹ��HashMap,���д洢,����ÿ�����Զ���Ψһ��
	 * @param key ������
	 * @param value ����ֵ
	 */
	public void addAtts(String key,String value) {
		if(attrs==null) {
			attrs=new HashMap<String,String>();
		}
		attrs.put(key, value);
	}
	
	public HashMap<String, String> getAttrs() {
		return attrs;
	}


	public void setAttrs(HashMap<String, String> attrs) {
		this.attrs = attrs;
	}


	public Div() {	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		addAtts("id", id);
		this.id = id;
	}


	public String getLayout() {
		return layout;
	}


	public void setLayout(String layout) {
		addAtts("layout", layout);
		this.layout = layout;
	}


	public List<Div> getDivs() {
		return divs;
	}


	public void setDivs(List<Div> divs) {
		this.divs = divs;
	}


	public Div(String id, String layout, List<Div> divs) {
		super();
		this.id = id;
		this.layout = layout;
		this.divs = divs;
	}


	public String getDivType() {
		return divType;
	}


	public void setDivType(String divType) {
		addAtts("divType", divType);
		this.divType = divType;
	}

	@Override
	public String toString() {
		return "{\"divs\":\"" + divs + "\", \"attrs\":\"" + attrs + "\"}";
	}

	
	
}
