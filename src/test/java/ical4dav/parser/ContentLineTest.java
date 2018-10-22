package ical4dav.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

public class ContentLineTest {
	
	@Test
	public void testContentLine() throws UnsupportedEncodingException, IOException {
		String example = "BEGIN:VCALENDAR";
		
		ContentLine l = iCalParser.parseContentLine(new ByteArrayInputStream(example.getBytes("utf-8")));
		
		Assert.assertTrue(l.name != null && l.name.compareTo("BEGIN")==0);
		Assert.assertTrue(l.value != null && l.value.compareTo("VCALENDAR")==0);
		Assert.assertTrue(l.params.size() == 0);
	}

	@Test
	public void testContentLineParams() throws UnsupportedEncodingException, IOException {
		String example = "BEGIN;test=\"testtest\";abc=123:VCALENDAR";
		
		ContentLine l = iCalParser.parseContentLine(new ByteArrayInputStream(example.getBytes("utf-8")));

		System.out.println("cl: " + l.name + ":" + l.value + ":" + l.params.size());
		Assert.assertTrue(l.name != null && l.name.compareTo("BEGIN")==0);
		Assert.assertTrue(l.value != null && l.value.compareTo("VCALENDAR")==0);
		Assert.assertTrue(l.params.size() == 2);
	}

}
