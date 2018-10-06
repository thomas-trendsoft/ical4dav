package ical4dav.properties;

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
	private List<Parameter> parameters;

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
	
}
