package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.DBConnect;
import models.SparePart;

public class SparePartServices {

    // Method: addSparePart
    public static List<Integer> addSparePart(SparePart sparePart) {
        System.out.println("Add spare part service called");
        List<Integer> imageIds = new ArrayList<>();

        String sql = "INSERT INTO spareParts (title, quantity, unitPrice, location, description, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        DBConnect dbConnect;
        try {
            dbConnect = DBConnect.getInstance();
            Connection con = dbConnect.getConnection();

            // Insert spare part details
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, sparePart.getTitle());
            stmt.setInt(2, sparePart.getQuantity());
            stmt.setDouble(3, sparePart.getUnitPrice());
            stmt.setString(4, sparePart.getLocation());
            stmt.setString(5, sparePart.getDescription());
            stmt.setString(6, sparePart.getStatus());

            stmt.executeUpdate();

            // Get the generated product ID
            ResultSet rs = stmt.getGeneratedKeys();
            int sparePartId = rs.next() ? rs.getInt(1) : -1;

            // Insert images as BLOBs and collect image IDs
            sql = "INSERT INTO sparePartImages (sparePartId, image) VALUES (?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            List<byte[]> images = sparePart.getImages();
            for (byte[] image : images) {
                stmt.setInt(1, sparePartId);
                stmt.setBytes(2, image);
                stmt.executeUpdate();

                // Get the generated image ID
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    imageIds.add(rs.getInt(1));
                }
            }

            con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageIds;
    }

    // Method: getAllSpareParts
    public static List<SparePart> getAllSpareParts() {
        List<SparePart> spareParts = new ArrayList<>();
        String sql = "SELECT sp.id, sp.title, sp.quantity, sp.unitPrice, sp.location, sp.description, sp.status, spi.id AS imageId " +
                     "FROM spareParts sp LEFT JOIN sparePartImages spi ON sp.id = spi.sparePartId";

        try {
            DBConnect dbConnect = DBConnect.getInstance();
            Connection con = dbConnect.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            Map<Integer, SparePart> sparePartMap = new HashMap<>();
            while (rs.next()) {
                int sparePartId = rs.getInt("id");
                SparePart sparePart = sparePartMap.get(sparePartId);

                if (sparePart == null) {
                    String title = rs.getString("title");
                    int quantity = rs.getInt("quantity");
                    double unitPrice = rs.getDouble("unitPrice");
                    String location = rs.getString("location");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    List<byte[]> images = new ArrayList<>();
                    sparePart = new SparePart(title, quantity, unitPrice, location, description, status, images);
                    sparePartMap.put(sparePartId, sparePart);
                    spareParts.add(sparePart);
                }

                int imageId = rs.getInt("imageId");
                if (!rs.wasNull()) {
                    // Add image URL to imageUrls field
                    sparePart.getImageUrls().add("ImageServlet?id=" + imageId);
                }
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return spareParts;
    }
}