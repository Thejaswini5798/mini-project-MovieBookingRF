package com.tutorials.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tutorials.jdbc.bo.Shows;
import com.tutorials.jdbc.bo.Ticket;

public class TicketDAO
{
	static Connection conn = null;
	static String connURL1 = "jdbc:mysql://localhost:3306/demo6?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
	static String connURL = "jdbc:mysql://localhost:3306/demo6";
    static String userName = "root";
	static String password = "root123";
 	//static String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	static String JDBC_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	
	//TODO Revisit this later to make a proper instance method
	//after the CRUD functionalities are completed.
	/*public static UsersDAO _instance = null;
	
	public MovieDAO()
	{
		if(null==_instance) {
			_instance = new MovieDAO();
		}
	}*/
	
	public static void main(String... args)
	{
		try {
			connectToDB();
			System.out.println("Connection to the MySQL DB is successful!");
			System.out.println("conn : " + conn);
		}catch(Exception exception) {
			System.err.println("Exception occurred while making a connection..");
			System.err.println("Error Message :  " + exception.getMessage());
			exception.printStackTrace();//NOT recommended in PROD codebase, as it reveals the code structure
		}

		List<Ticket> ticketList = listAll();
		System.out.println("ticketList size :  " + ticketList.size());

		if(ticketList.size()>0) {
			for(Ticket ticket : ticketList) {
				System.out.println(ticket);
			}
		}
	}

	public static void connectToDB() throws Exception
	{	
		Class.forName(JDBC_DRIVER_CLASS);
		conn = DriverManager.getConnection(connURL, userName, password);
	}

	public static void unUsedCreateTable() throws SQLException
	{
		String SQL = "CREATE TABLE TICKET( TICKETID INT AUTO_INCREMENT PRIMARY KEY,TICKET_CODE varchar(20) NOT NULL,BOOKINGID INT NOT NULL,PRICE int NOT NULL,TICKETCLASS VARCHAR(10) NOT NULL,NO_OF_TICKETS INT NOT NULL,SEATS_REMAININGGOLD INT NOT NULL CHECK (SEATS_REMAININGGOLD >= 0),SEATS_REMAININGSILVER INT NOT NULL CHECK (SEATS_REMAININGSILVER >= 0),THEATRENAME VARCHAR(30) NOT NULL)";
		System.out.println(SQL);
	}
	
	public static List<Ticket> listAll()
	{
		System.out.println("--- listAll invoked --- ");
		
		String sql = "SELECT * FROM TICKET";
	
		/*if(null==conn) {
			throw new RuntimeException("DB Connection has not yet been established!");	
		}*/

		Statement stmt = null; 
		ResultSet rs = null;
		List<Ticket> ticketList = new ArrayList<>();
		Ticket ticket = null;
		
		System.out.println("Connection Object : " + conn);

		try {
			
			if(null==conn || conn.isClosed())
			{
				System.out.println("Connection is null, creating a new one");
				connectToDB();
				System.out.println("Connection Object : " + conn);
			}
			
			stmt = conn.createStatement();	
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				//create a new instance of Person whenever there is a data from the ResultSet
				ticket = new Ticket();
				
				ticket.setTicketid(rs.getInt("Ticketid"));//Users.setId(rs.getInt(1));
				ticket.setTicket_code(rs.getString("Ticket_code"));
				ticket.setBookingid(rs.getInt("Bookingid"));
				ticket.setPrice(rs.getInt("Price"));
				ticket.setTicketclass(rs.getString("Ticketclass"));
				ticket.setNo_of_tickets(rs.getInt("No_of_tickets"));
				ticket.setSeats_remaininggold(rs.getInt("Seats_remaininggold"));
				ticket.setSeats_remainingsilver(rs.getInt("Seats_remainingsilver"));
				ticket.setTheatrename(rs.getString("Theatrename"));
				//Add the Users object into the Collection
				ticketList.add(ticket);
			}
		}catch(SQLException sqlEx) {
			System.err.println("SQLException occurred while fetching the rows from the table");
			System.err.println("Message : " + sqlEx.getMessage());
			sqlEx.printStackTrace();
		}catch(Exception ex) {
			System.err.println("Exception occurred while fetching the rows from the table");
			System.err.println("Message : " + ex.getMessage());
			ex.printStackTrace();
		}finally {
			try {
				if(null!=rs) rs.close();
				if(null!=stmt) stmt.close();
				if(null!=conn) conn.close();
			}catch(SQLException sqlException) {
				System.err.println("Exception occurred while closing the JDBC Resources");
				System.err.println("Message : " + sqlException.getMessage());
			}
		}

