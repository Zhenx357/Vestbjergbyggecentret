package buildingmerchant.controller;

import buildingmerchant.model.Loan;
import buildingmerchant.model.LoanContainer;

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
}
	
