package com.tutorials.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.tutorials.jdbc.bo.Screens;

public class ScreensDAO
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

		List<Screens> screensList = listAll();
		System.out.println("screensList size :  " + screensList.size());

		if(screensList.size()>0) {
			for(Screens screens : screensList) {
				System.out.println(screens);
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
		String SQL = "CREATE TABLE SCREENS( SCREENID INT AUTO_INCREMENT PRIMARY KEY,SCREENCODE varchar(5),NO_OF_GOLDSEATS INT NOT NULL,NO_OF_SILVERSEATS INT NOT NULL,THEATREID INT NOT NULL, THEATRENAME VARCHAR(30) NOT NULL,FOREIGN KEY(THEATREID) REFERENCES THEATRES(THEATREID) ON DELETE CASCADE ON UPDATE CASCADE)";
		System.out.println(SQL);
	}
	
	public static List<Screens> listAll()
	{
		System.out.println("--- listAll invoked --- ");
		
		String sql = "SELECT * FROM  SCREENS";
	
		/*if(null==conn) {
			throw new RuntimeException("DB Connection has not yet been established!");	
		}*/

		Statement stmt = null; 
		ResultSet rs = null;
		List<Screens> screensList = new ArrayList<>();
		Screens screens= null;
		
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
				screens = new Screens();
				
				screens.setScreenid(rs.getInt("Screenid"));//Users.setId(rs.getInt(1));
				screens.setScreencode(rs.getString("Screencode"));
				screens.setNo_of_goldseats(rs.getInt("No_of_goldseats"));
				screens.setNo_of_silverseats(rs.getInt("No_of_silverseats"));
				screens.setTheatreid(rs.getInt("Theatreid"));
				screens.setTheatrename(rs.getString("Theatrename"));
				
				//Add the Users object into the Collection
				screensList.add(screens);
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

		return screensList;
	}	
	
	
	public static Screens getEmployeeById(int idParm)
	{
		System.out.println("--- getEmployeeById - idParam :: " + idParm);
		
		String sql = "SELECT * FROM SCREENS WHERE SCREENID =?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Screens screens = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1,idParm);

			rs = stmt.executeQuery();
		
			int screenid,no_of_goldseats,no_of_silverseats,theatreid;
			String screencode, theatrename;
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				screens = new Screens();

				 screenid = rs.getInt(1);
				 screencode = rs.getString(2);
				 no_of_goldseats = rs.getInt(3);
				 no_of_silverseats = rs.getInt(4);
				 theatreid=rs.getInt(5);
				 theatrename = rs.getString(6);


				    screens.setScreenid(screenid);
					screens.setScreencode(screencode);
					screens.setNo_of_goldseats(no_of_goldseats);
					screens.setNo_of_silverseats(no_of_silverseats);
					screens.setTheatreid(theatreid);
					screens.setTheatrename(theatrename);
						 
			
				
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


		return screens;
	}
	
	public static int createTheatres(Screens screens)
	{
		System.out.println("--- create screens- screens :: " +screens);
		
		String sql ="INSERT INTO SCREENS(SCREENCODE,NO_OF_GOLDSEATS,NO_OF_SILVERSEATS,THEATREID,THEATRENAME) "+ 
				" VALUES (?, ?, ?, ?,?)";

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
			
			pStmt.setString(1,  screens.getScreencode());
			pStmt.setInt(2, screens.getNo_of_goldseats());
			pStmt.setInt(3,  screens.getNo_of_silverseats());
			pStmt.setInt(4,  screens.getTheatreid());
			pStmt.setString(5,  screens.getTheatrename());
			
			
			
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
	
	public static int createPersonFlavor1ThrowsException(Screens screens) throws Exception
	{
		System.out.println("--- createPersonFlavor1ThrowsException - screens :: " +screens);
		
		String sql = "INSERT INTO SCREENS(SCREENCODE,NO_OF_GOLDSEATS,NO_OF_SILVERSEATS,THEATREID,THEATRENAME) "+ 
				" VALUES (?, ?, ?, ?,?)";

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
		
		pStmt.setString(1, screens.getScreencode());
		pStmt.setInt(2,screens.getNo_of_goldseats());
		pStmt.setInt(3,screens.getNo_of_silverseats());
		pStmt.setInt(4,screens.getTheatreid());
		pStmt.setString(5,screens.getTheatrename());
		
		
		
		
		
		
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
	public static void updateScreens(Screens screens) throws Exception
	{
		System.out.println("--- updateTheatres - screens :: " + screens);
	
		String sql = "UPDATE SCREENS SET "
				+ "SCREENCODE=?,NO_OF_GOLDSEATS=?,NO_OF_SILVERSEATS=?,THEATREID=?,THEATRENAME=?"
				+ "WHERE SCREENID=?";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		int recordsUpdated = 0;
			
		if(null==conn || conn.isClosed())
		{
			connectToDB();
		}
		
		pStmt = conn.prepareStatement(sql);
		
		pStmt.setString(1,  screens.getScreencode());
		pStmt.setInt(2, screens.getNo_of_goldseats());
		pStmt.setInt(3,  screens.getNo_of_silverseats());
		pStmt.setInt(4,  screens.getTheatreid());
		pStmt.setString(5,  screens.getTheatrename());
		pStmt.setInt(6,  screens.getScreenid());

	
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
		
		String sql = "DELETE FROM SCREENS WHERE SCREENID=?";

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


	
