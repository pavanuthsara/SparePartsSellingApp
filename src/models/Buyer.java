package models;

public class Buyer extends User{
	private String shippingAddress;

	public Buyer(String name, String email, String password, String mobileNumber, String shippingAddress) {
		super( name, email, password, mobileNumber);
		this.shippingAddress = shippingAddress;
	}
	
	public Buyer(String name, String email, String mobileNumber, String shippingAddress) {
		super(name, email, mobileNumber);
		this.shippingAddress = shippingAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
}
