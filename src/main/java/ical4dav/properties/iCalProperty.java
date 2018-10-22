package ical4dav.properties;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * Base caldav property implementation
 * @author tkrieger
 *
 */
public abstract class iCalProperty {
	
	/**
	 * Property parameters
	 */
	protected List<Parameter> parameters;

	/**
	 * property name
	 */
	private String name;
	
	/**
	 * default constructor 
	 * 
	 * @param name property name
	 */
	public iCalProperty(String name) {
		this.name = name;
		this.parameters = new LinkedList<>();
	}
	
	public List<Parameter> getParameters() {
		return parameters;
	}

	/**
	 * parameter list construcotr 
	 * 
	 * @param name property name
	 * @param params property parameters
	 */
	public iCalProperty(String name,List<Parameter> params) {
		this.name       = name;
		this.parameters = params;
	}
	
	public abstract void setValue(String value) throws ParseException;
	
	public abstract String getValue();
	
	@Override
	public String toString() {
		String out = name; 
		for (Parameter p : parameters) {
			out += ";" + p.toString();
		}
		out += ":" + getValue();
		return out;
	}
	
}
