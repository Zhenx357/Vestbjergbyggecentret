package buildingmerchant.controller;

import buildingmerchant.model.Loan;
import buildingmerchant.model.LoanContainer;
import buildingmerchant.model.Order;

public class LoanController {
	private LoanContainer loanContainer;
	public LoanController() {
		loanContainer = LoanContainer.getInstance();
	}
	
	public boolean createLoan(Loan loan) {
		int loanNumber = loanContainer.getLatestLoanNumber();
		loan.setLoanNumber(loanNumber);
		loan.confirmLoan();
		boolean success = loanContainer.addLoan(loan);
		return success;
	}
	public Loan findLoan(int loanNumber) {
		return loanContainer.getLoan(loanNumber);
	}
}
	
