package models;

import java.util.ArrayList;

public class Cart {
	private ArrayList<CartItem> items;
	private double totalPrice;
	
	public Cart() {
		System.out.println("cart constructor");
		totalPrice = 0;
		items = new ArrayList<CartItem>();
	}
	
	public void addItemToCart(CartItem item) {
		items.add(item);
	}
	
	public void removeItemFromCart(CartItem item) {
		items.remove(item);
	}
	
	public double checkout() {
		for(CartItem i : items) {
			totalPrice += i.getPrice();
		}
		return totalPrice;
	}

	public void displayCart() {
		System.out.println("Displaying cart items");
		for(CartItem i : items) {
			System.out.println(i.getProductId());;
		}
	}

	public ArrayList<CartItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}
	
	
	

}
