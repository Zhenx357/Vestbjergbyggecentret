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
		System.out.println("Kunde: ");
		System.out.println("Navn: " + getName());
		System.out.println("Kunde ID: " + customerID);
		System.out.println("Telefonnummer: " + getPhoneNumber());
		System.out.println("Adresse: " + getAddress());
		System.out.println("--------------------------------------------------");
	}
}
