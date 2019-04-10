package ical4dav.caldav.resources;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import ical4dav.caldav.iCalDAVParser;
import ical4dav.parser.ContentLine;
import ical4dav.parser.TokenMap;
import ical4dav.properties.StringProperty;
import ical4dav.properties.iCalComponent;
import ical4dav.properties.iCalMultiProperty;
import ical4dav.properties.iCalProperty;

/**
 * iCal calendar 
 * 
 * @author tkrieger
 *
 */
public class Calendar extends CalDAVResource {
	
	/**
	 * Defaultconstructor 
	 */
	public Calendar() {
		super("VCALENDAR");
		
		this.getProperties().put(TokenMap.VERSION, new StringProperty(TokenMap.VERSION, "2.0", null));
	}
	
	public void setProdID(String value) {
		getProperties().put(TokenMap.PRODID, new StringProperty(TokenMap.PRODID, value,null));
	}
	
	public void setTimezone(Timezone tz) {
		this.components.put("VTIMEZONE", tz);
	}
	
	public String toString(List<iCalComponent> comps) {
		StringBuffer buf = new StringBuffer();
		
		buf.append("BEGIN:" + name + "\r\n");
		
		for (iCalProperty p : properties.values()) {
			buf.append(p.toString() + "\r\n");
		}
		
		for (iCalComponent c : comps) {
			buf.append(c.toString());
		}
		
		for (iCalMultiProperty m : mproperties.values()) {
			for (iCalProperty p : m.getProperties()) {
				buf.append(p.toString());
			}
		}
		
		buf.append("END:" + name + "\r\n");
		
		return buf.toString();
		
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
				throw new ParseException("unknown calendar token: " + step.name,0);
			}
			
			switch (t) {
			case TokenMap.PRODID:
				cal.getProperties().put(TokenMap.PRODID,new StringProperty(TokenMap.PRODID,step.value,step.params));
				break;
			case TokenMap.VERSION:
				cal.getProperties().put(TokenMap.VERSION,new StringProperty(TokenMap.VERSION, step.value,step.params));
				break;
			case TokenMap.UID:
				cal.UID = step.value;
			case TokenMap.BEGIN:
				if (step.value.compareTo("VEVENT")==0) {
					Event e = Event.parse(data);
					cal.getComponents().put(e.getUID(), e);
				} else if (step.value.compareTo("VTIMEZONE")==0) {
					Timezone tz = Timezone.parse(data);
					cal.getComponents().put(tz.UID, tz);
				} else {
					throw new ParseException("unknown calendar resource: '" + step.value + "'",0);
				}
				break;
			case TokenMap.END:
				return cal;
				default:
					throw new ParseException("unimplemented calendar token: '" + step.name + "'", 0);
			}
		}
		
		return cal;
	}
	
	
}
