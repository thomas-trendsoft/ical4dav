package ical4dav.caldav.properties;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import ical4dav.caldav.iCalDAVParser;
import ical4dav.caldav.resources.Timezone;
import ical4dav.parser.ContentLine;
import ical4dav.parser.TokenMap;
import ical4dav.properties.StringProperty;
import ical4dav.properties.Timestamp;
import ical4dav.properties.iCalComponent;
import ical4dav.properties.iCalProperty;

/**
 * tzprop implementation 
 * 
 * @author tkrieger
 *
 */
public class TzProp extends iCalComponent {
	
	/**
	 * default constructor 
	 * 
	 * @param name
	 */
	public TzProp(String name) {
		super(name);
	}

	public static TzProp parse(String name,InputStream data) throws IOException, ParseException {
		ContentLine step;
		TzProp tp = new TzProp(name);
		
		while ((step = iCalDAVParser.parseContentLine(data)) != null) {
			Integer t = TokenMap.getTokenMap().get(step.name);
			
			if (t == null) {
				throw new ParseException("unknown tzprop token: '" + step.name + "'",0);
			}
			
			switch (t) {
			case TokenMap.TZOFFSETFROM:
				tp.getProperties().add(new UTCOffset("TZOFFSETFROM", step.value));
				break;
			case TokenMap.TZOFFSETTO:
				tp.getProperties().add(new UTCOffset("TZOFFSETTO", step.value));
				break;
			case TokenMap.TZNAME:
				tp.getProperties().add(new StringProperty("TZNAME", step.value,step.params));
				break;
			case TokenMap.DTSTART:
				tp.getProperties().add(new Timestamp("DTSTART",step.value,step.params));
				break;
			case TokenMap.END:
				return tp;
				default:
					throw new ParseException("unimplemented timezone property: " + step, 0);
			}
		}

		return tp;
	}
	
}
