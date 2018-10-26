package ical4dav.properties;

import java.util.List;

public class StringProperty extends iCalProperty {

	private String value;
	
	public StringProperty(Integer token,String value,List<Parameter> params) {
		super(token);
		this.value = value;
		if (params != null) {
			this.parameters.addAll(params);
		}
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

}
