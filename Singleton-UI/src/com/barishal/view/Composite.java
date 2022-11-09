package com.barishal.view;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

interface Farm{
    public void deleteItem(item Item);
    public void changeName(String newName);
    public void changePrice(double newPrice);
    public void changeLocationX(double newLocationX);
    public void changeLocationY(double newLocationY);
    public void changeLength(double newLength);
    public void changeWidth(double newWidth);
    public void changeHeight(double newHeight);
    public String getName();
    public Double getPrice();
    public Double getLocationX();
    public Double getLocationY();
    public Double getLength();
    public Double getWidth();
    public Double getHeight();
    public Rectangle getRectangle();
    public StackPane getStackPane();
    public Label getLabel();
    public void setStackPane(StackPane stackPane);
    public void setRectangle(Rectangle newRectangle);
    public void addItemContainer(itemContainer ItemContainer);
    public void deleteItemContainer(itemContainer ItemContainer);
    public void addItem(item Item);
    public List<Double> accept(Visitor v);
    public List<itemContainer> getCollectionOfItemContainers();
    public List<item> getCollectionOfItems();
}

class itemContainer implements Farm{

    private String name;
    private double price;
    private double locationX;
    private double locationY;
    private double length;
    private double width;
    private double height;
    private Rectangle rectangle;
    private StackPane stackPane;
    private Label label;

    private List<itemContainer> collectionOfItemContainers = new ArrayList<itemContainer>();
    private List<item> collectionOfItems = new ArrayList<item>();

    public itemContainer(String name, double price, double locationX, double locationY, double length, double width, double height){
        this.name = name;
        this.price = price;
        this.locationX = locationX;
        this.locationY = locationY;
        this.length = length;
        this.width = width;
        this.height = height;
        
        stackPane = new StackPane();
        label = new Label();
        label.setText(name);
        rectangle = (new Rectangle(locationX, locationY, length, width));
        
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        
        stackPane.getChildren().add(rectangle);
        stackPane.getChildren().add(label);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        
        stackPane.setAlignment(Pos.TOP_CENTER);

    }

    @Override
    public void deleteItem(item Item) {
        collectionOfItems.remove(Item);
    }

    @Override
    public void changeName(String newName) {
        this.name = newName;
        label.setText(newName);
    }

    @Override
    public void changePrice(double newPrice) {
        this.price = newPrice;
    }

    @Override
    public void changeLocationX(double newLocationX) {
        this.locationX = newLocationX;
        rectangle.setX(newLocationX);
    }

    @Override
    public void changeLocationY(double newLocationY) {
        this.locationY = newLocationY;
        rectangle.setY(newLocationY);
    }

    @Override
    public void changeLength(double newLength) {
        this.length = newLength;
        rectangle.setHeight(length);;
    }

    @Override
    public void changeWidth(double newWidth) {
        this.width = newWidth;
        rectangle.setWidth(newWidth);
    }

    @Override
    public void changeHeight(double newHeight) {
        this.height = newHeight;
    }
    
    @Override
    public String getName() {
    	return this.name;
    }
    
    @Override
    public Double getPrice() {
    	return this.price;
    }
    
    @Override
    public Double getLocationX() {
    	return this.locationX;
    }
    
    @Override
    public Double getLocationY() {
    	return this.locationY;
    }
    
    @Override
    public Double getLength() {
    	return this.length;
    }
    
    @Override
    public Double getWidth() {
    	return this.width;
    }
    
    @Override
    public Double getHeight() {
    	return this.height;
    }

    @Override
    public void addItemContainer(itemContainer ItemContainer) {
        collectionOfItemContainers.add(ItemContainer);
    }

    @Override
    public void deleteItemContainer(itemContainer ItemContainer) {
        collectionOfItemContainers.remove(ItemContainer);
    }

    @Override
    public void addItem(item Item) {
        collectionOfItems.add(Item);
    }

    public String getContainerName(itemContainer Container) {
        return Container.name;
    }
    
    public List<itemContainer> getCollectionOfItemContainers(){
    	return this.collectionOfItemContainers;
    }
    
    public List<item> getCollectionOfItems(){
    	return this.collectionOfItems;
    }
    
    public void printItemContainerCollection() {
        System.out.println("\nPrinting item container collection(s) for " + this.name + ":");
        for (itemContainer i : collectionOfItemContainers) {
            System.out.println(getContainerName(i));
        }
    }

    public void printItems(){
        System.out.println("\nPrinting item(s) for " + this.name + ":");
        for (item i : collectionOfItems) {
            System.out.println((i.getName()));
        }
    }
    
    public String toString() {
    	return this.name;
    }

    @Override
	public Rectangle getRectangle() {
		return rectangle;
	}
    
    @Override
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

    @Override
	public StackPane getStackPane() {
		return stackPane;
	}

	@Override
	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}

	@Override
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	@Override
	public List<Double> accept(Visitor v) {
		return v.visit(this, 0.0);
	}
}

class item implements Farm{

