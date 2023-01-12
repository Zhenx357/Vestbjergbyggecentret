package buildingmerchant.controller;

import buildingmerchant.model.BusinessCustomer;
import buildingmerchant.model.Customer;
import buildingmerchant.model.CustomerContainer;
import buildingmerchant.model.PrivateCustomer;

public class CustomerController {
	private CustomerContainer container;

	public CustomerController() {
		container = CustomerContainer.getInstance();
	}

	public Customer findCustomer(String phoneNumber) {
		return container.findCustomer(phoneNumber);
	}

	public void generateTestCustomers() {
		
		Customer customer1 = new PrivateCustomer();
		customer1.setAddress("testaddress 1");
		customer1.setName("Bo Jensen");

		customer1.setPhoneNumber("11111111");
		customer1.setEmailAddress("test123@email.com");
		int ID = container.getLatestCustomerID();
		customer1.setCustomerID(ID);
		container.saveCustomer(customer1);

		Customer customer2 = new BusinessCustomer();
		customer2.setName("Finn Jensen");
		customer2.setAddress("Forretningsaddresse 1");
		customer2.setPhoneNumber("22222222");
		customer2.setEmailAddress("test234@email.com");

		ID = container.getLatestCustomerID();
		customer2.setCustomerID(ID);
		container.saveCustomer(customer2);
	}
}
