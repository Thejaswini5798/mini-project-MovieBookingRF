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


public class ShowsDAO
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

		List<Shows> showsList = listAll();
		System.out.println("showsList size :  " + showsList.size());

		if(showsList.size()>0) {
			for(Shows shows : showsList) {
				System.out.println(shows);
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
		String SQL = "CREATE TABLE SHOWS( SHOWSID  INT AUTO_INCREMENT PRIMARY KEY,SHOWSCODE VARCHAR(10) NOT NULL,SHOWTIME VARCHAR(15) NOT NULL,SHOWDATE  VARCHAR(15) NOT NULL,SCREENID INT NOT NULL,MOVIE_ID INT NOT NULL,THEATRENAME VARCHAR(30) NOT NULL, MOVIE_NAME VARCHAR(20) NOT NULL,SEATS_REMAINING INT NOT NULL CHECK (SEATS_REMAINING >= 0),FOREIGN KEY(SCREENID)REFERENCES SCREENS(SCREENID) ON DELETE CASCADE ON UPDATE CASCADE,FOREIGN KEY(MOVIE_ID)REFERENCES MOVIES(MOVIE_ID) ON DELETE CASCADE ON UPDATE CASCADE)";
		System.out.println(SQL);
	}
	
	public static List<Shows> listAll()
	{
		System.out.println("--- listAll invoked --- ");
		
		String sql = "SELECT * FROM  SHOWS";
	
		/*if(null==conn) {
			throw new RuntimeException("DB Connection has not yet been established!");	
		}*/

		Statement stmt = null; 
		ResultSet rs = null;
		List<Shows> showsList = new ArrayList<>();
		Shows shows= null;																					
		
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
				shows = new Shows();
				
				
				shows.setShowsid(rs.getInt("Showsid"));//Users.setId(rs.getInt(1));
				shows.setShowscode(rs.getString("Showscode"));
				shows.setShowtime(rs.getString("Showtime"));
				shows.setShowdate(rs.getString("Showdate"));
				shows.setScreenid(rs.getInt("Screenid"));
				shows.setMovie_id(rs.getInt("Movie_id"));
				shows.setTheatrename(rs.getString("Theatrename"));
				shows.setMovie_name(rs.getString("Movie_name"));
				shows.setSeats_remaining(rs.getInt("Seats_remaining"));
				//Add the Users object into the Collection
				showsList.add(shows);
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

		return showsList;
	}	
	
	public static Shows getEmployeeById(int idParm)
	{
		System.out.println("--- getEmployeeById - idParam :: " + idParm);
		
		String sql = "SELECT * FROM SHOWS WHERE SHOWSID =?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Shows shows = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1,idParm);

			rs = stmt.executeQuery();
		
			int showsid,seats_remaining,screenid,movie_id;
			String showscode, theatrename,movie_name,showtime,showdate;
			
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				shows = new Shows();
				
				

				 showsid = rs.getInt(1);
				 showscode = rs.getString(2);
				 showtime = rs.getString(3);
				 showdate = rs.getString(4);
			
				
				 screenid = rs.getInt(5);
				 movie_id = rs.getInt(6);
				 theatrename=rs.getString(7);
				 movie_name= rs.getString(8);
				 seats_remaining=rs.getInt(9);

				 	shows.setShowsid(showsid);
					shows.setShowscode(showscode);
					 
					shows.setShowtime(showtime);
					shows.setShowdate(showdate);
					
				
					shows.setScreenid(screenid);
					shows.setMovie_id(movie_id);
					shows.setTheatrename(theatrename);
					shows.setMovie_name(movie_name);
					shows.setSeats_remaining(seats_remaining);

			
			
				
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


		return shows;
	}
	
	public static int createTheatres(Shows shows)
	{
		System.out.println("--- create shows- shows :: " +shows);
		
		String sql =" INSERT INTO  SHOWS(SHOWSCODE,SHOWTIME,SHOWDATE,SCREENID,MOVIE_ID,THEATRENAME, MOVIE_NAME,SEATS_REMAINING) "+ 
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
			
			
			
			
			pStmt.setString(1, shows.getShowscode());
			pStmt.setString(2, shows.getShowtime());
			pStmt.setString(3,  shows.getShowdate());
			
			
			pStmt.setInt(4,  shows.getScreenid());
			pStmt.setInt(5,  shows.getMovie_id() );
			pStmt.setString(6,  shows.getTheatrename());
			pStmt.setString(7, shows.getMovie_name());
			pStmt.setInt(8,  shows.getSeats_remaining());
			
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
	
	public static int createPersonFlavor1ThrowsException(Shows shows) throws Exception
	{
		System.out.println("--- createPersonFlavor1ThrowsException - shows :: " +shows);
		
		String sql = " INSERT INTO  SHOWS(SHOWSCODE,SHOWTIME,SHOWDATE,SCREENID,MOVIE_ID,THEATRENAME, MOVIE_NAME,SEATS_REMAINING) "+ 
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
		
		
		pStmt.setString(1, shows.getShowscode());
		pStmt.setString(2, shows.getShowtime());
		pStmt.setString(3,  shows.getShowdate());
		pStmt.setInt(4,  shows.getScreenid());
		pStmt.setInt(5,  shows.getMovie_id() );
		pStmt.setString(6,  shows.getTheatrename());
		pStmt.setString(7, shows.getMovie_name());
		pStmt.setInt(8,  shows.getSeats_remaining());
		
		
		
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
	public static void updateScreens(Shows shows) throws Exception
	{
		System.out.println("--- updateTheatres - shows :: " + shows);
	
		String sql = "UPDATE SHOWS SET "
				+ "SHOWSCODE=?,SHOWTIME=?,SHOWDATE=?,SCREENID=?,MOVIE_ID=?,THEATRENAME=?, MOVIE_NAME=?,SEATS_REMAINING=?"
				+ " WHERE SHOWSID=?";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		int recordsUpdated = 0;
			
		if(null==conn || conn.isClosed())
		{
			connectToDB();
		}
		
		pStmt = conn.prepareStatement(sql);
		

		pStmt.setString(1, shows.getShowscode());
		pStmt.setString(2, shows.getShowtime());
		pStmt.setString(3,  shows.getShowdate());
	
		
		pStmt.setInt(4, shows.getScreenid());
		pStmt.setInt(5, shows.getMovie_id() );
		pStmt.setString(6, shows.getTheatrename());
		pStmt.setString(7,shows.getMovie_name());
		pStmt.setInt(8, shows.getSeats_remaining());
		pStmt.setInt(9, shows.getShowsid());

	
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
		
		String sql = "DELETE FROM SHOWS WHERE SHOWSID=?";

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
	

	

	public static List<Shows> getTicketByMoviename(String movie_nameParam)
	{
		System.out.println("--- getEmployeeById - movie_nameParam :: " + movie_nameParam);
		
		String sql = "SELECT * FROM SHOWS WHERE MOVIE_NAME=? ";

		System.out.println("SQL Query :: " + sql);
		
		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Shows> showsList = new ArrayList<>();
		Shows shows= null;	
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
				shows = new Shows();
				shows.setShowsid(rs.getInt("Showsid"));//Users.setId(rs.getInt(1));
				shows.setShowscode(rs.getString("Showscode"));
				shows.setShowtime(rs.getString("Showtime"));
				shows.setShowdate(rs.getString("Showdate"));
				
				
				shows.setScreenid(rs.getInt("Screenid"));
				shows.setMovie_id(rs.getInt("Movie_id"));
				shows.setTheatrename(rs.getString("Theatrename"));
				shows.setMovie_name(rs.getString("Movie_name"));
				shows.setSeats_remaining(rs.getInt("Seats_remaininggold"));
			
				
				
				
				
				count++;
				showsList.add(shows);
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


		return showsList;
	
	}	
	public static void updateShows(int nooftickets, int showsid) throws Exception
	{
		//System.out.println("--- updateTheatres - shows :: " + shows);
	
		String sql ="UPDATE  SHOWS SET  SEATS_REMAINING= SEATS_REMAINING-? WHERE SHOWSID=?";
 

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		int recordsUpdated = 0;
			
		if(null==conn || conn.isClosed())
		{
			connectToDB();
		}
		
		pStmt = conn.prepareStatement(sql);
		

		
		pStmt.setInt(1, nooftickets);
		pStmt.setInt(2, showsid);

	
		recordsUpdated = pStmt.executeUpdate();
		//lastInsertedId = pStmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		System.out.println("recordsUpdated : " + recordsUpdated);
	
	    if(null!=pStmt) pStmt.close();
		if(null!=conn) conn.close();
			
		System.out.println("recordsUpdated  : " + recordsUpdated);
	}
	
}


	

