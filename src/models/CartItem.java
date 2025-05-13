package models;

public class CartItem {
	private int productId;
	private int quantity;
	private double unitPrice;
	private double price;
	
	public CartItem(int productId, int quantity, double unitPrice) {
		this.productId = productId;
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

}
