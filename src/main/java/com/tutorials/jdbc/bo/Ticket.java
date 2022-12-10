package com.tutorials.jdbc.bo;

public class Ticket
{
	private int ticketid;
	
	private String ticket_code;

	private int bookingid;
	
	private  int price;
	
	private String ticketclass;
	
	private int no_of_tickets;
	private int seats_remaininggold;
	private int seats_remainingsilver;
	private String theatrename;
	
	public int getTicketid() {
		return ticketid;
	}
	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}
	public String getTicket_code() {
		return ticket_code;
	}
	public void setTicket_code(String ticket_code) {
		this.ticket_code = ticket_code;
	}
	
	public int getBookingid() {
		return bookingid;
	}
	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getTicketclass() {
		return ticketclass;
	}
	public void setTicketclass(String ticketclass) {
		this.ticketclass = ticketclass;
	}
	public int getNo_of_tickets() {
		return no_of_tickets;
	}
	public void setNo_of_tickets(int no_of_tickets) {
		this.no_of_tickets = no_of_tickets;
	}
	
	public int getSeats_remaininggold() {
		return seats_remaininggold;
	}

	public void setSeats_remaininggold(int seats_remaininggold) {
		this.seats_remaininggold = seats_remaininggold;
	}

	public int getSeats_remainingsilver() {
		return seats_remainingsilver;
	}

	public void setSeats_remainingsilver(int seats_remainingsilver) {
		this.seats_remainingsilver = seats_remainingsilver;
	}

	public String getTheatrename() {
		return theatrename;
	}

	public void setTheatrename(String theatrename) {
		this.theatrename = theatrename;
	}
	
	
	
	public Ticket() {
		System.out.println("Ticket() - instantiated..");
	}
	public Ticket(int ticketid,String ticket_code,int bookingid, int price,String ticketclass,int no_of_tickets, int seats_remaininggold,int seats_remainingsilver,String theatrename) {
		System.out.println("Ticket(  ticketid,ticket_code, bookingid,price,ticketclass , no_of_tickets,seats_remaininggold,seats_remainingsilver,theatrename) - instantiated..");
	

		this.ticketid=ticketid;
		this.ticket_code = ticket_code;
		this.bookingid =bookingid;
		this.price=price;
		this.ticketclass = ticketclass;
		this.no_of_tickets = no_of_tickets;
		this.seats_remaininggold =seats_remaininggold ;
		this.seats_remainingsilver=seats_remainingsilver;
		this.theatrename=theatrename;
	}
	
	public String toString() {
		return "[ticket] hashCode=" + this.hashCode()
			+ ", Ticketid = " + ticketid
			+",Ticket_code ="+ ticket_code
			+ ",Bookingid= " +bookingid
			+ ", Ticketclass="+ ticketclass
		+",no_of_tickets ="+ no_of_tickets
		+ ",seats_remaininggold  = " +seats_remaininggold
		+ ",seats_remainingsilver= " +seats_remainingsilver
		+ ",theatrename= " + theatrename ;
			
	}
}