package com.tutorials.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.tutorials.jdbc.bo.Movies;

public class MoviesDAO
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

		List<Movies> moviesList = listAll();
		System.out.println("movieList size :  " + moviesList.size());

		if(moviesList.size()>0) {
			for(Movies movies : moviesList) {
				System.out.println(movies);
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
		String SQL = "CREATE TABLE MOVIES(MOVIE_ID INT AUTO_INCREMENT PRIMARY KEY,MOVIE_CODE VARCHAR(15) ,MOVIE_NAME VARCHAR(20) NOT NULL,GENRE VARCHAR(20)NOT NULL,CAST VARCHAR(100) NOT NULL,DIRECTOR VARCHAR(20) NOT NULL,CREW VARCHAR(300) NOT NULL,LANGUAGE VARCHAR(20) NOT NULL,RELEASE_DATE VARCHAR(20) NOT NULL, MUSIC VARCHAR(20) NOT NULL,RATEING VARCHAR(10)NOT NULL)";
		System.out.println(SQL);
	}
	
	public static List<Movies> listAll()
	{
		System.out.println("--- listAll invoked --- ");
		
		String sql = "SELECT * FROM MOVIES";
	
		/*if(null==conn) {
			throw new RuntimeException("DB Connection has not yet been established!");	
		}*/

		Statement stmt = null; 
		ResultSet rs = null;
		List<Movies> moviesList = new ArrayList<>();
		Movies movies = null;
		
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
                 movies = new Movies();
				
				movies.setMovie_id(rs.getInt("Movie_id"));//Users.setId(rs.getInt(1));
				movies.setMovie_code(rs.getString("Movie_code"));
				movies.setMovie_name(rs.getString("Movie_name"));
				movies.setGenre(rs.getString("Genre"));
				movies.setCast(rs.getString("Cast"));
				movies.setDirector(rs.getString("Director"));
				movies.setCrew(rs.getString("Crew"));
				movies.setLanguage(rs.getString("Language"));
				movies.setRelease_date(rs.getString("Release_date"));
				movies.setMusic(rs.getString("Music"));
				movies.setRateing(rs.getString("Rateing"));
				
				//Add the Users object into the Collection
				moviesList.add(movies);
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

		return moviesList;
	}	
	
	
	public static Movies getEmployeeById(int idParm)
	{
		System.out.println("--- getEmployeeById - idParam :: " + idParm);
		
		String sql = "SELECT * FROM MOVIES WHERE MOVIE_ID=?";

		System.out.println("SQL Query :: " + sql);

		//Statement stmt = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int count = 0;

		Movies movies = null;
		
		try {
			
			if(null==conn || conn.isClosed())
			{
				connectToDB();
			}
			
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1,idParm);

			rs = stmt.executeQuery();
		
		int movie_id;
			String movie_code,movie_name, genre,cast ,director,crew,language,release_date,music,rateing;
		
			while(rs.next()) { //read one full row's data - one column at a time

				// makes sense to create an object, so that we don't waste the memory allotted to an Object.
				movies = new Movies();

				movie_id = rs.getInt(1);
				movie_code = rs.getString(2);
				movie_name = rs.getString(3);
				genre = rs.getString(4);
				cast=rs.getString(5);
				director= rs.getString(6);
				crew=rs.getString(7);
				language=rs.getString(8);
				release_date=rs.getString(9);
				music=rs.getString(10);
				rateing=rs.getString(11);


				movies.setMovie_id(movie_id);
				movies.setMovie_code(movie_code);
				movies.setMovie_name(movie_name);
				movies.setGenre(genre);
				movies.setCast(cast);
				movies.setDirector(director);
				movies.setCrew(crew);
				movies.setLanguage(language);
				movies.setRelease_date(release_date);
				movies.setMusic(music);
				movies.setRateing(rateing);

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


		return movies;
	}
	
	public static int createMovies(Movies movies)
	{
		System.out.println("--- createMovie - movies :: " +movies);
		
		String sql = "INSERT INTO MOVIES (MOVIE_CODE,MOVIE_NAME,GENRE,CAST ,DIRECTOR ,CREW ,LANGUAGE ,RELEASE_DATE , MUSIC ,RATEING )" + 
				" VALUES (?, ?, ?, ?,?,?,?,?,?,?)";

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
			
			pStmt.setString(1, movies.getMovie_code());
			
			pStmt.setString(2, movies.getMovie_name());
			pStmt.setString(3, movies.getGenre());
			pStmt.setString(4, movies.getCast());
			pStmt.setString(5, movies.getDirector());
			pStmt.setString(6, movies.getCrew());
			pStmt.setString(7, movies.getLanguage());
			pStmt.setString(8, movies.getRelease_date());
			pStmt.setString(9, movies.getMusic());
			pStmt.setString(10, movies.getRateing());
			
			
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
	
	public static int createPersonFlavor1ThrowsException(Movies movies) throws Exception
	{
		System.out.println("--- createPersonFlavor1ThrowsException - movie :: " + movies);
		
		String sql = "INSERT INTO MOVIES (MOVIE_CODE,MOVIE_NAME,GENRE,CAST ,DIRECTOR ,CREW ,LANGUAGE ,RELEASE_DATE , MUSIC ,RATEING )" + 
				" VALUES (?, ?, ?, ?,?,?,?,?,?,?)";
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
		
		
		pStmt.setString(1, movies.getMovie_code());
		pStmt.setString(2, movies.getMovie_name());
		pStmt.setString(3, movies.getGenre());
		pStmt.setString(4, movies.getCast());
		pStmt.setString(5, movies.getDirector());
		pStmt.setString(6, movies.getCrew());
		pStmt.setString(7, movies.getLanguage());
		pStmt.setString(8, movies.getRelease_date());
		pStmt.setString(9, movies.getMusic());
		pStmt.setString(10, movies.getRateing());
		
		
		
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
	public static void updateMovie(Movies movies) throws Exception
	{
		System.out.println("--- updateMovies - movies :: " + movies);
		
		String sql = "UPDATE MOVIES SET "
				+ " MOVIE_CODE=?,MOVIE_Name=?, GENRE=?, CAST=?, DIRECTOR=?, CREW=? , LANGUAGE=?, RELEASE_DATE=?,MUSIC=?,RATEING=?"
				+ " WHERE MOVIE_ID= ?";

		System.out.println("SQL Query :: " + sql);
		
		PreparedStatement pStmt = null;
		int recordsUpdated = 0;
			
		if(null==conn || conn.isClosed())
		{
			connectToDB();
		}
		
		pStmt = conn.prepareStatement(sql);
		
		

		pStmt.setString(1, movies.getMovie_code());
		pStmt.setString(2, movies.getMovie_name());
		pStmt.setString(3, movies.getGenre());
		pStmt.setString(4, movies.getCast());
		pStmt.setString(5, movies.getDirector());
		pStmt.setString(6, movies.getCrew());
		pStmt.setString(7, movies.getLanguage());
		pStmt.setString(8, movies.getRelease_date());
		pStmt.setString(9, movies.getMusic());
		pStmt.setString(10, movies.getRateing());
		pStmt.setInt(11, movies.getMovie_id());
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
		
		String sql = "DELETE FROM MOVIES WHERE MOVIE_ID=?";

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


	
