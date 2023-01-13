package buildingmerchant.controller;

import java.util.ArrayList;

import buildingmerchant.model.Product;
import buildingmerchant.model.Tool;
import buildingmerchant.model.ToolContainer;
import buildingmerchant.model.ToolCopy;

public class ToolController {
	private ToolContainer container;
	
	public ToolController() {
		container = ToolContainer.getInstance();
	}
	
	public Tool findTool(String barcode) {
		return container.findTool(barcode);
	}

	public void generateTestTools() {
		Tool floorSander = new ToolCopy("Floor Sander");
		floorSander.setBarcode("0005");
		floorSander.setPricePerDay(500);
		container.addTool(floorSander);
		
		Tool sprayingSystem = new ToolCopy("Spraying System");
		sprayingSystem.setBarcode("0006");
		sprayingSystem.setPricePerDay(700);
		container.addTool(sprayingSystem);
		
		Tool hedgeTrimmer = new ToolCopy("Hedge Trimmer");
		hedgeTrimmer.setBarcode("0007");
		hedgeTrimmer.setPricePerDay(400);
		container.addTool(hedgeTrimmer);
		
		Tool chainSaw = new ToolCopy("ChainSaw");
		chainSaw.setBarcode("0008");
		chainSaw.setPricePerDay(300);
		container.addTool(chainSaw);
	}
	public ArrayList<Tool> getAllTools(){
		return container.getAllTools();
	} 

}
