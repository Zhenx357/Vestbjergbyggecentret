package buildingmerchant.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	private ArrayList<OrderLine> orderLines;
	private ArrayList<Employee> employees;
	private int orderNumber;
	private Date date;
	private Customer customer;
	private Delivery delivery;
	private boolean confirmed;
	
	public Order(Customer customer) {
		orderLines = new ArrayList<>();
		employees = new ArrayList<>();
		date = new Date();
		this.setCustomer(customer);
		confirmed = false;
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}

	public int getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public void addOrderLine(OrderLine orderLine) {
		orderLines.add(orderLine);
	}
	
	public void addOrderLines(ArrayList<OrderLine> lines) {
		lines.addAll(lines);
	}

	public Date getDate() {
		return date;
	}
	
	public double getTotalPrice() {
		double totalPrice = 0;
		for (OrderLine orderLine : orderLines) {
			totalPrice += orderLine.getProductPrice() * orderLine.getQuantity();
		}
		return totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	
	/**
	 * Marks the order as confirmed. This is done when the order is saved in the system.
	 */
	public void confirmOrder() {
		this.confirmed = true;
	}
	
	/**
	 * Prints out the order and all it lines
	 */
	public void printOrder() {
		System.out.println("");
		System.out.println("----------------------------------------------------------");
		System.out.println("");
		if (isConfirmed()) {
			System.out.println("Order number: " + orderNumber);
		} else {
			System.out.println("Order not created");
		}
		System.out.println("");
		customer.printCustomer();
		System.out.println("");
		System.out.println("Ordered: " + date.toString());
		System.out.println("Total price: " + getTotalPrice());
		System.out.println("");
		delivery.printDelivery();
		System.out.println("");
		printOrderLines();
		System.out.println("----------------------------------------------------------");
		System.out.println("");
		
	}
	

	/**
	 * Prints all products that has been added to an order
	 * @param orderLines
	 */
	public void printOrderLines() {
		System.out.println("--------------------------------------------------");
		System.out.println("Added products: ");
		for (OrderLine line : orderLines) {
			System.out.println(line.getInfo());
		}
		System.out.println("--------------------------------------------------");
	}

	public boolean isConfirmed() {
		return confirmed;
	}
	
	public ArrayList<OrderLine> getLines() {
		return orderLines;
	}
}
