package models;

public class Seller extends User{
	private String storeName;
	
	public Seller(int userId, String name, String email, String password, String mobileNumber, String storeName) {
		super(userId, name, email, password, mobileNumber);
		this.storeName = storeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	
	
	
}
