package edu.brilleslange.bl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.*;

public class Record implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String title;
	public String author;
	public String placement;
	
	
}

class PlacementO {
	public ArrayList<String> placements = new ArrayList<String>();
	public String placement = "";
	
	boolean testValue(){
		for(String s : placements){
			placement+= " - " + s; 
		}
		return Pattern.matches("*UREAL*", placement);
	}
}
