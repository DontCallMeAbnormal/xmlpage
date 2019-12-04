package xtml.core.page;

import java.util.ArrayList;
import java.util.List;

public class Conntorner {
	private String id;
	private String url;
	private List<Parm> parms;
	
	public void addParm(Parm parm) {
		if(parms==null) {
			parms=new ArrayList<Parm>();
		}
		parms.add(parm);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Parm> getParms() {
		return parms;
	}
	public void setParms(List<Parm> parms) {
		this.parms = parms;
	}
	
}
