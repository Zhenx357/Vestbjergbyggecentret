package buildingmerchant.model;

public class LoanLine {
	private Tool tool;
	private int quantity;
	
	public LoanLine(Product product, int quantity) {
		this.setTool(tool);
		this.setQuantity(quantity);
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getToolPrice() {
		return tool.getPricePerDay();
	}
	
	public double getTotalPrice() {
		return tool.getPricePerDay() * quantity;
	}

	public String getInfo() {
		return "Tool: " + tool.getToolName() + ", quantity: " + quantity + ", price per unit: " + getToolPrice() + ", price: " + getTotalPrice();
	}
}
