package ical4dav.caldav.resources;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import ical4dav.caldav.iCalDAVParser;
import ical4dav.parser.ContentLine;
import ical4dav.parser.TokenMap;
import ical4dav.properties.StringProperty;

public class Timezone extends CalDAVResource {

	public Timezone() {
		super("VTIMEZONE");
	}
	
	public void setTzId(StringProperty tzid) {
		this.properties.put(TokenMap.TZID, tzid);
	}

	/**
	 * parse timezone resource
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Timezone parse(InputStream data) throws IOException, ParseException {
		ContentLine step;
		Timezone    tz = new Timezone();
		
		while ((step = iCalDAVParser.parseContentLine(data)) != null) {
			Integer t = TokenMap.getTokenMap().get(step.name);
			
			if (t == null) {
				throw new ParseException("unknown timezone token: " + step.name,0);
			}
			
			switch (t) {
			case TokenMap.TZID:
				tz.getProperties().put(TokenMap.TZID,new StringProperty(TokenMap.TZID, step.value, step.params));
				break;
			case TokenMap.BEGIN:
				System.out.println("sub tz res: " + step.value);
				if (step.value.compareTo("DAYLIGHT")==0 || step.value.compareTo("STANDARD")==0) {
					// TODO check multi component of same name for the rfc may occur more than one
					tz.getComponents().put(step.value,TzProp.parse(step.value,data));
				} else {
					throw new ParseException("unimplemented timzone sub property: " + step,0);
				}
				break;
			case TokenMap.END:
				return tz;
				default:
					throw new ParseException("unimplemented timezone property: " + step, 0);
			}
		}
		
		return tz;
	}
}
