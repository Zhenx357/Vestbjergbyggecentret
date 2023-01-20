package buildingmerchant.model;

import java.util.ArrayList;
import java.util.Date;

public class Loan {
	private ArrayList<LoanLine> loanLines;
	private ArrayList<Employee> employees;
	private int loanNumber;
	private Date date;
	private Customer customer;
	private boolean confirmed;
	
	public Loan(Customer customer) {
		loanLines = new ArrayList<>();
		employees = new ArrayList<>();
		getDate(new Date());
		this.setCustomer(customer);
		confirmed = false;
	}
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}

	public int getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(int loanNumber) {
		this.loanNumber = loanNumber;
	}
	public void addLoanLine(LoanLine loanLine) {
		loanLines.add(loanLine);
	}
	
	public void addLoanLines(ArrayList<LoanLine> lines) {
		lines.addAll(lines);
	}

	public Date getDate() {
		return date;
	}
	
	public double getTotalPrice() {
		double totalPrice = 0;
		for (LoanLine loanLine : loanLines) {
			totalPrice += loanLine.getToolPrice() * loanLine.getQuantity();
		}
		return totalPrice;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	public static void add(Loan loan) {
		// TODO Auto-generated method stub
		
	}
	public void confirmLoan() {
		// TODO Auto-generated method stub
		this.confirmed = true;
	}
	
	/**
	 * Prints out the order and all it lines
	 */
	public void printLoan() {
		System.out.println("");
		System.out.println("----------------------------------------------------------");
		System.out.println("");
		if (isConfirmed()) {
			System.out.println("Loan number: " + loanNumber);
		} else {
			System.out.println("Loan not created");
		}
		System.out.println("");
		customer.printCustomer();
		System.out.println("");
		System.out.println("Loan: " + date.toString());
		System.out.println("Total rent price: " + getTotalPrice());
		System.out.println("");
		printLoanLines();
		System.out.println("----------------------------------------------------------");
		System.out.println("");
		
	}
	

	/**
	 * Prints all products that has been added to an order
	 * @param orderLines
	 */
	public void printLoanLines() {
		System.out.println("--------------------------------------------------");
		System.out.println("Added tools: ");
		for (LoanLine line : loanLines) {
			System.out.println(line.getInfo());
		}
		System.out.println("--------------------------------------------------");
	}

	public boolean isConfirmed() {
		return confirmed;
	}
	
	public ArrayList<LoanLine> getLines() {
		return loanLines;
	}
}
