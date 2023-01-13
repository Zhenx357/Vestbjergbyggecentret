package buildingmerchant.model;

import java.util.ArrayList;

public class ToolContainer {
	private static ToolContainer instance;
	private ArrayList<Tool> tools;
	
	private ToolContainer() {
		this.tools = new ArrayList<>();
	}

	public static ToolContainer getInstance() {
		if(instance == null) {
			instance = new ToolContainer();
		}
		// TODO Auto-generated method stub
		return instance;
	}

	public Tool findTool(String barcode) {
		for (Tool tool : tools) {
			if (tool.getBarcode().equals(barcode)) {
				return tool;
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	public void addTool(Tool tool) {
		Tool existingTool = findTool(tool.getBarcode());
		if (existingTool != null) {
			return;
		}
		tools.add(tool);
		// TODO Auto-generated method stub
		
	}


	public ArrayList<Tool> getAllTools (){
		return tools;
	}
	

	
}
