package ical4dav.caldav.resources;

/**
 * ical calendar resource base class
 * 
 * @author tkrieger
 *
 */
public class CalDAVResource {
	
	/**
	 * UID property
	 */
	protected String UID;

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}
	
}
