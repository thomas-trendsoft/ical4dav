package ical4dav.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

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
			// TODO check cr lr or lr
			c = (char)r;
			for (char s : stops) {
				if (s == c) {
					return new Token(line,r);
				}
			}
			line += c;
		}		
		return new Token(line,r);
	}
	
	/**
	 * parse a content param section 
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static List<Param> parseLineParams(InputStream data) throws IOException {
		Token       step;
		boolean     key  = true;
		
		LinkedList<Param> ret = new LinkedList<Param>();
		Param               p = new Param();
		
		while (true) {
			step = readUntil(data, ",)=".toCharArray());
			// TODO check cr lr or lr
			System.out.println("pstep: " + step.value + ":" + step.last);
			if (step.last == '\n' || step.last=='=' || step.last==')' || step.last == ',') {
				if (key) { 
					p.key = step.value;
				} else {
					p.value = step.value;
					ret.add(p);
					p = new Param();
				}
				if (step.last == '\n' || step.last == ')' || step.last == -1) {
					if (step.last == ')') 
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

		step  = readUntil(data, ":(\n".toCharArray());
		cline = new ContentLine(step.value);
		
		System.out.println("cl: " + step.value);
		// check if line end
		if (step.last == -1 || step.last == '\n')
			return cline;
		
		// check if params
		if (step.last == '(') {
			cline.params = parseLineParams(data);
		} 
		
		Token val = readUntil(data, "\n".toCharArray());
		cline.value = val.value;
		
		return cline;
	}
	
}