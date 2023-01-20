package buildingmerchant.model;

public abstract class Tool {
	private String toolName;
	private String barcode;
	private double pricePerDay;
	
	public Tool(String toolName) {
		this.toolName = toolName;
	}
	
	public String getToolName() {
		return toolName;
	}
	
	public String getBarcode() {
		// TODO Auto-generated method stub
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public double getPricePerDay() {
		return pricePerDay;
	}
	
	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public String getInfo() {
		// TODO Auto-generated method stub
		return toolName + " | " + barcode + " | " + pricePerDay + " Dkk per day";
	}
	
	public void printTool() {
		System.out.println("Tool: " + toolName + "\n");
	}

}
