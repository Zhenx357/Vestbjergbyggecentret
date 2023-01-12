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
				System.out.println("Tak for denne gang.");
				running = false;
				break;
			default:
				System.out.println("Der er sket en uforklarlig fejl, choice = " + choice);
				break;
			}
		}
	}
	
	private int writeMainMenu() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("****** Hovedmenu ******");
		System.out.println(" (1) Ordremenu");
		System.out.println(" (9) Generer testdata");
		System.out.println(" (0) Afslut programmet");
		System.out.print("\n Vælg:");

		while (!keyboard.hasNextInt()) {
			System.out.println("Input skal være et tal - prøv igen");
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
