package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.DBConnect;
import models.CartItem;
import models.Order;

public class OrderService {
	
	public static boolean placeOrder(Order order) {
		String sqlQuery = "insert into buyerOrder(shippingAddress, specialNote, mobileNumber, paymentMethod, totalCost, date, buyerEmail, fastDelivery) values (?, ?, ?, ?, ?, ?, ?, ?);";
		String sqlQuerySupport = "select * from buyerOrder order by orderId desc limit 1;";
		String sqlQuery2 = "insert into orderProducts(orderId ,productId ) values (?,?);";
		
		try {
			DBConnect dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			
			//insert order
			PreparedStatement statement = con.prepareStatement(sqlQuery);
			
			statement.setString(1, order.getShippingAddress());
			statement.setString(2, order.getSpecialNote());
			statement.setString(3, order.getMobileNumber());
			statement.setString(4, order.getPaymentMethod());
			statement.setDouble(5, order.getTotalCost());
			statement.setString(6,  order.getDate());
			statement.setString(7,  order.getBuyerEmail());
			statement.setBoolean(8,  order.isFastDelivery());
			
			System.out.println(statement);
			
			int result = statement.executeUpdate();
			
			if(result>0) {
				System.out.println("Insert success, Order added successfully!");
			} else {
				System.out.println("Insert unsuccess!");
			}
			
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static ArrayList<Order> getOrdersOfBuyer(String buyerEmail) {
		System.out.println("Get orders of buyer");
        String sql = "select * from buyerOrder where buyerEmail=?;";
        
        ArrayList<Order> orderList = new ArrayList<Order>();
        
        try {
            DBConnect dbConnect;
            dbConnect = DBConnect.getInstance();
            Connection con = dbConnect.getConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, buyerEmail);
            
            System.out.println("Query statement : " + stmt);
            
            // Execute query
            ResultSet resultSet = stmt.executeQuery();

            // Process the result set
            while (resultSet.next()) {
            	int orderId = resultSet.getInt("orderId");
            	String shippingAddress = resultSet.getString("shippingAddress");
            	String specialNote = resultSet.getString("specialNote");
            	String mobileNumber = resultSet.getString("mobileNumber");
            	String paymentMethod = resultSet.getString("paymentMethod");
            	double totalCost = resultSet.getDouble("totalCost");
            	String date = resultSet.getString("date");
            	buyerEmail = resultSet.getString("buyerEmail");
            	boolean fastDelivery = resultSet.getBoolean("fastDelivery");
                
                orderList.add(new Order(orderId, shippingAddress, specialNote, mobileNumber, paymentMethod, totalCost, date, buyerEmail, fastDelivery ));
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
		
	}


}
