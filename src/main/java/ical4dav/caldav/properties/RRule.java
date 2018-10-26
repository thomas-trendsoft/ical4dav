package ical4dav.caldav.properties;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import ical4dav.properties.Parameter;
import ical4dav.properties.iCalProperty;

public class RRule extends iCalProperty {
	
	private HashMap<String,String> rparts;

	public RRule(Integer token, String value,List<Parameter> params) throws ParseException {
		super(token, params);
		
		rparts = new HashMap<>();
		
		setValue(value);
	}

	@Override
	public void setValue(String value) throws ParseException {
		String[] s1 = value.split(";");
		
		for (String s : s1) {
			String[] pair = s.split("=");
			if (pair.length != 2) {
				throw new ParseException("no correct rrule pair: " + s,0);
			}
			rparts.put(pair[0], pair[1]);
		}
	}

	@Override
	public String getValue() {
		String ret = "";
		
		for (String k : rparts.keySet()) {
			ret += k + "=" + rparts.get(k) + ";";
		}
		
		return ret;
	}

}
