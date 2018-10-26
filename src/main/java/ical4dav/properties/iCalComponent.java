package ical4dav.properties;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * base ical component 
 * 
 * @author tkrieger
 */
public class iCalComponent {
	
	/**
	 * component name
	 */
	protected String name;
	
	/**
	 * single properties
	 */
	protected HashMap<Integer,iCalProperty> properties;
	
	/**
	 * multi properties
	 */
	protected HashMap<Integer,iCalMultiProperty> mproperties;
	
	/**
	 * sub components
	 */
	protected HashMap<String,iCalComponent> components;
	
	/**
	 * default constructor 
	 * 
	 * @param name
	 */
	public iCalComponent(String name) {
		this.name = name;
		this.properties = new HashMap<>();
		this.components = new HashMap<>();
	}

	public HashMap<Integer,iCalProperty> getProperties() {
		return properties;
	}
	
	public HashMap<Integer,iCalMultiProperty> getMultiProperties() {
		return mproperties;
	}
	
	public void addMultiProperty(iCalProperty prop) {
		iCalMultiProperty mp = mproperties.get(prop.getTokenId());
		if (mp == null) {
			mp = new iCalMultiProperty(prop.getTokenId());
		}
		mp.getProperties().add(prop);
	}

	public HashMap<String,iCalComponent> getComponents() {
		return components;
	}

	public List<iCalComponent> getComponentsByType(Class<?> type) {
		LinkedList<iCalComponent> ret = new LinkedList<>();
		
		for (iCalComponent c : components.values()) {
			if (c.getClass() == type) {
				ret.add(c);
			}
		}
		
		return ret;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append("BEGIN:" + name + "\r\n");
		
		for (iCalProperty p : properties.values()) {
			buf.append(p.toString() + "\r\n");
		}
		
		for (iCalComponent c : components.values()) {
			buf.append(c.toString());
		}
		
		for (iCalMultiProperty m : mproperties.values()) {
			for (iCalProperty p : m.getProperties()) {
				buf.append(p.toString());
			}
		}
		
		buf.append("END:" + name + "\r\n");
		
		return buf.toString();
	}
	
	
}
