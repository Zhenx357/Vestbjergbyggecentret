package buildingmerchant.model;

public class OrderLine {
	private Product product;
	private int quantity;
	
	public OrderLine(Product product, int quantity) {
		this.setProduct(product);
		this.setQuantity(quantity);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getProductPrice() {
		return product.getPrice();
	}
	
	public double getTotalPrice() {
		return product.getPrice() * quantity;
	}
}
