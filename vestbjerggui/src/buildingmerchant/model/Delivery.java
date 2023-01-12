package buildingmerchant.model;

import java.util.Date;

public class Delivery {
	private String deliveryAddress;
	private Date deliveryDate;
	
	public Delivery(String deliveryAddress, Date deliveryDate) {
		this.setDeliveryAddress(deliveryAddress);
		this.setDeliveryDate(deliveryDate);
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public void printDelivery() {
		System.out.println("Leveringsinformationer: ");
		System.out.println("Adresse: " + deliveryAddress);
		System.out.println("Leveringstidspunkt: " + deliveryDate.toString());
		System.out.println("---------------------------------------------------");
	}
}
