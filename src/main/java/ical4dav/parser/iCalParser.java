package ical4dav.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import ical4dav.properties.Parameter;

/**
 * base parse methods 
 * 
 * @author tkrieger
 *
 */
public abstract class iCalParser {
	
	/**
	 * parse next token in data stream
	 * @param data data stream
	 * @param stops stop chars 
	 * @return
	 * @throws IOException
	 */
	public static Token readUntil(InputStream data,char[] stops) throws IOException {
		String      line = "";
		int         r    = -1;
		char        c    = 0;
		boolean   stop   = false;
		
		while ((r = data.read()) != -1 && !stop) {
			c = (char)r;
			for (int ci=0;ci<stops.length;ci++) {
				if (stops[ci] == c) {
					return new Token(line,r);
				}
			}
			line += c;
		}
		System.out.println();
		return new Token(line,r);
	}
	
	/**
	 * parse a content param section 
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static List<Parameter> parseLineParams(InputStream data) throws IOException {
		Token       step;
		boolean     key  = true;
		
		LinkedList<Parameter> ret = new LinkedList<Parameter>();
		Parameter               p = new Parameter();
		
		while (true) {
			step = readUntil(data, ":;=".toCharArray());
			// TODO check cr lr or lr
			System.out.println("pstep: " + step.value + ":" + step.last);
			if (step.last == '\r' || step.last == '\n' || step.last=='=' || step.last==';' || step.last == ':') {
				if (key) { 
					p.key = step.value;
				} else {
					p.value = step.value;
					ret.add(p);
					p = new Parameter();
				}
				if (step.last == '\n' || step.last == '\r' || step.last == ':' || step.last == -1) {
					if (step.last == '\r') 
						data.read();
					break;
				} 	
			}
			key = !key;
		}
		
		return ret;
	}
	
	/**
	 * parse a content line
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static ContentLine parseContentLine(InputStream data) throws IOException {
		ContentLine cline;
		Token       step;

		step  = readUntil(data, ":;\r\n".toCharArray());
		cline = new ContentLine(step.value);
		
		System.out.println("cls: " + step.value);
		// check if line end
		if (step.last == -1 || step.last == '\n')
			return cline;
		else if (step.last == '\r') {
			data.read(); // skip \n byte
		}
		
		// check if params
		if (step.last == ';') {
			cline.params = parseLineParams(data);
		} 
		
		Token val = readUntil(data, "\r\n".toCharArray());
		cline.value = val.value;
		if (val.last == '\r')
			data.read();
		
		return cline;
	}
	
}