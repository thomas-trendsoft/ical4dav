package ical4dav.properties;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import ical4dav.parser.TokenMap;

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
	 * property name token id
	 */
	private Integer token;
	
	/**
	 * default constructor 
	 * 
	 * @param name property name
	 */
	public iCalProperty(Integer tokenid) {
		this.token = tokenid;
		this.parameters = new LinkedList<>();
	}
	
	public Integer getTokenId() {
		return token;
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
	public iCalProperty(Integer token,List<Parameter> params) {
		this.token      = token;
		this.parameters = params;
	}
	
	public abstract void setValue(String value) throws ParseException;
	
	public abstract String getValue();
	
	@Override
	public String toString() {
		String out = TokenMap.getStringMap().get(token); 
		for (Parameter p : parameters) {
			out += ";" + p.key + "=" + p.value;
		}
		out += ":" + getValue();
		return out;
	}
	
}
