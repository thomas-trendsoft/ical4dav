package ical4dav.caldav.properties;

import java.text.ParseException;
import java.util.List;

import ical4dav.properties.Parameter;
import ical4dav.properties.iCalProperty;

public class Transparent extends iCalProperty {

	private int value;
	
	public Transparent(Integer token, String value, List<Parameter> params) throws ParseException {
		super(token, params);
		
		setValue(value);
	}

	@Override
	public void setValue(String v) throws ParseException {
		if (v.toUpperCase().startsWith("OP")) {
			value = 1;
		} else {
			value = 0;
		}
	}

	@Override
	public String getValue() {
		if (value == 1) {
			return "OPAQUE";
		} else {
			return "TRANSPARENT";
		}
	}

}
