package com.tutorials.jdbc.bo;


public class Shows {
	private int showsid;
	private String showscode ;
	private String showtime;
	private String showdate;
	private int screenid;
	private int movie_id;
	private String theatrename;
	private String movie_name; 
	private int seats_remaining;

	public int getShowsid() {
		return showsid;
	}

	public void setShowsid(int showsid) {
		this.showsid = showsid;
	}

	public String getShowscode() {
		return showscode;
	}

	public void setShowscode(String showscode) {
		this.showscode = showscode;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}



	public String getShowdate() {
		return showdate;
	}

	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}

	public int getScreenid() {
		return screenid;
	}

	public void setScreenid(int screenid) {
		this.screenid = screenid;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getTheatrename() {
		return theatrename;
	}

	public void setTheatrename(String theatrename) {
		this.theatrename = theatrename;
	}
	
	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public int getSeats_remaining() {
		return seats_remaining;
	}

	public void setSeats_remaining(int seats_remaining) {
		this.seats_remaining = seats_remaining;
	}
	public Shows() {
		System.out.println("Shows() - instantiated..");
	}
	public Shows(int showsid,String showscode,String showtime,String showdate,int screenid,int movie_id,String theatrename,String movie_name, int seats_remaining) {
		System.out.println("Shows(showsid, showscode,showtime, showdate,screenid, movie_id,theatrename,movie_name,seats_remaining ) - instantiated..");
		
		this.showsid=showsid;
		this.showscode = showscode;
		this.showtime = showtime;
		this.showdate = showdate ;
		this.screenid=screenid;
		this. movie_id=movie_id;
		this.theatrename=theatrename;
		this. movie_name=movie_name;
		this.seats_remaining =seats_remaining ;
	}
		public String toString() {
			return "[Shows] hashCode=" + this.hashCode()
			+",showsid ="+ showsid 
				+",showscode ="+showscode
				+",showtime ="+ showtime
				+ ",showdate   = " +showdate  
			+ ",screenid = " + screenid 
		+ ", movie_id= " + movie_id
			+ ",theatrename= " + theatrename 
			+ ", movie_name= " + movie_name
			+ ",seats_remaining  = " +seats_remaining;
		}

		
}
	
	








































