package models;

public class Order {
	private int orderId;
	private Cart cart;
	private String shippingAddress;
	private String specialNote;
	private String mobileNumber;
	private String paymentMethod;
	private double totalCost;
	private int date;
	private int buyerId;
	
	public Order() {
		totalCost = cart.checkout();
	}

}
