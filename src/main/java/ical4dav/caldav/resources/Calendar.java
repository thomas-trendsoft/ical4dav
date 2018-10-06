package ical4dav.caldav.resources;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ical4dav.caldav.iCalDAVParser;
import ical4dav.parser.ContentLine;
import ical4dav.parser.Property;
import ical4dav.parser.TokenMap;

/**
 * iCal calendar 
 * 
 * @author tkrieger
 *
 */
public class Calendar extends CalDAVResource {

	/**
	 * Product ID
	 */
	@Property
	private String prodId;
	
	/**
	 * Calendar version
	 */
	private String version;
	
	/**
	 * main child entries
	 */
	private List<CalDAVResource> childs;
	
	/**
	 * calendar timezone entries
	 */
	private HashMap<String,Timezone> timezones;
	
	/**
	 * default constructor 
	 */
	public Calendar() {
		childs    = new LinkedList<>();
		timezones = new HashMap<>();
	}
	
	/**
	 * parse a calendar object 
	 * 
	 * @param data
	 * @return
	 * @throws IOException 
	 */
	public static Calendar parse(InputStream data) throws ParseException,IOException {
		ContentLine step;
		Calendar    cal  = new Calendar();

		// parse lines
		while ((step = iCalDAVParser.parseContentLine(data)) != null) {
			Integer t = TokenMap.getTokenMap().get(step.name);
			
			if (t == null) {
				throw new ParseException("unknown token: " + step.name,0);
			}
			
			switch (t) {
			case TokenMap.PRODID:
				cal.prodId = step.value;
				break;
			case TokenMap.VERSION:
				cal.version = step.value;
				break;
			case TokenMap.UID:
				cal.UID = step.value;
			case TokenMap.BEGIN:
				if (step.value.compareTo("VEVENT")==0) {
					Event e = Event.parse(data);
					cal.childs.add(e);
				} else if (step.value.compareTo("VTIMEZONE")==0) {
					Timezone tz = Timezone.parse(data);
					cal.timezones.put(String.valueOf(tz.getTzId()), tz);
				} else {
					throw new ParseException("unknown calendar resource: " + step.value,0);
				}
				break;
			case TokenMap.END:
				return cal;
			}
		}
		
		return cal;
	}
	
	@Override 
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append("BEGIN:VCALENDAR\r\n");
		if (prodId != null)
			buf.append("PRODID:" + prodId + "\r\n");
		if (version != null)
			buf.append("VERSION:" + version + "\r\n");
		if (UID != null)
			buf.append("UID:" + UID + "\r\n");
		
		// append child resource elements
		for (CalDAVResource r : childs) {
			buf.append(r.toString());
		}
		
		buf.append("END:VCALENDAR\r\n");
		
		return buf.toString();
	}
	
}
