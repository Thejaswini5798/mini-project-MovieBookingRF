package com.tutorials.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


import com.tutorials.jdbc.bo.Users;

public class UsersDAO
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
	
	public UsersDAO()
	{
		if(null==_instance) {
			_instance = new UsersDAO();
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

		List<Users> usersList = listAll();
		System.out.println("usersList size :  " + usersList.size());

		if(usersList.size()>0) {
			for(Users users : usersList) {
				System.out.println(users);
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
		String SQL = "CREATE TABLE USERS(ID INT AUTO_INCREMENT PRIMARY KEY,NAME VARCHAR(20) NOT NULL,AGE INT NOT NULL,PHONE VARCHAR(10) NOT NULL,CITY VARCHAR(20) NOT NULL,PINCODE VARCHAR(15)NOT NULL, EMAIL VARCHAR(20) NOT NULL,PASSWORD VARCHAR(15) NOT NULL))";
		System.out.println(SQL);
	}
	
	public static List<Users> listAll()
	{
		System.out.println("--- listAll invoked --- ");
		
		String sql = "SELECT * FROM USERS";
	
		/*if(null==conn) {
			throw new RuntimeException("DB Connection has not yet been established!");	
		}*/

		Statement stmt = null; 
		ResultSet rs = null;
		List<Users> usersList = new ArrayList<>();
		Users users = null;
		
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
                 users = new Users();
				
				users.setId(rs.getInt("Id"));//Users.setId(rs.getInt(1));
				users.setName(rs.getString("Name"));
				users.setAge(rs.getInt("Age"));
				users.setPhone(rs.getString("Phone"));
				users.setCity(rs.getString("City"));
				users.setPincode(rs.getString("Pincode"));
				users.setEmail(rs.getString("Email"));
				users.setPassword(rs.getString("Password"));
				//Add the Users object into the Collection
				usersList.add(users);
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

		return usersList;
	}	
	
	
	public static Users getEmployeeById(int idParam)
	{
		System.out.println("--- getEmployeeById - idParam :: " + idParam);
		
		String sql = "SELECT * FROM USERS WHERE ID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Users users = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, idParam);

			rs = stmt.executeQuery();
		
			int id, age;
			String name, city,email,password,phone,pincode;
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				users = new Users();

				id = rs.getInt(1);
				name = rs.getString(2);
				age = rs.getInt(3);
				phone=rs.getString(4);
				city = rs.getString(5);
				pincode=rs.getString(6);
				email=rs.getString(7);
				password=rs.getString(8);
				


				users.setId(id);
				users.setName(name);
				users.setAge(age);
				users.setPhone(phone);
				users.setCity(city);
				users.setPincode(pincode);
				users.setEmail(email);
				users.setPassword(password);
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

		return users;
	}
	
	public static int createUsers(Users users)
	{
		System.out.println("--- createPerson - users :: " +users);
		
		String sql = "INSERT INTO USERS (Name, Age, Phone, City, Pincode,Email,Password)" + 
				" VALUES (?, ?, ?, ?,?,?,?)";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		int lastInsertedId=0, recordsInserted = 0;
		
		try {
			
			if(null==conn)
			{
				connectToDB();
			}
			
			System.out.println("AutoCommit ? : " + conn.getAutoCommit());
			/*conn.setAutoCommit(true);
			System.out.println("(2) AutoCommit ? : " + conn.getAutoCommit());*/
			
			pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, users.getName());
			pStmt.setInt(2, users.getAge());
			pStmt.setString(3, users.getPhone());
			pStmt.setString(4, users.getCity());
			pStmt.setString(5, users.getPincode());
			pStmt.setString(6, users.getEmail());
			pStmt.setString(7, users.getPassword());
			
			
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
	
	public static int createPersonFlavor1ThrowsException(Users users) throws Exception
	{
		System.out.println("--- createPersonFlavor1ThrowsException - users :: " + users);
		
		String sql = "INSERT INTO USERS (Name, Age, Phone, City, Pincode, Email, Password)" + 
				" VALUES (?, ?, ?, ?, ?,?,?)";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		int lastInsertedId=0, recordsInserted = 0;
			
		if(null==conn)
		{
			connectToDB();
		}
		
		System.out.println("AutoCommit ? : " + conn.getAutoCommit());
		/*conn.setAutoCommit(true);
		System.out.println("(2) AutoCommit ? : " + conn.getAutoCommit());*/
		
		pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, users.getName());
		pStmt.setInt(2, users.getAge());
		pStmt.setString(3, users.getPhone());
		pStmt.setString(4, users.getCity());
		pStmt.setString(5, users.getPincode());
		pStmt.setString(6, users.getEmail());
		pStmt.setString(7, users.getPassword());
		
		
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
	public static void updateUsers(Users users) throws Exception
	{
		System.out.println("--- updateUsers - users :: " + users);
		
		String sql = "UPDATE USERS SET "
				+ "Name=?, Age=?, Phone=?, City=?, Pincode=? , Email=?, Password=?"
				+ " WHERE ID= ?";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		int recordsUpdated = 0;
			
		if(null==conn || conn.isClosed())
		{
			connectToDB();
		}
		
		pStmt = conn.prepareStatement(sql);
		
		
		pStmt.setString(1, users.getName());
		pStmt.setInt(2, users.getAge());
		pStmt.setString(3, users.getPhone());
		pStmt.setString(4, users.getCity());
		pStmt.setString(5, users.getPincode());
		pStmt.setString(6, users.getEmail());
		pStmt.setString(7, users.getPassword());
		pStmt.setInt(8, users.getId());
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
		
		String sql = "DELETE FROM USERS WHERE ID=?";

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
	public static boolean validate( Users users) throws Exception{ 
		System.out.println("--- loginUsers - users :: " + users);
		boolean status=false;
	
		String sql = "select * from users where email = ? and password = ? ";
		
			System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		
	
try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, users.getEmail());
			pStmt.setString(2, users.getPassword());
			ResultSet rs=pStmt.executeQuery();  
			status=rs.next();  
	
	}
		catch(Exception e){
		System.out.println(e);
		}  
		return status;  
		}

	

	private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	public static Users getUsersByEmail( String emailParam) throws Exception{ 
	
		System.out.println("--- getEmployeeById - emailParam :: " + emailParam);
		
		String sql ="select * from users where email = ?  ";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Users users = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, emailParam);
			

			rs = stmt.executeQuery();
		
			int id, age;
			String name, city,email,password,phone,pincode;
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				users = new Users();

				id = rs.getInt(1);
				name = rs.getString(2);
				age = rs.getInt(3);
				phone=rs.getString(4);
				city = rs.getString(5);
				pincode=rs.getString(6);
				email=rs.getString(7);
				password=rs.getString(8);
				


				users.setId(id);
				users.setName(name);
				users.setAge(age);
				users.setPhone(phone);
				users.setCity(city);
				users.setPincode(pincode);
				users.setEmail(email);
				users.setPassword(password);

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

		return users;
	}
	
	
}
	
	
	
	



	
