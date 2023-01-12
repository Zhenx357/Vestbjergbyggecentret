package buildingmerchant.model;

import java.util.ArrayList;

public class CustomerContainer {
	private static CustomerContainer instance;
	private ArrayList<Customer> customers;
	
	private CustomerContainer() {
		customers = new ArrayList<>();
	}
	
	public Customer findCustomer(String phoneNumber) {
		for (Customer customer : customers) {
			if (customer.getPhoneNumber().equals(phoneNumber)) {
				return customer;
			}
		}
		return null;
	}
	
	public int getLatestCustomerID() {
		int customerCount = customers.size();
		if (customerCount == 0) {
			return 1;
		}
		// We subtract 1 because the first index in the array is 0 to make sure we get the last index.
		Customer lastOrder = customers.get(customerCount - 1);
		return lastOrder.getCustomerID() + 1;
	}
	
	
	public static CustomerContainer getInstance() {
		if (instance == null) {
			instance = new CustomerContainer();
		}
		return instance;
	}

	public void saveCustomer(Customer customer) {
		Customer existingCustomer = findCustomer(customer.getPhoneNumber());
		if (existingCustomer != null) {
			return;
		}
		customers.add(customer);
	}
}
