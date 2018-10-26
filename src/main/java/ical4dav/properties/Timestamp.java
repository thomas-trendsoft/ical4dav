package ical4dav.properties;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ical4dav.caldav.iCalDAVParser;

public class Timestamp extends iCalProperty {

	private Date timestamp;
	
	public Timestamp(Integer token,String value,List<Parameter> params) throws ParseException {
		super(token,params);
		this.setValue(value);
	}

	@Override
	public void setValue(String value) throws ParseException {
		timestamp = iCalDAVParser.dateFormat.parse(value);
	}

	@Override
	public String getValue() {
		return iCalDAVParser.dateFormat.format(timestamp);
	}

}
