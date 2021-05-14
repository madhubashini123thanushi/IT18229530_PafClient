package com;
import java.sql.*;
public class User
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con =
 DriverManager.getConnection(
 "jdbc:mysql://127.0.0.1:3306/test3", "root", "");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }
public String readUsers()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for reading.";
 }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>User Name</th><th>Email</th><th>Age</th><th>Phone Number</th><th>Password</th>" + "<th>Update</th><th>Remove</th></tr>";
 String query = "select * from users";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String userID = Integer.toString(rs.getInt("userID"));
 String userName = rs.getString("userName"); 
 String Email = rs.getString("Email");
 String Age = rs.getString("Age");
 String PhoneNumber = rs.getString("PhoneNumber");
 String Password = rs.getString("Password");
 // Add into the html table
 output += "<tr><td><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='" + userID
 + "'>" + userName + "</td>";
 output += "<td>" + Email + "</td>";
 output += "<td>" + Age + "</td>";
 output += "<td>" + PhoneNumber + "</td>";
 output += "<td>" + Password + "</td>";
 // buttons
output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='"
 + userID + "'>" + "</td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the users.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String insertUser(String name, String email,
 String age, String phoneNumber,String password)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for inserting.";
 }
 // create a prepared statement
 String query = " insert into users(`userID`,`userName`,`Email`,`Age`,`PhoneNumber`,`Password`)"
 
+ " values (?, ?, ?, ?, ?,?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, email);
		 preparedStmt.setString(4, age);
		 preparedStmt.setString(5, phoneNumber);
		 preparedStmt.setString(6, password);
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newUsers = readUsers();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newUsers + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String updateUser(String ID, String name,String email, String age,
		 String phoneNumber, String password)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for updating.";
		 }
		 // create a prepared statement
		 String query = "UPDATE users SET userName=?,Email=?,Age=?,PhoneNumber=?,Password=? WHERE userID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, name);
		 preparedStmt.setString(2, email);
		 preparedStmt.setString(3, age);
		 preparedStmt.setString(4, phoneNumber);
		 preparedStmt.setString(5, password);
		 preparedStmt.setInt(6, Integer.parseInt(ID)); 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newUsers = readUsers();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newUsers + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteUser(String userID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for deleting.";
		 }
		 // create a prepared statement
		 String query = "delete from users where userID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(userID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newUsers = readUsers();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newUsers + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		}
