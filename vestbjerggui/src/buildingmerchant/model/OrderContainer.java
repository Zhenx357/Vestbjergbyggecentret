package buildingmerchant.model;

import java.util.ArrayList;

public class OrderContainer {
	private static OrderContainer instance;
	private ArrayList<Order> orders;

	private OrderContainer() {
		orders = new ArrayList<>();
	}
	
	/**
	 * Tries to add an order to the containers order list. If a order already exists for the order number, 
	 * the method returns false.
	 * @param order 
	 * @return
	 */
	public boolean addOrder(Order order) {
		Order existingOrder = getOrder(order.getOrderNumber());
		if (existingOrder != null) {
			return false;
		}
		orders.add(order);
		return true;
		
	}
	
	public Order getOrder(int orderNumber) {
		for (Order order : orders) {
			if (order.getOrderNumber() == orderNumber) {
				return order;
			}
		}
		return null;
	}
	
	public int getLatestOrderNumber() {
		int orderCount = orders.size();
		if (orderCount == 0) {
			return 1;
		}
		Order lastOrder = orders.get(orderCount - 1);
		return lastOrder.getOrderNumber() + 1;
	}
	
	public static OrderContainer getInstance() {
		if (instance == null) {
			instance = new OrderContainer();
		}
		return instance;
	}
	
}