		//System.out.println("Users List size : " + personList.size());

		return ticketList;
	}	
	
	
	public static Ticket getTicketById(int idParm)
	{
		System.out.println("--- getTicketById - idParam :: " + idParm);
		
		String sql = "SELECT * FROM TICKET WHERE TICKETID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Ticket ticket = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1,idParm);

			rs = stmt.executeQuery();
		
		int ticketid,bookingid, price, no_of_tickets,seats_remaininggold,seats_remainingsilver;
			String ticket_code,ticketclass, theatrename;
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				ticket = new Ticket();

				ticketid = rs.getInt(1);
				ticket_code = rs.getString(2);
				 bookingid = rs.getInt(3);
				 price = rs.getInt(4);
				 ticketclass =rs.getString(5);
				 no_of_tickets = rs.getInt(6);
				 seats_remaininggold=rs.getInt(7);
				 seats_remainingsilver = rs.getInt(8);
				 theatrename=rs.getString(9);
				
				 ticket.setTicketid(ticketid);
				 ticket.setTicket_code(ticket_code);
				 ticket.setBookingid( bookingid);
				 ticket.setPrice(price);
				 ticket.setTicketclass(ticketclass);
				 ticket.setNo_of_tickets(no_of_tickets);
				 ticket.setSeats_remaininggold(seats_remaininggold);
				 ticket.setSeats_remainingsilver(seats_remainingsilver);
				 ticket.setTheatrename(theatrename);
					
				count++;
			}
		}catch(SQLException sqlException) {
			System.err.println("SQLException occurred while reading the data from the Database Table");
			System.err.println("Message : " + sqlException.getMessage());
		}catch(Exception exception) {
			System.err.println("Exception occurred while reading the data from the Database Table");
			System.err.println("Message : " + exception.getMessage());
		}finally {
			try {
				if(null!=rs) rs.close();
				if(null!=stmt) stmt.close();
				if(null!=conn) conn.close();
			}catch(SQLException sqlException) {
				System.err.println("Exception occurred while reading the data from the Database Table");
				System.err.println("Message : " + sqlException.getMessage());
			}finally {
				try {
					if(null!=rs) rs.close();
					if(null!=stmt) stmt.close();
					if(null!=conn) conn.close();
				}catch(SQLException sqlException) {
					System.err.println("Exception occurred while closing the JDBC Resources");
					System.err.println("Message : " + sqlException.getMessage());
				}
			}
		}
		if(count==0) {
			System.out.println("There are no matching records for the criteria specified!");
		}else {
			System.out.println("Data read from the table successfully!");
		}


		return ticket;
	}
	
	public static int createTicket(Ticket ticket)
	{
		System.out.println("--- createticket - ticket :: " +ticket);
		
		String sql = "INSERT INTO TICKET (TICKET_CODE ,BOOKINGID,PRICE,TICKETCLASS ,NO_OF_TICKETS,,SEATS_REMAININGGOLD,SEATS_REMAININGSILVER,THEATRENAME  )" + 
				" VALUES (?, ?, ?, ?,?,?,?,?)";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		int lastInsertedId=0;
				int recordsInserted = 0;
		
		try {
			
			if(null==conn)
			{
				connectToDB();
			}
			
			System.out.println("AutoCommit ? : " + conn.getAutoCommit());
			/*conn.setAutoCommit(true);
			System.out.println("(2) AutoCommit ? : " + conn.getAutoCommit());*/
			
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, ticket.getTicket_code());
			
			pStmt.setInt(2, ticket.getBookingid());
			pStmt.setInt(3, ticket.getPrice());
			pStmt.setString(4, ticket.getTicketclass());
			pStmt.setInt(5, ticket.getNo_of_tickets());
			pStmt.setInt(6, ticket.getSeats_remaininggold());
			pStmt.setInt(7, ticket.getSeats_remainingsilver());
			pStmt.setString(8, ticket.getTheatrename());
			
			
			recordsInserted = pStmt.executeUpdate();
			//lastInsertedId = pStmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			System.out.println("recordsInserted : " + recordsInserted);
			
		    rs = pStmt.executeQuery("SELECT LAST_INSERT_ID()");

		    if (rs.next()) {
		    	lastInsertedId =rs.getInt(1);
		    } else {
		        System.err.println("There was no record inserted in this session!");
		    }

		    System.out.println("Key returned from " +
		                       "'SELECT LAST_INSERT_ID()': " +
		                       lastInsertedId);
			
		}catch(SQLException sqlException) {
			System.err.println("SQL Exception occurred while reading the data from the Database Table");
			System.err.println("Message : " + sqlException.getMessage());
		}catch(Exception exception) {
			System.err.println("Exception occurred while reading the data from the Database Table");
			System.err.println("Message : " + exception.getMessage());
		} finally {
			try {
				if(null!=pStmt) pStmt.close();
				if(null!=conn) conn.close();
			}catch(SQLException sqlException) {
				System.err.println("Exception occurred while inserting the data into the Database Table");
				System.err.println("Message : " + sqlException.getMessage());
			}finally {
				try {
					if(null!=pStmt) pStmt.close();
					if(null!=conn) conn.close();
				}catch(SQLException sqlException) {
					System.err.println("Exception occurred while closing the JDBC Resources");
					System.err.println("Message : " + sqlException.getMessage());
				}
			}
		}
		
		System.out.println("Records Inserted with the Id : " + lastInsertedId);
		//System.out.println("Records Inserted  : " + recordsInserted);
		
		return lastInsertedId;
	}
	
	public static int createPersonFlavor1ThrowsException(Ticket ticket) throws Exception
	{
		System.out.println("--- createPersonFlavor1ThrowsException -  ticket :: " +  ticket);
		
		String sql =  "INSERT INTO TICKET (TICKET_CODE ,BOOKINGID,PRICE,TICKETCLASS ,NO_OF_TICKETS,,SEATS_REMAININGGOLD,SEATS_REMAININGSILVER,THEATRENAME  )" + 
				" VALUES (?, ?, ?, ?,?,?,?,?)";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		int lastInsertedId=0;
		int recordsInserted = 0;
			
		if(null==conn)
		{
			connectToDB();
		}
		
		System.out.println("AutoCommit ? : " + conn.getAutoCommit());
		/*conn.setAutoCommit(true);
		System.out.println("(2) AutoCommit ? : " + conn.getAutoCommit());*/
		
		pStmt = conn.prepareStatement(sql);
		
	

		pStmt.setString(1, ticket.getTicket_code());
		
		pStmt.setInt(2, ticket.getBookingid());
		pStmt.setInt(3, ticket.getPrice());
		pStmt.setString(4, ticket.getTicketclass());
		pStmt.setInt(5, ticket.getNo_of_tickets());
		pStmt.setInt(6, ticket.getSeats_remaininggold());
		pStmt.setInt(7, ticket.getSeats_remainingsilver());
		pStmt.setString(8, ticket.getTheatrename());
		
		recordsInserted = pStmt.executeUpdate();
		//lastInsertedId = pStmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		System.out.println("recordsInserted : " + recordsInserted);
		
	    rs = pStmt.executeQuery("SELECT LAST_INSERT_ID()");

	    if (rs.next()) {
	    	lastInsertedId = rs.getInt(1);
	    } else {
	        System.err.println("There was no record inserted in this session!");
	    }

	    System.out.println("Key returned from " +
	                       "'SELECT LAST_INSERT_ID()': " +
	                       lastInsertedId);
	    
	    if(null!=pStmt) pStmt.close();
		if(null!=conn) conn.close();
			
		
		
		System.out.println("Records Inserted with the Id : " + lastInsertedId);
		//System.out.println("Records Inserted  : " + recordsInserted);
		
		return lastInsertedId;
	}

	
}