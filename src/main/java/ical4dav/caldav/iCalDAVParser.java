package ical4dav.caldav;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ical4dav.caldav.resources.CalDAVResource;
import ical4dav.caldav.resources.Calendar;
import ical4dav.caldav.resources.Event;
import ical4dav.parser.ContentLine;
import ical4dav.parser.iCalParser;

/**
 * basic ical calendar object parser
 * 
 * @author tkrieger
 *
 */
public class iCalDAVParser extends iCalParser {

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
	
	public static CalDAVResource parse(InputStream data) throws IOException, ParseException {
		CalDAVResource ret   = null;
		ContentLine    start = parseContentLine(data);
		
		if (start.value == null || start.name == null || start.name.compareTo("BEGIN") != 0) {
			throw new ParseException("no ical begin found",0);
		}
		
		if (start.value.compareTo("VCALENDAR")==0) {
			ret = Calendar.parse(data);
		} else if (start.value.compareTo("VEVENT")==0) {
			ret = Event.parse(data);
		} else {
			System.err.println("unkown resource token: " + start.value);
		}
		
		return ret;
	}

}
