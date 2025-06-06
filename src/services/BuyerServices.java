package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DBConnect;
import models.Buyer;
import models.Order;

public class BuyerServices {
	
	//buyer register
	public static void register(Buyer buyer) {
		
		String sqlQuery = "insert into buyer(email, name, password, address, mobileNumber) "
				+ "values (?, ?, ?, ?, ?);";
		
		try {
			DBConnect dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sqlQuery);
			
			statement.setString(1, buyer.getEmail());
			statement.setString(2, buyer.getName());
			statement.setString(3, buyer.getPassword());
			statement.setString(4, buyer.getShippingAddress());
			statement.setString(5, buyer.getMobileNumber());
			
			System.out.println(statement);
			
			int result = statement.executeUpdate();
			
			if(result>0) {
				System.out.println("Insert success, Buyer registration success");
			} else {
				System.out.println("Insert unsuccess!");
			}
			
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//buyer login
	public static Buyer login(String email, String password){

		String sqlQuery = "select * from buyer where email=? and password=?;";
		Buyer buyer = null;
		
		try {
			DBConnect dbConnect;
			dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sqlQuery);
		
			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet results = statement.executeQuery();

			if(results.next()){
				String buyerEmail = results.getString(2);
				String buyerName = results.getString(3);
				String address = results.getString(5);
				String mobileNumber = results.getString(6);
				
				buyer = new Buyer(buyerName, buyerEmail, mobileNumber, address);
				System.out.println("Buyer login success");
			} else {
				System.out.println("error with login!, Invalid sid or password!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return buyer;
	}
	
	//get profile details
	public static Buyer getProfileDetails(String email) {
		Buyer buyer = null;
		String sqlQuery = "select * from buyer where email=?;";
		
		try {
			DBConnect dbConnect;
			dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sqlQuery);
		
			statement.setString(1, email);

			ResultSet results = statement.executeQuery();

			if(results.next()){
				String buyerEmail = results.getString(2);
				String buyerName = results.getString(3);
				String address = results.getString(5);
				String mobileNumber = results.getString(6);
				
				buyer = new Buyer(buyerName, buyerEmail, mobileNumber, address);
				System.out.println("Buyer fetched successfully");
			} else {
				System.out.println("error with fetching buyer!, Invalid sid or password!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return buyer;
	}
	
	//update profile
	public static boolean updateProfile(Buyer buyer) {
		String sqlQuery = "update buyer set name=?, mobileNumber=?, address=? where email=?;";
		boolean success = false;
		
		try {
			DBConnect dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sqlQuery);
			
			statement.setString(1, buyer.getName());
			statement.setString(2, buyer.getMobileNumber());
			statement.setString(3, buyer.getShippingAddress());
			statement.setString(4, buyer.getEmail());
			
			System.out.println(statement);
			
			int result = statement.executeUpdate();
			
			if(result > 0) {
				System.out.println("Update success, Buyer profile updated");
				success = true;
			} else {
				System.out.println("Update unsuccessful!");
			}
			
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	//delete profile
	public static boolean deleteProfile(String email) {
		String sqlQuery = "delete from buyer where email=?;";
		boolean success = false;
		
		try {
			DBConnect dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sqlQuery);
			
			statement.setString(1, email);
			
			System.out.println(statement);
			
			int result = statement.executeUpdate();
			
			if(result > 0) {
				System.out.println("Delete success, Buyer profile deleted");
				success = true;
			} else {
				System.out.println("Delete unsuccessful!");
			}
			
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
}