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
		
		String sql = "INSERT INTO spareParts (title, quantity, unitPrice, location, description, status) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
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
			
			stmt.executeUpdate();
			
			// Get the generated product ID
			ResultSet rs = stmt.getGeneratedKeys();
			int sparePartId = rs.next() ? rs.getInt(1) : -1;
			
			// Insert image paths
			sql = "INSERT INTO sparePartImages (sparePartId, imagePath) VALUES (?, ?)";
			stmt = con.prepareStatement(sql);
			
			List<String> imagePaths = sparePart.getImagePaths();
			
			for (String imagePath : imagePaths) {
			    stmt.setInt(1, sparePartId);
			    stmt.setString(2, imagePath);
			    stmt.executeUpdate();
			}

			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
