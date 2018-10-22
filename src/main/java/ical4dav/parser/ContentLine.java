package ical4dav.parser;

import java.util.LinkedList;
import java.util.List;

import ical4dav.properties.Parameter;

/**
 * Base line for ical lines 
 * 
 * @author tkrieger
 *
 */
public class ContentLine {

	public String name;
	
	public List<Parameter> params;
	
	public String value;
	
	public ContentLine(String name) {
		this.name   = name;
		params = new LinkedList<Parameter>();
		value  = null;
	}
	
	@Override
	public String toString() {
		return "'" + name + "':'" + value + "'";
	}
}
