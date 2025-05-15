package models;

public class Seller extends User{
	private String storeName;
	
	public Seller(String name, String email, String password, String mobileNumber, String storeName) {
		super(name, email, password, mobileNumber);
		this.storeName = storeName;
	}
	
	public Seller(String name, String email, String mobileNumber, String storeName) {
		super(name, email, mobileNumber);
		this.storeName = storeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
