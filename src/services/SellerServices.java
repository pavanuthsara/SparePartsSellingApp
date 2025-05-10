package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
