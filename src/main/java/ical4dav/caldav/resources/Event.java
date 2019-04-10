package ical4dav.caldav.resources;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import ical4dav.caldav.iCalDAVParser;
import ical4dav.caldav.properties.RRule;
import ical4dav.caldav.properties.Transparent;
import ical4dav.parser.ContentLine;
import ical4dav.parser.TokenMap;
import ical4dav.properties.StringProperty;
import ical4dav.properties.Timestamp;

/**
 * calendar object event resource implementation
 * 
 * @author tkrieger
 *
 */
public class Event extends CalDAVResource {
	
	/**
	 * default constructor 
	 */
	public Event() {
		super("VEVENT");
	}

	/**
	 * parse a calendar object 
	 * 
	 * @param data
	 * @return
	 * @throws IOException 
	 */
	public static Event parse(InputStream data) throws ParseException,IOException {
		ContentLine step;
		Event      event = new Event();
		
		while ((step = iCalDAVParser.parseContentLine(data)) != null) {
			Integer t = TokenMap.getTokenMap().get(step.name);
			
			if (t == null) {
				throw new ParseException("unknown token: " + step.name,0);
			}
			
			switch (t) {
			case TokenMap.UID:
				event.UID = step.value;
				break;
			case TokenMap.SUMMARY:
				event.properties.put(TokenMap.SUMMARY, new StringProperty(TokenMap.SUMMARY, step.value, step.params));
				break;
			case TokenMap.DTSTAMP:
				event.properties.put(TokenMap.DTSTAMP,new StringProperty(TokenMap.DTSTAMP, step.value, step.params));
				break;
			case TokenMap.DTEND:
				event.properties.put(TokenMap.DTEND,new Timestamp(TokenMap.DTEND, step.value, step.params));
				break;
			case TokenMap.DTSTART:
				event.properties.put(TokenMap.DTSTART,new Timestamp(TokenMap.DTSTART,step.value,step.params));
				break;
			case TokenMap.CREATED:
				event.properties.put(TokenMap.CREATED,new Timestamp(TokenMap.CREATED, step.value, step.params));
				break;
			case TokenMap.RRULE:
				event.addMultiProperty(new RRule(TokenMap.RRULE, step.value, step.params));
				break;	
			case TokenMap.LAST_MODIFIED:
				event.properties.put(TokenMap.LAST_MODIFIED, new Timestamp(TokenMap.LAST_MODIFIED,step.value,step.params));
				break;
			case TokenMap.TRANSP:
				event.properties.put(TokenMap.TRANSP, new Transparent(TokenMap.TRANSP, step.value, step.params));
				break;
			case TokenMap.END:
				return event;
			}
		}
		
		return event;
	}
	

}
