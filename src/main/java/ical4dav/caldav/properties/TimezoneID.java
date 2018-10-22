package ical4dav.caldav.properties;

import ical4dav.properties.iCalProperty;

/**
 * Timezone ID property 
 * 
 * @author tkrieger
 *
 */
public class TimezoneID extends iCalProperty {

	/**
	 * id value
	 */
	private String value;
	
	/**
	 * default constructor 
	 * 
	 * @param name
	 */
	public TimezoneID(String value) {
		super("TZID");
		
		this.value = value;
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
