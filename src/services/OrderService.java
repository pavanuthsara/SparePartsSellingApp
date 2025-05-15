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
			statement.setString(6, order.getDate());
			statement.setString(7, order.getBuyerEmail());
			statement.setBoolean(8, order.isFastDelivery());
			
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
            DBConnect dbConnect = DBConnect.getInstance();
            Connection con = dbConnect.getConnection();
            
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, buyerEmail);
            
            ResultSet resultSet = statement.executeQuery();

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
                
                orderList.add(new Order(orderId, shippingAddress, specialNote, mobileNumber, paymentMethod, totalCost, date, buyerEmail, fastDelivery));
            }
            
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
	}
	
	public static Order getOrderById(int orderId, String buyerEmail) {
		String sql = "select * from buyerOrder where orderId=? and buyerEmail=?;";
		Order order = null;
		
		try {
			DBConnect dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setInt(1, orderId);
			statement.setString(2, buyerEmail);
			
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				int id = resultSet.getInt("orderId");
				String shippingAddress = resultSet.getString("shippingAddress");
				String specialNote = resultSet.getString("specialNote");
				String mobileNumber = resultSet.getString("mobileNumber");
				String paymentMethod = resultSet.getString("paymentMethod");
				double totalCost = resultSet.getDouble("totalCost");
				String date = resultSet.getString("date");
				buyerEmail = resultSet.getString("buyerEmail");
				boolean fastDelivery = resultSet.getBoolean("fastDelivery");
				
				order = new Order(id, shippingAddress, specialNote, mobileNumber, paymentMethod, totalCost, date, buyerEmail, fastDelivery);
				System.out.println("Order fetched successfully");
			} else {
				System.out.println("Error fetching order!");
			}
			
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return order;
	}
	
	public static boolean updateOrder(Order order) {
		String sql = "update buyerOrder set shippingAddress=?, specialNote=?, mobileNumber=?, paymentMethod=?, fastDelivery=? where orderId=? and buyerEmail=?;";
		boolean success = false;
		
		try {
			DBConnect dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			
			statement.setString(1, order.getShippingAddress());
			statement.setString(2, order.getSpecialNote());
			statement.setString(3, order.getMobileNumber());
			statement.setString(4, order.getPaymentMethod());
			statement.setBoolean(5, order.isFastDelivery());
			statement.setInt(6, order.getOrderId());
			statement.setString(7, order.getBuyerEmail());
			
			System.out.println(statement);
			
			int result = statement.executeUpdate();
			
			if (result > 0) {
				System.out.println("Update success, Order updated");
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
	
	public static boolean deleteOrder(int orderId, String buyerEmail) {
		String sqlOrder = "delete from buyerOrder where orderId=? and buyerEmail=?;";
		String sqlProducts = "delete from orderProducts where orderId=?;";
		boolean success = false;
		
		try {
			DBConnect dbConnect = DBConnect.getInstance();
			Connection con = dbConnect.getConnection();
			con.setAutoCommit(false); // Start transaction
			
			// Delete from orderProducts first (due to foreign key constraints)
			PreparedStatement statementProducts = con.prepareStatement(sqlProducts);
			statementProducts.setInt(1, orderId);
			statementProducts.executeUpdate();
			statementProducts.close();
			
			// Delete from buyerOrder
			PreparedStatement statementOrder = con.prepareStatement(sqlOrder);
			statementOrder.setInt(1, orderId);
			statementOrder.setString(2, buyerEmail);
			
			System.out.println(statementOrder);
			
			int result = statementOrder.executeUpdate();
			
			if (result > 0) {
				System.out.println("Delete success, Order deleted");
				con.commit();
				success = true;
			} else {
				System.out.println("Delete unsuccessful!");
				con.rollback();
			}
			
			statementOrder.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
}