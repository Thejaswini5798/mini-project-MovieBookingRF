package com.tutorials.jdbc.bo;

public class Booking {
	private int bookingid;
	private String booking_code ;
	private int no_of_tickets;
	private int total_cost;
	private String card_number;
	private String name_oncard; 
	private int id;
	private int showsid;
	
	
	public int getBookingid() {
		return bookingid;
	}
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	public String getBooking_code() {
		return booking_code;
	}
	public void setBooking_code(String booking_code) {
		this.booking_code = booking_code;
	}
	public int getNo_of_tickets() {
		return no_of_tickets;
	}
	public void setNo_of_tickets(int no_of_tickets) {
		this.no_of_tickets = no_of_tickets;
	}
	public int getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getName_oncard() {
		return name_oncard;
	}
	public void setName_oncard(String name_oncard) {
		this.name_oncard = name_oncard;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShowsid() {
		return showsid;
	}
	public void setShowsid(int showsid) {
		this.showsid = showsid;
	}
	
	public Booking() {
		System.out.println("Booking() - instantiated..");
	}
	public Booking(int bookingid,String booking_code,int no_of_tickets,int total_cost,String card_number,String name_oncard,int id,int showsid ) {
		System.out.println("Booking( bookingid,booking_code, no_of_tickets, total_cost, card_number, name_oncard, id,showsid) - instantiated..");
		
		this.bookingid=bookingid;
		this.booking_code =booking_code;
		this.no_of_tickets = no_of_tickets;
		this.total_cost = total_cost ;
		this.card_number = card_number ;
		this. name_oncard= name_oncard;
		this.id=id;
		this.showsid=showsid;
		
		}
			public String toString() {
				return "[Booking] hashCode=" + this.hashCode()
				+",bookingid ="+bookingid
					+",booking_code ="+booking_code
					+",no_of_tickets ="+ no_of_tickets
					+ ",total_cost   = " +total_cost 
					+ ",card_number  = " +card_number
				+ ",name_oncard= " +name_oncard
				+ ",id = " +id
				+",showsid ="+ showsid;
		
}
	
}








































