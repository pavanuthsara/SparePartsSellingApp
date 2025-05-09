package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
}
