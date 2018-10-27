package ical4dav.properties;

public class Parameter {
	public String key;
	
	public String value;
	
	@Override
	public String toString() {
		return key + "=" + value;
	}
}
