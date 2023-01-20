package buildingmerchant.tui;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import buildingmerchant.controller.CustomerController;
import buildingmerchant.controller.OrderController;
import buildingmerchant.controller.ProductController;
import buildingmerchant.controller.ToolController;
import buildingmerchant.model.BusinessCustomer;
import buildingmerchant.model.Customer;
import buildingmerchant.model.Delivery;
import buildingmerchant.model.Order;
import buildingmerchant.model.OrderLine;
import buildingmerchant.model.Product;
import buildingmerchant.model.SimpleProduct;

public class OrderUI {
	private OrderController orderController;
	private CustomerController customerController;
	private ProductController productController;
	
	public OrderUI() {
		orderController = new OrderController();
		customerController = new CustomerController();
		productController = new ProductController();
	}
	
	private void orderUI() {
		boolean running = true;
		while (running) {
			int choice = writeOrderMenu();
			switch (choice) {
			case 1:
				createOrder();
				break;
			case 2:
				getOrder();
			case 0:
				running = false;
				break;
			default:
				System.out.println("An unexplained error has occurred with choice = " + choice);
				break;
			}
		}
	}
	
	private void getOrder() {
		System.out.println("Enter order number: ");
		int orderNumber = getIntegerFromUser();
		Order order = orderController.findOrder(orderNumber);
		if (order == null) {
			System.out.println("No order found with number: " + orderNumber);
			return;
		}
		order.printOrder();
	}

	private void createOrder() {
		System.out.println("Creating order");
		System.out.println("Insert phone number");
		String phoneNumber = getStringFromUser();
		if (phoneNumber.length() != 8) {
			System.out.println("Invalid phone number");
			return;
		}
		Customer customer = findCustomer(phoneNumber);
		if (customer == null) {
			System.out.println("Customer does not exist");
			return;
		}
		System.out.println("Customer found: ");
		customer.printCustomer();
		Order order = new Order(customer);
		while(true) {
			System.out.println("Scan barcode on product or press 0 to complete scanning of products:");
			String barcode = getStringFromUser();
			if (barcode.equals("0")) {
				System.out.println("Stops scanning of products");
				break;
			}
			if (barcode.isEmpty()) {
				System.out.println("Invalid barcode. Try again");
				continue;
			}
			System.out.println("Barcode scanned correctly - Finds product");
			Product product = findProduct(barcode);
			if (product == null) {
				System.out.println("Products not found. Try again");
				continue;
			}
			product.printProduct();
			
			System.out.println("Enter quantity: ");
			
			int quantity = getIntegerFromUser();
			if (quantity == 0) {
				System.out.println("0 is not a valid quantity. Try again.");
				continue;
			}
			
			// Check if we already have a product of this kind in the order. If we do, we add the quantities.
			// If we don't have it, we add a new line
			OrderLine newLine = new OrderLine(product, quantity);
			boolean lineExistsForProduct = false;
			for (OrderLine orderLine : order.getLines()) {
				if (newLine.getProduct().equals(orderLine.getProduct())) {
					lineExistsForProduct = true;
					int existingQuantity = orderLine.getQuantity();
					orderLine.setQuantity(existingQuantity + quantity);
				}
			}
			
			if (!lineExistsForProduct) {
				order.addOrderLine(newLine);
			}
			order.printOrderLines();
		}
		if (order.getLines().isEmpty()) {
			System.out.println("No products selected. Cancels order creation.");
			return;
		}
		
		String address = customer.getAddress();
		Date currentDate = new Date();
		
		// Currently using current time as delivery time.
		Delivery delivery = createDelivery(address, currentDate);
		
		order.setDelivery(delivery);		
		System.out.println("");
		System.out.println("Order prepared");
		order.printOrder();
		
		System.out.println("Confirm order? Press j/n.");
		String confirmation = getStringFromUser();
		if (confirmation.equals("j")) {
			boolean result = orderController.createOrder(order);
			if (result) {
				System.out.println("The order has been created correctly with number: " + order.getOrderNumber());
				System.out.println("Invoice sent to: " + order.getCustomer().getEmailAddress());
			} else {
				System.out.println("The order could not be created.");
			}
			
		} else if (confirmation.equals("n")) {
			System.out.println("The order has been cancelled.");
		}
	}
	
	private Delivery createDelivery(String deliveryAddress, Date deliveryDate) {
		Delivery delivery = new Delivery(deliveryAddress, deliveryDate);
		return delivery;
	}
	
	/**
	 * Finds a customer based on their phone number
	 * @param phoneNumber
	 * @return
	 */
	private Customer findCustomer(String phoneNumber) {
		return customerController.findCustomer(phoneNumber);
	}
	
	/**
	 * Finds a product based on a bar code
	 * @param barcode
	 * @return
	 */
	private Product findProduct(String barcode) {
		return productController.findProduct(barcode);
	}

	public void start() {
		orderUI();
	}
	
	private int writeOrderMenu() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("****** Order menu ******");
		System.out.println(" (1) Create order");
		System.out.println(" (2) Find order");
		System.out.println(" (0) Back");
		System.out.print("\n Choose:");
		int choice = getIntegerFromUser();
		return choice;
	}
	
	/**
	 * Scans the input for the next integer entered by the user
	 * @return
	 */
	private int getIntegerFromUser() {
		Scanner keyboard = new Scanner(System.in);
		while (!keyboard.hasNextInt()) {
			System.out.println("Input must be a number - try again");
			keyboard.nextLine();
		}
		return keyboard.nextInt();
	}
	
	/**
	 * Scans the input for the next string entered by the user
	 * @return
	 */
	private String getStringFromUser() {
		Scanner keyboard = new Scanner(System.in);
		while (!keyboard.hasNextLine()) {
			System.out.println("A value must be entered");
			keyboard.nextLine();
		}
		return keyboard.nextLine();
	}
	
	
}
