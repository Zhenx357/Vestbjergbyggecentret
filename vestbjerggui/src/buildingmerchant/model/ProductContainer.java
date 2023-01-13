package buildingmerchant.model;

import java.util.ArrayList;

public class ProductContainer {
	private static ProductContainer instance;
	private ArrayList<Product> products;
	
	ProductContainer() {
		this.products = new ArrayList<>();
	}
	
	public static ProductContainer getInstance() {
		if (instance == null) {
			instance = new ProductContainer();
		}
		return instance;
	}

	public Product findProduct(String barcode) {
		for (Product product : products) {
			if (product.getBarcode().equals(barcode)) {
				return product;
			}
		}
		return null;
	}

	public void addProduct(Product product) {
		Product existingProduct = findProduct(product.getBarcode());
		if (existingProduct != null) {
			return;
		}
		products.add(product);
	}
	public ArrayList<Product> getAllProducts(){
		return products;
	} 
	
}
