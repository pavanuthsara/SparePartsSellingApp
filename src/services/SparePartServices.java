package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import config.DBConnect;
import models.SparePart;

public class SparePartServices {

	// add spare part
	public static void addSparePart(SparePart sparePart) {
		System.out.println("Add spare part service called");
		
		String sql = "INSERT INTO spareParts (title, quantity, unitPrice, location, description, status, image, sellerEmail) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		DBConnect dbConnect;
		try {
			dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, sparePart.getTitle());
			stmt.setInt(2, sparePart.getQuantity() );
			stmt.setDouble(3, sparePart.getUnitPrice());
			stmt.setString(4, sparePart.getLocation());
			stmt.setString(5, sparePart.getDescription());
			stmt.setString(6, sparePart.getStatus());
			stmt.setString(7, sparePart.getImagePath());
			stmt.setString(8, sparePart.getSellerEmail());
			
			int row = stmt.executeUpdate();
			
			if(row>0) {
				System.out.println("Product added successfully");
			} else {
				System.out.println("Failed to add a product");
			}
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
