package models;

import java.util.ArrayList;

public class Order {
	private int orderId;
	private ArrayList<CartItem> cartItems;
	private String shippingAddress;
	private String specialNote;
	private String mobileNumber;
	private String paymentMethod;
	private double totalCost;
	private String date;
	private String buyerEmail;
	private boolean fastDelivery;
	
	public Order(ArrayList<CartItem> cartItems, String shippingAddress, String specialNote, String mobileNumber,
			String paymentMethod, double totalCost, String date, String buyerEmail, boolean fastDelivery) {
		super();
		this.cartItems = cartItems;
		this.shippingAddress = shippingAddress;
		this.specialNote = specialNote;
		this.mobileNumber = mobileNumber;
		this.paymentMethod = paymentMethod;
		this.totalCost = totalCost;
		this.date = date;
		this.buyerEmail = buyerEmail;
		this.fastDelivery = fastDelivery;
	}
	
	public Order(int orderId, String shippingAddress, String specialNote, String mobileNumber, String paymentMethod,
			double totalCost, String date, String buyerEmail, boolean fastDelivery) {
		super();
		this.orderId = orderId;
		this.shippingAddress = shippingAddress;
		this.specialNote = specialNote;
		this.mobileNumber = mobileNumber;
		this.paymentMethod = paymentMethod;
		this.totalCost = totalCost;
		this.date = date;
		this.buyerEmail = buyerEmail;
		this.fastDelivery = fastDelivery;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public ArrayList<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(ArrayList<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getSpecialNote() {
		return specialNote;
	}

	public void setSpecialNote(String specialNote) {
		this.specialNote = specialNote;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public boolean isFastDelivery() {
		return fastDelivery;
	}

	public void setFastDelivery(boolean fastDelivery) {
		this.fastDelivery = fastDelivery;
	}
	
	
	


}
