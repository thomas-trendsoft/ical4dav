package ical4dav.caldav.resources;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;

import ical4dav.caldav.iCalDAVParser;
import ical4dav.parser.ContentLine;
import ical4dav.parser.TokenMap;

/**
 * calendar object event resource implementation
 * 
 * @author tkrieger
 *
 */
public class Event extends CalDAVResource {
	
	/**
	 * event summary
	 */
	private String summary;
	
	private Date timestamp;
	
	private Date start;
	
	private Date end;
	
	/**
	 * default constructor 
	 */
	public Event() {
		
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
				event.summary = step.value;
				break;
			case TokenMap.DTSTAMP:
				event.timestamp = iCalDAVParser.dateFormat.parse(step.value);
				break;
			case TokenMap.DTEND:
				event.end = iCalDAVParser.dateFormat.parse(step.value);
				break;
			case TokenMap.DTSTART:
				event.start = iCalDAVParser.dateFormat.parse(step.value);
				break;
			case TokenMap.END:
				return event;
			}
		}
		
		return event;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append("BEGIN:VEVENT\r\n");
		if (summary != null)
			buf.append("SUMMARY:" + summary + "\r\n");
		if (timestamp != null)
			buf.append("DTSTAMP:" + iCalDAVParser.dateFormat.format(timestamp) + "\r\n");
		if (start != null)
			buf.append("DTSTART:" + iCalDAVParser.dateFormat.format(start) + "\r\n");
		if (end != null) 
			buf.append("DTEND:" + iCalDAVParser.dateFormat.format(end) + "\r\n");
		buf.append("END:VEVENT\r\n");
		
		return buf.toString();
	}

}
