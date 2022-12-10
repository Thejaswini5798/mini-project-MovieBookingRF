package com.tutorials.jdbc.bo;

public class Screens {
	private int screenid;
	private String screencode ;
	private int no_of_goldseats;
	private int no_of_silverseats ;
	private int theatreid;
	private String theatrename;
	
	
	public int getScreenid() {
		return screenid;
	}
	public void setScreenid(int screenid) {
		this.screenid = screenid;
	}
	public String getScreencode() {
		return screencode;
	}
	public void setScreencode(String screencode) {
		this.screencode = screencode;
	}
	public int getNo_of_goldseats() {
		return no_of_goldseats;
	}
	public void setNo_of_goldseats(int no_of_goldseats) {
		this.no_of_goldseats = no_of_goldseats;
	}
	public int getNo_of_silverseats() {
		return no_of_silverseats;
	}
	public void setNo_of_silverseats(int no_of_silverseats) {
		this.no_of_silverseats = no_of_silverseats;
	}
	public int getTheatreid() {
		return theatreid;
	}
	public void setTheatreid(int theatreid) {
		this.theatreid = theatreid;
	}
	public String getTheatrename() {
		return theatrename;
	}
	public void setTheatrename(String theatrename) {
		this.theatrename = theatrename;
	}
	public Screens() {
		System.out.println("Screens() - instantiated..");
	}
	
	public Screens(int screenid,String screencode,int no_of_goldseats,int no_of_silverseats,int theatreid,String theatrename) {
		System.out.println("Screens( screenid ,screencode,no_of_goldseats , no_of_silverseats , theatreid ,String theatrename ) - instantiated..");
		
		this.screenid=screenid;
		this.screencode = screencode;
		this.no_of_goldseats = no_of_goldseats;
		this.no_of_silverseats = no_of_silverseats ;
		this. theatreid =theatreid ;
		this. theatrename =theatrename;
}
	public String toString() {
		return "[Screens] hashCode=" + this.hashCode()
			+ ",screenid = " + screenid 
			+",screencode ="+ screencode 
			+",no_of_goldseats ="+ no_of_goldseats
			+ ",no_of_silverseats  = " + no_of_silverseats 
			+ ",theatreid  = " +theatreid 
		+ ",theatrename= " +theatrename ;
	}
}