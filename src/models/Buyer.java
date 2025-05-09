package models;

public class Buyer extends User{
	private String shippingAddress;

	public Buyer(int userId, String name, String email, String password, String mobileNumber, String shippingAddress) {
		super(userId, name, email, password, mobileNumber);
		this.shippingAddress = shippingAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
}
