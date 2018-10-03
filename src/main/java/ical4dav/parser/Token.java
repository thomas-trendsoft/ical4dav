package ical4dav.parser;

public class Token {
	
	public String value;
	
	public int last;
	
	public Token(String value,int last) {
		this.value = value;
		this.last  = last;
	}

}
