package models;

public class Buyer extends User{
	private String shippingAddress;
	
	public Buyer(int userId, String name, String email, String password, String shippingAddress) {
		super(userId, name, email, password);
		this.shippingAddress = shippingAddress;
	}
	
}
