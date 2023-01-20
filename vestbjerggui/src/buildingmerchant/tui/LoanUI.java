package buildingmerchant.tui;

import buildingmerchant.controller.CustomerController;
import buildingmerchant.controller.LoanController;
import buildingmerchant.controller.ToolController;

public class LoanUI {
	private LoanController loanController;
	private CustomerController customerController;
	private ToolController toolController;
	
	public LoanUI() {
		loanController = new LoanController();
		customerController = new CustomerController();
		toolController = new ToolController();
	}
	
}
