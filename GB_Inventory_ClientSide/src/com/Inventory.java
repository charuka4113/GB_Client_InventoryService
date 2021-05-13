package com; 
import java.sql.*;

public class Inventory 
{ 	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafprojtest", "root", ""); 
			System.out.print("Successfully connected");
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 

	public String insertInventory(String name, String desc, 
			 String size, String price) 
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
			 String query = " insert into inventory (`productid`,`productname`,`description`,`size`,`price`)"
					+ " values (?, ?, ?, ?, ?)"; 
 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, desc); 
			 preparedStmt.setString(4, size);
			 preparedStmt.setInt(5, Integer.parseInt(price));
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newInventory = readInventory(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newInventory + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the software product.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }  
			
	public String readInventory()
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
	 output = "<table border='1'><tr><th>Software Product Name</th>" 
	 + "<th>Software Product Description</th><th>Software Product Size</th>"
	 + "<th>Software Product Price</th>" 
	 + "<th>Update</th><th>Remove</th></tr>"; 
	 String query = "select * from inventory"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String productid = Integer.toString(rs.getInt("productid")); 
	 String productname = rs.getString("productname"); 
	 String description = rs.getString("description"); 
	 String size = rs.getString("size");  
	 String price =Integer.toString(rs.getInt("price")); 
	 
	// Add into the html table
	 output += "<tr><td>" + productname + "</td>"; 
	 output += "<td>" + description + "</td>"; 
	 output += "<td>" + size + "</td>"; 
	 output += "<td>" + price + "</td>"; 
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-productid='" + productid + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-productid='" + productid + "'></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while reading the Inventory."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
		
	public String updateInventorym(String ID, String name, String desc, 
			 String size, String price) 
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
			 String query = "UPDATE inventory SET productname=?,description=?,size=?,price=? WHERE productid=?"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, desc); 
			 preparedStmt.setString(3, size); 
			 preparedStmt.setInt(4, Integer.parseInt(price)); 
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newInventory = readInventory(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newInventory + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the software products.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
			 
	public String deleteInventory(String productid) 
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
	 String query = "delete from inventory where productid=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(productid)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newInventory = readInventory(); 
	 output = "{\"status\":\"success\", \"data\": \"" + 
	 newInventory + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\": \"Error while deleting the software product.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
} 