    private String name;
    private double price;
    private double locationX;
    private double locationY;
    private double length;
    private double width;
    private double height;
    private Rectangle rectangle;
    private StackPane stackPane;
    private Label label;

    private List<item> collectionOfItems = new ArrayList<item>();

    public item(String name, double price, double locationX, double locationY, double length, double width, double height){
        this.name = name;
        this.price = price;
        this.locationX = locationX;
        this.locationY = locationY;
        this.length = length;
        this.width = width;
        this.height = height;
        stackPane = new StackPane();
        label = new Label();
        label.setText(name);
        rectangle = (new Rectangle(locationX, locationY, length, width));
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        
        stackPane.getChildren().add(rectangle);
        stackPane.getChildren().add(label);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        
        stackPane.setAlignment(Pos.TOP_CENTER);
    }

    @Override
    public void deleteItem(item Item) {
        collectionOfItems.remove(Item);
    }

    @Override
    public void changeName(String newName) {
        this.name = newName;
        label.setText(newName);
    }

    @Override
    public void changePrice(double newPrice) {
        this.price = newPrice;
    }

    @Override
    public void changeLocationX(double newLocationX) {
        this.locationX = newLocationX;
        rectangle.setX(newLocationX);
        
    }

    @Override
    public void changeLocationY(double newLocationY) {
        this.locationY = newLocationY;
        rectangle.setY(newLocationY);
        
    }

    @Override
    public void changeLength(double newLength) {
        this.length = newLength;
        rectangle.setHeight(newLength);
        
    }

    @Override
    public void changeWidth(double newWidth) {
        this.width = newWidth;
        rectangle.setWidth(newWidth);
        
    }

    @Override
    public void changeHeight(double newHeight) {
        this.height = newHeight;
        
    }

	@Override
	public void addItemContainer(itemContainer ItemContainer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteItemContainer(itemContainer ItemContainer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addItem(item Item) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public String toString() {
    	return this.name;
    }
    
	@Override
    public String getName() {
    	return this.name;
    }
    
	@Override
    public Double getPrice() {
    	return this.price;
    }
    
	@Override
    public Double getLocationX() {
    	return this.locationX;
    }
    
	@Override
    public Double getLocationY() {
    	return this.locationY;
    }
    
	@Override
    public Double getLength() {
    	return this.length;
    }
    
	@Override
    public Double getWidth() {
    	return this.width;
    }
    
	@Override
    public Double getHeight() {
    	return this.height;
    }

	@Override
	public Rectangle getRectangle() {
		return rectangle;
	}

	@Override
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	@Override
	public StackPane getStackPane() {
		return stackPane;
	}

	@Override
	public void setStackPane(StackPane stackPane) {
		this.stackPane = stackPane;
	}

	@Override
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
	
	@Override
	public List<Double> accept(Visitor v) {
		return v.visit(this);
	}

	@Override // sorry????
	public List<itemContainer> getCollectionOfItemContainers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override // sorry????
	public List<item> getCollectionOfItems() {
		// TODO Auto-generated method stub
		return null;
	}
	
}


public class Composite{
    public static void main(String[] args) {

//        //------------------------------------------------------------First Scenario---------------------------------------------------------
//        itemContainer Barn = new itemContainer("Barn", 175621.42, 567, 42, 42, 20, 15);
//        itemContainer liveStockArea = new itemContainer("Live-Stock-Area", 5765.21, 567, 42, 12, 16, 8);
//        item milkStorage = new item("Milk Storage", 25.75, 567, 42, 4, 5, 3);
//        item cow = new item("Cow", 2000.00, 567, 42, 62, 28, 68);
//
//        Barn.addItemContainer(liveStockArea);
//        Barn.addItem(milkStorage);
//        liveStockArea.addItem(cow);
//
//        Barn.printItemContainerCollection();
//        Barn.printItems();
//
//        liveStockArea.printItems();
//
//
//        //------------------------------------------------------------Second Scenario---------------------------------------------------------
//        itemContainer storageBuilding = new itemContainer("Storage Building", 7500.35, 565, 40, 65, 20, 25);
//        item tractor = new item("Tractor", 15000, 565, 40, 12, 6, 4);
//        item tiller = new item("Tiller", 10000, 565, 40, 10, 4, 4);
//
//        storageBuilding.addItem(tractor);
//        storageBuilding.addItem(tiller);
//        
//        storageBuilding.printItems();
//
//
//        //------------------------------------------------------------Third Scenario---------------------------------------------------------
//        itemContainer commandCenter = new itemContainer("Command Center", 8500, 560, 35, 40, 30, 20);
//        item drone = new item("Drone", 100, 560, 35, 4, 4, 1);
//        
//        commandCenter.addItem(drone);
//        commandCenter.printItems();
//
//        //------------------------------------------------------------Fourth Scenario---------------------------------------------------------
//        item soy = new item("Soy", 10.20, 500, 15, 0.5, 0.5, 2);
//
//
//        System.out.println("\n");
    } 
}