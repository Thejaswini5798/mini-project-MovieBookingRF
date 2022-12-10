package com.tutorials.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.tutorials.jdbc.bo.Booking;

public class BookingDAO
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

		List<Booking> bookingList = listAll();
		System.out.println("bookingList size :  " + bookingList.size());

		if(bookingList.size()>0) {
			for(Booking booking : bookingList) {
				System.out.println(booking);
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
		String SQL = "CREATE TABLE BOOKING(BOOKINGID  INT AUTO_INCREMENT PRIMARY KEY,BOOKING_CODE VARCHAR(10),NO_OF_TICKETS INT NOT NULL,TOTAL_COST INT NOT NULL,CARD_NUMBER VARCHAR(19),NAME_ONCARD VARCHAR(21),ID INT NOT NULL,SHOWSID INT  NULL)";
		System.out.println(SQL);
	}
	
	public static List<Booking> listAll()
	{
		System.out.println("--- listAll invoked --- ");
		
		String sql = "SELECT * FROM  BOOKING";
	
		/*if(null==conn) {
			throw new RuntimeException("DB Connection has not yet been established!");	
		}*/

		Statement stmt = null; 
		ResultSet rs = null;
		List<Booking> bookingList = new ArrayList<>();
		Booking booking= null;
		
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
				booking = new Booking();
				
				booking.setBookingid(rs.getInt("Bookingid"));//Users.setId(rs.getInt(1));
				booking.setBooking_code(rs.getString("Booking_code"));
				booking.setNo_of_tickets(rs.getInt("No_of_tickets"));
				booking.setTotal_cost(rs.getInt("Total_cost"));
				booking.setCard_number(rs.getString("Card_number"));
				booking.setName_oncard(rs.getString("Name_oncard"));
				booking.setId(rs.getInt("Id"));
				booking.setShowsid(rs.getInt("Showsid"));
				//Add the Users object into the Collection
				bookingList.add(booking);
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

		return bookingList;
	}	
	
	
	public static Booking getEmployeeById(int idParm)
	{
		System.out.println("--- getEmployeeById - idParam :: " + idParm);
		
		String sql = "SELECT * FROM BOOKING WHERE BOOKINGID =?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Booking booking = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1,idParm);

			rs = stmt.executeQuery();
		
			int bookingid,no_of_tickets,total_cost, id,showsid;
			String booking_code,card_number,name_oncard;
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				booking = new Booking();

				bookingid= rs.getInt(1);
				booking_code = rs.getString(2);
				no_of_tickets = rs.getInt(3);
				total_cost = rs.getInt(4);
				card_number =rs.getString(5);
				name_oncard = rs.getString(6);
				id = rs.getInt(7);
				showsid = rs.getInt(8);


				    booking.setBookingid(bookingid);
				    booking.setBooking_code(booking_code);
				    booking.setNo_of_tickets(no_of_tickets);
				    booking.setTotal_cost(total_cost);
				    booking.setCard_number(card_number);
				    booking.setName_oncard(name_oncard);
				    booking.setId(id);
				    booking.setShowsid(showsid);
			
				
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


		return booking;
	}
	
	public static int createTheatres(Booking booking)
	{
		System.out.println("--- create screens- booking :: " +booking);
		
		String sql ="INSERT INTO  BOOKING(BOOKING_CODE,NO_OF_TICKETS,TOTAL_COST,CARD_NUMBER,NAME_ONCARD,ID,SHOWSID)  "+ 
				" VALUES (?, ?, ?, ?,?,?,?)";

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
			
			pStmt.setString(1,  booking.getBooking_code());
			pStmt.setInt(2, booking.getNo_of_tickets());
			pStmt.setInt(3,  booking.getTotal_cost());
			pStmt.setString(4,  booking.getCard_number());
			pStmt.setString(5,  booking.getName_oncard());
			pStmt.setInt(6,  booking.getId());
			pStmt.setInt(7,  booking.getShowsid());
			
			
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
	
	public static int createPersonFlavor1ThrowsException(Booking booking) throws Exception
	{
		System.out.println("--- createPersonFlavor1ThrowsException - booking :: " +booking);
		
		String sql = "INSERT INTO  BOOKING(BOOKING_CODE,NO_OF_TICKETS,TOTAL_COST,CARD_NUMBER,NAME_ONCARD,ID,SHOWSID)  "+ 
				" VALUES (?, ?, ?, ?,?,?,?)";

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
		
		pStmt.setString(1,  booking.getBooking_code());
		pStmt.setInt(2, booking.getNo_of_tickets());
		pStmt.setInt(3,  booking.getTotal_cost());
		pStmt.setString(4,  booking.getCard_number());
		pStmt.setString(5,  booking.getName_oncard());
		pStmt.setInt(6,  booking.getId());
		pStmt.setInt(7,  booking.getShowsid());
		
		
		
		
		
		
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
	public static void updateBooking(Booking booking) throws Exception
	{
		System.out.println("--- updateTheatres - booking :: " + booking);
	
		String sql = "UPDATE BOOKING SET "
				+ "BOOKING_CODE =?, NO_OF_TICKETS=?, TOTAL_COST =?, CARD_NUMBER =?, NAME_ONCARD =?, ID=?, SHOWSID =? "
				+ " WHERE BOOKINGID =?";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		int recordsUpdated = 0;
			
		if(null==conn || conn.isClosed())
		{
			connectToDB();
		}
		
		pStmt = conn.prepareStatement(sql);
		
		pStmt.setString(1,booking.getBooking_code());
		pStmt.setInt(2,booking.getNo_of_tickets());
		pStmt.setInt(3,booking.getTotal_cost());
		pStmt.setString(4,booking.getCard_number());
		pStmt.setString(5,booking.getName_oncard());
		pStmt.setInt(6,booking.getId());
		pStmt.setInt(7,booking.getShowsid());
		pStmt.setInt(8,booking.getBookingid());
		
		
		
		
		

	
		recordsUpdated = pStmt.executeUpdate();
		//lastInsertedId = pStmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		System.out.println("recordsUpdated : " + recordsUpdated);
	
	    if(null!=pStmt) pStmt.close();
		if(null!=conn) conn.close();
			
		System.out.println("recordsUpdated  : " + recordsUpdated);
	}
	
	public static int deleteEmployeeById(int idParam)
	{
		System.out.println("--- deleteEmployeeById - idParam :: " + idParam);
		
		String sql = "DELETE FROM BOOKING WHERE BOOKINGID=?";

		System.out.println("SQL Query :: " + sql);

		PreparedStatement pStmt = null;
		int rowsDeleted = 0;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, idParam);
		
			rowsDeleted = pStmt.executeUpdate();
			
		}catch(SQLException sqlException) {
			System.err.println("SQLException occurred while deleting the data from the Database Table");
			System.err.println("Message : " + sqlException.getMessage());
		}catch(Exception exception) {
			System.err.println("Exception occurred while deleting the data from the Database Table");
			System.err.println("Message : " + exception.getMessage());
		}finally {
			try {
				if(null!=pStmt) pStmt.close();
				if(null!=conn) conn.close();
			}catch(SQLException sqlException) {
				System.err.println("Exception occurred while closing the JDBC Resources");
				System.err.println("Message : " + sqlException.getMessage());
			}
		}
		if(rowsDeleted==0) {
			System.out.println("There are no recors deleted");
		}else {
			System.out.println("Record deleted from the table successfully!");
		}

		return rowsDeleted;
	}
	
}


	
