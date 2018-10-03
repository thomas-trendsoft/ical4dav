package ical4dav.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import ical4dav.caldav.iCalDAVParser;
import ical4dav.caldav.resources.CalDAVResource;

public class ParserTest {

	@Test
	public void smaple1Parse() throws FileNotFoundException, IOException, ParseException {
		CalDAVResource c = iCalDAVParser.parse(new FileInputStream("src/test/resources/caldav/sample1.ics"));
		System.out.println(c);
	}
}
