package ical4dav.properties;

import java.util.LinkedList;
import java.util.List;

public class iCalComponent {
	
	protected String name;
	
	protected List<iCalProperty> properties;
	
	protected List<iCalComponent> components;
	
	public iCalComponent(String name) {
		this.name = name;
		this.properties = new LinkedList<>();
		this.components = new LinkedList<>();
	}

	public List<iCalProperty> getProperties() {
		return properties;
	}

	public List<iCalComponent> getComponents() {
		return components;
	}

	public List<iCalComponent> getComponentsByType(Class<?> type) {
		LinkedList<iCalComponent> ret = new LinkedList<>();
		
		for (iCalComponent c : components) {
			if (c.getClass() == type) {
				ret.add(c);
			}
		}
		
		return components;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append("BEGIN:" + name + "\r\n");
		
		for (iCalProperty p : properties) {
			buf.append(p.toString() + "\r\n");
		}
		
		for (iCalComponent c : components) {
			buf.append(c.toString());
		}
		
		buf.append("END:" + name + "\r\n");
		
		return buf.toString();
	}
	
	
}
