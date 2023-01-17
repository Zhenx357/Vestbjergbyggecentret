package buildingmerchant.model;

public abstract class Product {
	private String productName;
	private String barcode;
	private double price;

	public Product(String productName) {
		this.productName = productName;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getInfo() {
		return productName + " | " + barcode + " | " + price + " DKK";
	}
	/**
	 * Prints information about a single product
	 * @param product
	 */
	public void printProduct() {
		System.out.println("Product: " + productName + "\n");
	}
}
