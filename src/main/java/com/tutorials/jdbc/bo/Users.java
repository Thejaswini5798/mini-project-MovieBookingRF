package com.tutorials.jdbc.bo;

public class Users
{
	private int id;

	private String name;

	private int age;

	private String phone;

	private String city;

	private String pincode;
	
	private String email;
	
	private String password;
	

	/* ============================= */
	/*     Getters and Setters       */
	/* ============================= */

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id=id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return this.pincode;
	}
	
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password ;
	}


	public Users() {
		System.out.println("Users() - instantiated..");
	}

	
	/* ===================================== */
	/* 		Constructors		 */
	/* ===================================== */


	
	public Users(int id, String name, int age, String phone, String city, String pincode, String email, String password) {
		System.out.println("Users(id, name, age, phone, city, pincode,email,password) - instantiated..");
		this.id = id;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.city = city;
		this.pincode = pincode;
		this.email = email;
		this.password = password;
	}
	

	/* ==================================== */
	/*		toString()		*/
	/* ==================================== */

	@Override
	public String toString() {
		return "[Users] hashCode=" + this.hashCode()
			+ ", Id = " + id  
			+ ", Name = " + name 
			+ ", Age = " + age 
			+ ", Phone ="+ phone
			+ ", city = " + city
			+ ", pincode ="+ pincode
			+ ", email="+ email
			+ ", password ="+ password;
		    
	}
}
	
