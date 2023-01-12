package buildingmerchant.controller;

import java.util.ArrayList;

import buildingmerchant.model.Copy;
import buildingmerchant.model.Product;
import buildingmerchant.model.ProductContainer;
import buildingmerchant.model.SimpleProduct;

public class ProductController {
	private ProductContainer container;
	
	public ProductController() {
		container = ProductContainer.getInstance();
	}
	
	public Product findProduct(String barcode) {
		return container.findProduct(barcode);
	}

	public void generateTestProducts() {
		Product nail = new SimpleProduct("Søm");
		nail.setBarcode("0001");
		nail.setPrice(0.5);
		container.addProduct(nail);
		
		Product pressureWasher = new Copy("Højtryksrenser");
		pressureWasher.setBarcode("0002");
		pressureWasher.setPrice(1299.95);
		container.addProduct(pressureWasher);
		
		Product hammer = new SimpleProduct("Hammer");
		hammer.setBarcode("0003");
		hammer.setPrice(89.95);
		container.addProduct(hammer);
		
		Product tableSaw = new Copy("Rundsav");
		tableSaw.setBarcode("0004");
		tableSaw.setPrice(3499.95);
		container.addProduct(tableSaw);
	}
	public ArrayList<Product> getAllProducts(){
		return container.getAllProducts();
	} 

}
