package ical4dav.parser;

import java.util.LinkedList;
import java.util.List;

/**
 * Base line for ical lines 
 * 
 * @author tkrieger
 *
 */
public class ContentLine {

	public String name;
	
	public List<Param> params;
	
	public String value;
	
	public ContentLine(String name) {
		this.name   = name;
		params = new LinkedList<Param>();
		value  = null;
	}
	
}
