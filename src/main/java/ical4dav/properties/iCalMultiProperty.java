package ical4dav.properties;

import java.util.LinkedList;
import java.util.List;

/**
 * Container for properties that can be present multiple times 
 * 
 * @author tkrieger
 *
 */
public class iCalMultiProperty {

	/**
	 * token id
	 */
	private Integer token;
	
	/**
	 * instances of given properties
	 */
	private List<iCalProperty> properties;
	
	/**
	 * default constructor 
	 * 
	 * @param name
	 */
	public iCalMultiProperty(Integer token) {
		this.token = token;
		
		this.properties = new LinkedList<>();
	}

	public List<iCalProperty> getProperties() {
		return properties;
	}

	public Integer getTokenId() {
		return token;
	}

}
