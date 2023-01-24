package buildingmerchant.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import buildingmerchant.model.Customer;
import buildingmerchant.model.Order;
import buildingmerchant.model.OrderLine;
import buildingmerchant.model.PrivateCustomer;
import buildingmerchant.model.Product;
import buildingmerchant.model.SimpleProduct;


class TestOrder {
	Product product1;
	Product product2;
	Customer testCustomer;

	
	void setupProducts() {
		product1 = new SimpleProduct("Produkt 1");
		product2 = new SimpleProduct("Produkt 2");
		product1.setPrice(125);
		product2.setPrice(300);
		testCustomer = new PrivateCustomer();
	}
	
	
	void test_getTotalPriceTwoProducts() {
		Order order = new Order(testCustomer);
		OrderLine line1 = new OrderLine(product1, 5);
		OrderLine line2 = new OrderLine(product2, 10);
		order.addOrderLine(line1);
		order.addOrderLine(line2);
		double expectedPrice = 3625;
		assertEquals(expectedPrice, order.getTotalPrice());
	}
	

	
	void test_getTotalPrice() {
		Order order = new Order(testCustomer);
		OrderLine line1 = new OrderLine(product1, 5);
		order.addOrderLine(line1);
		double expectedPrice = 625;
		assertEquals(expectedPrice, order.getTotalPrice());
	}

}
