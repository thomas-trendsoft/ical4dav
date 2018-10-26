package ical4dav.caldav.resources;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import ical4dav.caldav.iCalDAVParser;
import ical4dav.caldav.properties.RRule;
import ical4dav.caldav.properties.UTCOffset;
import ical4dav.parser.ContentLine;
import ical4dav.parser.TokenMap;
import ical4dav.properties.StringProperty;
import ical4dav.properties.Timestamp;
import ical4dav.properties.iCalComponent;

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
				tp.getProperties().put(TokenMap.TZOFFSETFROM,new UTCOffset(TokenMap.TZOFFSETFROM, step.value));
				break;
			case TokenMap.TZOFFSETTO:
				tp.getProperties().put(TokenMap.TZOFFSETTO,new UTCOffset(TokenMap.TZOFFSETTO, step.value));
				break;
			case TokenMap.TZNAME:
				tp.addMultiProperty(new StringProperty(TokenMap.TZNAME, step.value,step.params));
				break;
			case TokenMap.DTSTART:
				tp.getProperties().put(TokenMap.DTSTART,new Timestamp(TokenMap.DTSTART,step.value,step.params));
				break;
			case TokenMap.RRULE:
				tp.addMultiProperty(new RRule(TokenMap.RRULE,step.value,step.params));
				break;				
			case TokenMap.END:
				return tp;
				default:
					throw new ParseException("unimplemented 1timezone property: " + step, 0);
			}
		}

		return tp;
	}
	
}
