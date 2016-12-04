package main;

import java.util.Comparator;

/**
 * Created on 04.12.2016.
 */
public class HelloWorldComparator implements Comparator<String> {

	public int compare(String o1, String o2) {
		return o1.equals(o2)?0:
				(o1.length()-o2.length())==0?-1:
						o1.length()-o2.length();
	}
}
