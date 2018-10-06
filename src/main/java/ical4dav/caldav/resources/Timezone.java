package ical4dav.caldav.resources;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import ical4dav.caldav.iCalDAVParser;
import ical4dav.caldav.properties.TimezoneID;
import ical4dav.parser.ContentLine;
import ical4dav.parser.TokenMap;

public class Timezone extends CalDAVResource {

	private TimezoneID tzId;

	public TimezoneID getTzId() {
		return tzId;
	}

	public static Timezone parse(InputStream data) throws IOException, ParseException {
		ContentLine step;
		Timezone    tz = new Timezone();
		
		while ((step = iCalDAVParser.parseContentLine(data)) != null) {
			Integer t = TokenMap.getTokenMap().get(step.name);
			
			if (t == null) {
				throw new ParseException("unknown token: " + step.name,0);
			}
			
			switch (t) {
			}
		}
		
		return tz;
	}
}
