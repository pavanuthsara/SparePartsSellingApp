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
			con.close();
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
		
		DBConnect dbConnect;
		try {
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
			
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return seller;
	}
	

}
