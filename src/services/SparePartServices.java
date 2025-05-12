package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnect;
import models.Seller;
import models.SparePart;

public class SparePartServices {

    // add spare part
    public static void addSparePart(SparePart sparePart) {
        System.out.println("Add spare part service called");
        
        String sql = "INSERT INTO spareParts (title, quantity, unitPrice, location, description, status, image, sellerEmail) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            DBConnect dbConnect;
            dbConnect = DBConnect.getInstance();
            Connection con = dbConnect.getConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
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
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // update spare part
    public static void updateSparePart(SparePart sparePart) {
        System.out.println("Update spare part service called");
        
        String sql = "UPDATE spareParts SET title=?, quantity=?, unitPrice=?, location=?, description=?, status=? WHERE id=? AND sellerEmail=?";
        
        try {
            DBConnect dbConnect = DBConnect.getInstance();
            Connection con = dbConnect.getConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, sparePart.getTitle());
            stmt.setInt(2, sparePart.getQuantity());
            stmt.setDouble(3, sparePart.getUnitPrice());
            stmt.setString(4, sparePart.getLocation());
            stmt.setString(5, sparePart.getDescription());
            stmt.setString(6, sparePart.getStatus());
            stmt.setInt(7, sparePart.getId());
            stmt.setString(8, sparePart.getSellerEmail());
            
            int row = stmt.executeUpdate();
            
            if(row>0) {
                System.out.println("Product updated successfully");
            } else {
                System.out.println("Failed to update product");
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // delete spare part
    public static void deleteSparePart(int productId) {
        System.out.println("Delete spare part service called");
        
        String sql = "DELETE FROM spareParts WHERE id=?";
        
        try {
            DBConnect dbConnect = DBConnect.getInstance();
            Connection con = dbConnect.getConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, productId);
            
            int row = stmt.executeUpdate();
            
            if(row>0) {
                System.out.println("Product deleted successfully");
            } else {
                System.out.println("Failed to delete product");
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<SparePart> getAllProducts() {
        System.out.println("Get all products service called");
        
        String sql = "select * from spareParts;";
        
        ArrayList<SparePart> productList = new ArrayList<SparePart>();
        
        try {
            DBConnect dbConnect;
            dbConnect = DBConnect.getInstance();
            Connection con = dbConnect.getConnection();
            
            Statement stmt = con.createStatement();
            
            // Execute query
            ResultSet resultSet = stmt.executeQuery(sql);

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); 
                String title = resultSet.getString("title"); 
                int quantity = resultSet.getInt("quantity");
                double unitPrice = resultSet.getDouble("unitPrice");
                String location = resultSet.getString("location"); 
                String description = resultSet.getString("description"); 
                String status = resultSet.getString("status"); 
                String image = resultSet.getString("image"); 
                String sellerEmail = resultSet.getString("sellerEmail"); 
                
                productList.add(new SparePart(id, title,quantity, unitPrice, location,  description, status, image, sellerEmail));
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
    
    // get products related to seller
    public static ArrayList<SparePart> getSellerProduct(String email){
        System.out.println("Get products related to seller service called");
        
        String sql = "select * from spareParts where sellerEmail=?;";
        
        ArrayList<SparePart> productList = new ArrayList<SparePart>();
        
        try {
            DBConnect dbConnect;
            dbConnect = DBConnect.getInstance();
            Connection con = dbConnect.getConnection();
            
            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setString(1, email);
            
            // Execute query
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); 
                String title = resultSet.getString("title"); 
                int quantity = resultSet.getInt("quantity");
                double unitPrice = resultSet.getDouble("unitPrice");
                String location = resultSet.getString("location"); 
                String description = resultSet.getString("description"); 
                String status = resultSet.getString("status"); 
                String image = resultSet.getString("image"); 
                String sellerEmail = resultSet.getString("sellerEmail"); 
                
                productList.add(new SparePart(id, title,quantity, unitPrice, location,  description, status, image, sellerEmail));
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productList;
    }
}