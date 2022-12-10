package com.tutorials.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.tutorials.jdbc.bo.BookSeats;


public class BookSeatsDAO
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

		List<BookSeats> bookseatsList = listAll();
		System.out.println("bookseatsList size :  " + bookseatsList.size());

		if(bookseatsList.size()>0) {
			for(BookSeats bookseats: bookseatsList) {
				System.out.println(bookseats);
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
		String SQL = "CREATE TABLE BOOKSEATS( BOOKID INT AUTO_INCREMENT PRIMARY KEY,THEATRENAME VARCHAR(30) NOT NULL,ADD MOVIE_NAME VARCHAR(20) NOT NULL, SHOWSID INT NOT NULL,SHOWTIME VARCHAR(15) NOT NULL,SHOWDATE  VARCHAR(15) NOT NULL,TOTAL_NO_SEAT INT NOT NULL,NO_OF_TICKETS INT NOT NULL)";
		System.out.println(SQL);
	}
	
	public static List<BookSeats> listAll()
	{
		System.out.println("--- listAll invoked --- ");
		
		String sql = "SELECT * FROM  BOOKSEATS";
	
		/*if(null==conn) {
			throw new RuntimeException("DB Connection has not yet been established!");	
		}*/

		Statement stmt = null; 
		ResultSet rs = null;
		List<BookSeats> bookseatsList = new ArrayList<>();
		BookSeats bookseats= null;																					
		
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
				bookseats = new BookSeats();
				
				
				bookseats.setBookid(rs.getInt("Bookid"));//Users.setId(rs.getInt(1));
				bookseats .setTheatrename(rs.getString("Theatrename"));
				bookseats .setMovie_name(rs.getString("Movie_name"));
				bookseats.setShowsid(rs.getInt("Showsid"));
				bookseats .setShowtime(rs.getString("Showtime"));
				bookseats .setShowdate(rs.getString("Showdate"));
				bookseats.setTotal_no_seat(rs.getInt("Total_no_seat"));
				bookseats.setNo_of_tickets(rs.getInt("No_of_tickets"));
				
				
				
				//Add the Users object into the Collection
				bookseatsList.add(bookseats);
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

		return bookseatsList;
	}	
	
	public static BookSeats getEmployeeById(int idParm)
	{
		System.out.println("--- getEmployeeById - idParam :: " + idParm);
		
		String sql = "SELECT * FROM BOOKSEATS WHERE BOOKID =?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		BookSeats bookseats = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1,idParm);

			rs = stmt.executeQuery();
		
			int bookid,total_no_seat,no_of_tickets,showsid;
			String theatrename, movie_name,showtime,showdate;
			
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				bookseats= new BookSeats();
				
				

				 bookid = rs.getInt(1);
				 theatrename=rs.getString(2);
				 movie_name= rs.getString(3);
				 showsid = rs.getInt(4);
				 showtime = rs.getString(5);
				 showdate = rs.getString(6);
				 total_no_seat = rs.getInt(7);
				 no_of_tickets = rs.getInt(8);
				 

				 bookseats.setBookid(bookid );
				 bookseats.setTheatrename(theatrename);
				 bookseats.setMovie_name(movie_name);
				 bookseats.setShowsid(showsid );
				 bookseats.setShowtime(showtime);
				 bookseats.setShowdate(showdate);
				 bookseats.setTotal_no_seat(total_no_seat);
				 bookseats.setNo_of_tickets( no_of_tickets);
					

			
			
				
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


		return bookseats;
	}
	
	public static int createTheatres(BookSeats bookseats)
	{
		System.out.println("--- create shows- shows :: " +bookseats);
		
		String sql =" INSERT INTO  BOOKSEATS(THEATRENAME,MOVIE_NAME,SHOWSID,SHOWTIME,SHOWDATE,TOTAL_NO_SEAT,NO_OF_TICKETS ) "+ 
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
			
			
			
			pStmt.setString(1,  bookseats.getTheatrename());
			pStmt.setString(2, bookseats.getMovie_name());
			pStmt.setInt(3, bookseats.getShowsid());
			pStmt.setString(4, bookseats.getShowtime());
			pStmt.setString(5,  bookseats.getShowdate());
			
			pStmt.setInt(6, bookseats.getTotal_no_seat());
			pStmt.setInt(7,  bookseats.getNo_of_tickets() );
			
			
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
	
	public static int createPersonFlavor1ThrowsException(BookSeats bookseats) throws Exception
	{
		System.out.println("--- createPersonFlavor1ThrowsException - bookseats :: " +bookseats);
		
		String sql = " INSERT INTO  BOOKSEATS(THEATRENAME,MOVIE_NAME,SHOWSID,SHOWTIME,SHOWDATE,TOTAL_NO_SEAT,NO_OF_TICKETS ) "+ 
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
		

		pStmt.setString(1,  bookseats.getTheatrename());
		pStmt.setString(2, bookseats.getMovie_name());
		pStmt.setInt(3, bookseats.getShowsid());
		pStmt.setString(4, bookseats.getShowtime());
		pStmt.setString(5,  bookseats.getShowdate());
		
		pStmt.setInt(6, bookseats.getTotal_no_seat());
		pStmt.setInt(7,  bookseats.getNo_of_tickets() );
		
		
		
		
		
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

	public static List<BookSeats> getSeatByMoviename(String movie_nameParam)
	{
		System.out.println("--- getEmployeeById - movie_nameParam :: " + movie_nameParam);
		
		String sql = "SELECT * FROM BOOKSEATS WHERE MOVIE_NAME=? ";

		System.out.println("SQL Query :: " + sql);
		
		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<BookSeats> bookseatsList = new ArrayList<>();
		BookSeats bookseats= null;																					
		
		int count = 0;

		
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setString(1,movie_nameParam);

			rs = stmt.executeQuery();
		
		
			
			
		
			while(rs.next()) { //read one full row's data - one column at a time
			
				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				bookseats = new BookSeats();
				bookseats.setBookid(rs.getInt("Bookid"));//Users.setId(rs.getInt(1));
				bookseats .setTheatrename(rs.getString("Theatrename"));
				bookseats .setMovie_name(rs.getString("Movie_name"));
				bookseats.setShowsid(rs.getInt("Showsid"));
				bookseats .setShowtime(rs.getString("Showtime"));
				bookseats .setShowdate(rs.getString("Showdate"));
				bookseats.setTotal_no_seat(rs.getInt("Total_no_seat"));
				bookseats.setNo_of_tickets(rs.getInt("No_of_tickets"));
				
				
			
				
				
				
				
				count++;
				bookseatsList.add(bookseats);
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


		return bookseatsList;
	
	}	
	
	
}


	

