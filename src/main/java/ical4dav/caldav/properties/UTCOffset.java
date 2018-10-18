package ical4dav.caldav.properties;

import java.text.DecimalFormat;

import ical4dav.properties.iCalProperty;

/**
 * UTC Offset for tz properties
 * 
 * @author tkrieger
 *
 */
public class UTCOffset extends iCalProperty {

	protected static final DecimalFormat format = new DecimalFormat("+0000;-#");
	
	/**
	 * offset value
	 */
	private int offset;
	
	/**
	 * default constructor 
	 * 
	 * @param name
	 * @param value
	 */
	public UTCOffset(String name,String value) {
		super(name);
		parseValue(value);
	}
	
	private void parseValue(String value) {
		this.offset = Integer.parseInt(value);
	}

	@Override
	public void setValue(String value) {
		parseValue(value);
	}

	@Override
	public String getValue() {
		return format.format(offset);
	}

}
