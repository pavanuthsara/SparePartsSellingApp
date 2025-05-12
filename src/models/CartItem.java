package models;

public class CartItem {
    private SparePart product;
    private int quantity;

    public CartItem(SparePart product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public SparePart getProduct() {
        return product;
    }

    public void setProduct(SparePart product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}