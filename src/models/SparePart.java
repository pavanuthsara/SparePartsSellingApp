package models;

import java.util.ArrayList;
import java.util.List;

public class SparePart {
	private String title;
	private int quantity;
	private double unitPrice;
	private String location;
	private String description;
	private String status;
	private List<String> imagePaths;
	
	public SparePart(String title, int quantity, double unitPrice, String location, String description, String status,
			List<String> imagePaths) {
		super();
		this.title = title;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.location = location;
		this.description = description;
		this.status = status;
		this.imagePaths = imagePaths;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(List<String> imagePaths) {
		this.imagePaths = imagePaths;
	}
	
}
