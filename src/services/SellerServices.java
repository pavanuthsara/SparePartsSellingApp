package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DBConnect;
import models.Seller;

public class SellerServices {
	
	//seller register
	public static void register(Seller seller) {
		
		String sqlQuery = "insert into seller(email, name, password, storeName, mobileNumber) "
				+ "values (?, ?, ?, ?, ?);";
		
		try {
			DBConnect dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sqlQuery);
			
			statement.setString(1, seller.getEmail());
			statement.setString(2, seller.getName());
			statement.setString(3, seller.getPassword());
			statement.setString(4, seller.getStoreName());
			statement.setString(5, seller.getMobileNumber());
			
			System.out.println(statement);
			
			int result = statement.executeUpdate();
			
			if(result>0) {
				System.out.println("Insert success, Seller registration success");
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

	//seller login
	public static Seller login(String email, String password) {
		String sqlQuery = "select * from seller where email=? and password=?;";
		Seller seller = null;
		
		try {
			DBConnect dbConnect;
			dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sqlQuery);
		
			statement.setString(1, email);
			statement.setString(2, password);

			ResultSet results = statement.executeQuery();

			if(results.next()){
				String sellerEmail = results.getString(2);
				String sellerName = results.getString(3);
				String sellerstoreName = results.getString(5);
				String sellerMobileNumber = results.getString(6);
				
				seller = new Seller(sellerName, sellerEmail, sellerMobileNumber, sellerstoreName);
				System.out.println("Seller login success");
			} else {
				System.out.println("error with login!, Invalid sid or password!");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return seller;
	}

	//get profile details
	public static Seller getProfileDetails(String email) {
		Seller seller = null;
		String sqlQuery = "select * from seller where email=?;";
		
		try {
			DBConnect dbConnect;
			dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sqlQuery);
		
			statement.setString(1, email);

			ResultSet results = statement.executeQuery();

			if(results.next()){
				String sellerEmail = results.getString(2);
				String sellerName = results.getString(3);
				String storeName = results.getString(5);
				String mobileNumber = results.getString(6);
				
				seller = new Seller(sellerName, sellerEmail, mobileNumber, storeName);
				System.out.println("Seller fetched successfully");
			} else {
				System.out.println("error with fetching seller!, Invalid sid or password!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return seller;
	}
	
	//update profile
	public static boolean updateProfile(Seller seller) {
		String sqlQuery = "update seller set name=?, mobileNumber=?, storeName=? where email=?;";
		boolean success = false;
		
		try {
			DBConnect dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sqlQuery);
			
			statement.setString(1, seller.getName());
			statement.setString(2, seller.getMobileNumber());
			statement.setString(3, seller.getStoreName());
			statement.setString(4, seller.getEmail());
			
			System.out.println(statement);
			
			int result = statement.executeUpdate();
			
			if(result > 0) {
				System.out.println("Update success, Seller profile updated");
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
		String sqlQuery = "delete from seller where email=?;";
		boolean success = false;
		
		try {
			DBConnect dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sqlQuery);
			
			statement.setString(1, email);
			
			System.out.println(statement);
			
			int result = statement.executeUpdate();
			
			if(result > 0) {
				System.out.println("Delete success, Seller profile deleted");
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