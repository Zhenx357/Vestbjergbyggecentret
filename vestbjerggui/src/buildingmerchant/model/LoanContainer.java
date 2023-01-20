package buildingmerchant.model;

import java.util.ArrayList;

public class LoanContainer {
	private static LoanContainer instance;
	private ArrayList<Loan> loan;

	private LoanContainer() {
		loan = new ArrayList<>();
	}
	/**
	 * Tries to add an order to the containers order list. If a order already exists for the order number, 
	 * the method returns false.
	 * @param order 
	 * @return
	 */
	public boolean addLoan(Loan loan) {
		Loan existingLoan = getLoan(loan.getLoanNumber());
		if (existingLoan != null) {
			return false;
		}
		Loan.add(loan);
		return true;
		
	}
	
	public Loan getLoan(int loanNumber) {
		for (Loan loan : loan) {
			if (loan.getLoanNumber() == loanNumber) {
				return loan;
			}
		}
		return null;
	}
	
	public int getLatestLoanNumber() {
		int loanCount = loan.size();
		if (loanCount == 0) {
			return 1;
		}
		Loan lastLoan = loan.get(loanCount - 1);
		return lastLoan.getLoanNumber() + 1;
	}
	
	public static LoanContainer getInstance() {
		if (instance == null) {
			instance = new LoanContainer();
		}
		return instance;
	}
}
