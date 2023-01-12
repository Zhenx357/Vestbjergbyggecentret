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
				System.out.println("En uforklarlig fejl er sket med choice = " + choice);
				break;
			}
		}
	}
	
	private void getOrder() {
		System.out.println("Indtast ordrenummer: ");
		int orderNumber = getIntegerFromUser();
		Order order = orderController.findOrder(orderNumber);
		if (order == null) {
			System.out.println("Ingen ordre fundet med nummer: " + orderNumber);
			return;
		}
		order.printOrder();
	}

	private void createOrder() {
		System.out.println("Opretter ordre");
		System.out.println("Indtast telefonnummer");
		String phoneNumber = getStringFromUser();
		if (phoneNumber.length() != 8) {
			System.out.println("Ugyldigt telefonnummer");
			return;
		}
		Customer customer = findCustomer(phoneNumber);
		if (customer == null) {
			System.out.println("Kunde ikke fundet");
			return;
		}
		System.out.println("Kunde fundet: ");
		customer.printCustomer();
		Order order = new Order(customer);
		while(true) {
			System.out.println("Scan stregkode på produkt eller tast 0 for at færdiggøre scanning af produkter:");
			String barcode = getStringFromUser();
			if (barcode.equals("0")) {
				System.out.println("Stopper scanning af produkter");
				break;
			}
			if (barcode.isEmpty()) {
				System.out.println("Ugyldig stregkode. Prøv igen");
				continue;
			}
			System.out.println("Stregkode scannet korrekt - Finder produkt");
			Product product = findProduct(barcode);
			if (product == null) {
				System.out.println("Produkt ikke fundet. Prøv igen");
				continue;
			}
			product.printProduct();
			
			System.out.println("Indtast antal: ");
			
			int quantity = getIntegerFromUser();
			if (quantity == 0) {
				System.out.println("0 er ikke en gyldig mængde. Prøv igen.");
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
			System.out.println("Ingen produkter valgt. Afbryder ordreoprettelse.");
			return;
		}
		
		String address = customer.getAddress();
		Date currentDate = new Date();
		
		// Currently using current time as delivery time.
		Delivery delivery = createDelivery(address, currentDate);
		
		order.setDelivery(delivery);		
		System.out.println("");
		System.out.println("Ordre forberedt");
		order.printOrder();
		
		System.out.println("Bekræft ordre? Tryk j/n.");
		String confirmation = getStringFromUser();
		if (confirmation.equals("j")) {
			boolean result = orderController.createOrder(order);
			if (result) {
				System.out.println("Ordren er oprettet korrekt med nummer: " + order.getOrderNumber());
				System.out.println("Faktura sendt til: " + order.getCustomer().getEmailAddress());
			} else {
				System.out.println("Ordren kunne ikke oprettes.");
			}
			
		} else if (confirmation.equals("n")) {
			System.out.println("Ordren er annulleret.");
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
		System.out.println("****** Ordremenu ******");
		System.out.println(" (1) Opret ordre");
		System.out.println(" (2) Find ordre");
		System.out.println(" (0) Tilbage");
		System.out.print("\n Vælg:");
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
			System.out.println("Input skal være et tal - prøv igen");
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
			System.out.println("Der skal indtastest en værdi");
			keyboard.nextLine();
		}
		return keyboard.nextLine();
	}
	
	
}
