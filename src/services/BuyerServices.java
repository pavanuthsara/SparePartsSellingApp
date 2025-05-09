package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConnect;
import models.Buyer;

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
		
		DBConnect dbConnect;
		try {
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
	

	
}
