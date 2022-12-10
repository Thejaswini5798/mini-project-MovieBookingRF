package com.tutorials.jdbc.bo;

public class Theatres {
	private int theatreid ;
	private String theatre_code ;
	private String theatrename ;

	private int no_of_screens;

	private String area;

	public int getTheatreid() {
		return theatreid;
	}

	public void setTheatreid(int theatreid) {
		this.theatreid = theatreid;
	}
	public String getTheatre_code() {
		return theatre_code;
	}

	public void setTheatre_code(String theatre_code) {
		this.theatre_code = theatre_code;
	}

	public String getTheatrename() {
		return theatrename;
	}

	public void setTheatrename(String theatrename) {
		this.theatrename = theatrename;
	}

	public int getNo_of_screens() {
		return no_of_screens;
	}

	public void setNo_of_screens(int no_of_screens) {
		this.no_of_screens = no_of_screens;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	public Theatres() {
		System.out.println("Theatres() - instantiated..");
	}
	
	public Theatres(int theatreid,String theatre_code, String theatrename, int no_of_screens, String area  ) {
		System.out.println("Theatre( theatreid ,theater_code,theatrename , no_of_screens , String area  ) - instantiated..");
	
	
	
		
		this.theatreid=theatreid;
		this.theatre_code = theatre_code;
		this.theatrename = theatrename;
		this.no_of_screens = no_of_screens ;
		this. area   = area  ;
		
	
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		return "[Theater] hashCode=" + this.hashCode()
			+ ",theatreid = " + theatreid 
			+",theatre_code ="+ theatre_code
			+",theatrename ="+ theatrename
			+ ", no_of_screens  = " + no_of_screens 
			+ ", area  = " +area ;
			
	}
}
