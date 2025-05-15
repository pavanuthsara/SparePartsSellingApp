package models;

public class CartItem {
	private int productId;
	private String title;
	private int quantity;
	private double unitPrice;
	private double price;
	
	public CartItem(int productId, String title, int quantity, double unitPrice) {
		this.productId = productId;
		this.title = title;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		calculatePrice();
	}
	
	public void calculatePrice() {
		price = quantity * unitPrice;
	}
	
	public void incrementQuantity() {
		quantity++;
		calculatePrice();
	}
	
	public void decrementQuantity() {
		quantity--;
		calculatePrice();
	}
	
	public double getPrice() {
		return price;
	}

	public int getProductId() {
		return productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	

}
