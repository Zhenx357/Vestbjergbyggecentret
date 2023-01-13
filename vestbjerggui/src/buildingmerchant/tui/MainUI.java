package buildingmerchant.tui;

import java.util.Scanner;

import buildingmerchant.controller.CustomerController;
import buildingmerchant.controller.ProductController;

public class MainUI {
	private OrderUI orderUI;
	private ProductController productController;
	private CustomerController customerController;
	
	public MainUI() {
		orderUI = new OrderUI();
		productController = new ProductController();
		customerController = new CustomerController();
	}
	
	
	public void start() {
		mainUI();
	}

	private void mainUI() {
		boolean running = true;
		while (running) {
			int choice = writeMainMenu();
			switch (choice) {
			case 1:
				orderUI.start();
				break;
			case 9:
				generateTestData();
				break;
			case 0:
				System.out.println("Thanks for now.");
				running = false;
				break;
			default:
				System.out.println("An unexplained error has occured, choice = " + choice);
				break;
			}
		}
	}
	
	private int writeMainMenu() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("****** Main menu ******");
		System.out.println(" (1) Order menu");
		System.out.println(" (9) Generate test data");
		System.out.println(" (0) Exit the program");
		System.out.print("\n Choose:");

		while (!keyboard.hasNextInt()) {
			System.out.println("Input must be a number - try again");
			keyboard.nextLine();
		}
		int choice = keyboard.nextInt();
		return choice;
	}
	
	private void generateTestData() {
		customerController.generateTestCustomers();
		productController.generateTestProducts();
	}
}
