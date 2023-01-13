package buildingmerchant.model;

public abstract class Customer extends Person {
	private int customerID;
	
	public Customer() {
	}

	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public void printCustomer() {
		System.out.println("--------------------------------------------------");
		System.out.println("Customer: ");
		System.out.println("Name: " + getName());
		System.out.println("Customer ID: " + customerID);
		System.out.println("Phone number: " + getPhoneNumber());
		System.out.println("Adress: " + getAddress());
		System.out.println("--------------------------------------------------");
	}
}
