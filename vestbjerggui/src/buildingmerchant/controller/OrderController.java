package buildingmerchant.controller;

import buildingmerchant.model.Order;
import buildingmerchant.model.OrderContainer;

public class OrderController {
	private OrderContainer orderContainer;
	public OrderController() {
		orderContainer = OrderContainer.getInstance();
	}
	
	public boolean createOrder(Order order) {
		int orderNumber = orderContainer.getLatestOrderNumber();
		order.setOrderNumber(orderNumber);
		order.confirmOrder();
		boolean success = orderContainer.addOrder(order);
		return success;
	}

	public Order findOrder(int orderNumber) {
		return orderContainer.getOrder(orderNumber);
	}
}
