package models;

public class User {
	protected String name;
	protected String email;
	protected String password;
	protected String mobileNumber;
	
	public User(String name, String email, String password, String mobileNumber) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public User(String name, String email, String mobileNumber) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
}
