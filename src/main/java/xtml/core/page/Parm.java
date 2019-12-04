package xtml.core.page;

public class Parm {
	private String parmType;
	private String parmName;
	private String pattern;//表单输入框的匹配规则
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getParmName() {
		return parmName;
	}

	public void setParmName(String parmName) {
		this.parmName = parmName;
	}

	public String getParmType() {
		return parmType;
	}

	public void setParmType(String parmType) {
		this.parmType = parmType;
	}
	
}
