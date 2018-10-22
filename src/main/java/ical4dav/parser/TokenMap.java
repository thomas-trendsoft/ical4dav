package ical4dav.parser;

import java.util.HashMap;

/**
 * Parser helping util
 * 
 * @author tkrieger
 *
 */
public class TokenMap {
	
	public static final int BEGIN = 0;
	
	public static final int END = 1;
	
	public static final int PRODID = 2;
	
	public static final int VERSION = 3;
	
	public static final int UID = 4;
	
	public static final int SUMMARY = 5;
	
	public static final int DTSTAMP = 6;
	
	public static final int DTSTART = 7;
	
	public static final int DTEND = 8;
	
	public static final int RRULE = 9;
	
	public static final int RECURRENCE_ID = 10;
	
	public static final int TZID = 11;
	
	public static final int TZOFFSETFROM = 12;
	
	public static final int TZOFFSETTO = 13;
	
	public static final int TZNAME = 14;
	
	
	private static HashMap<String,Integer> pmap = null;
	
	public static HashMap<String,Integer> getTokenMap() {
		if (pmap == null) {
			pmap = new HashMap<String,Integer>();
			pmap.put("BEGIN", BEGIN);
			pmap.put("END", END);
			pmap.put("PRODID",PRODID);
			pmap.put("VERSION",VERSION);
			pmap.put("UID",UID);
			pmap.put("SUMMARY", SUMMARY);
			pmap.put("DTSTAMP",DTSTAMP);
			pmap.put("DTSTART", DTSTART);
			pmap.put("DTEND", DTEND);
			pmap.put("RRULE",RRULE);
			pmap.put("RECURRENCE-ID", RECURRENCE_ID);
			pmap.put("TZID",TZID);
			pmap.put("TZOFFSETFROM", TZOFFSETFROM);
			pmap.put("TZOFFSETTO", TZOFFSETTO);
			pmap.put("TZNAME", TZNAME);
		}
		return pmap;
	}
}
