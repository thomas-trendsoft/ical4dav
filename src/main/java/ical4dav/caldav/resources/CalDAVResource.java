package ical4dav.caldav.resources;

import ical4dav.properties.iCalComponent;

/**
 * ical calendar resource base class
 * 
 * @author tkrieger
 *
 */
public abstract class CalDAVResource extends iCalComponent {
	
	/**
	 * UID property
	 */
	protected String UID;

	public CalDAVResource(String name) {
		super(name);
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}
	
}
