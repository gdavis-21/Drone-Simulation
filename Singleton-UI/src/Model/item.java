package Model;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class item implements FarmInterface{
	
	private String name = "";
	private double price = 0;
	private double locationX = 0;
	private double locationY = 0;
	private double length = 0;
	private double width = 0;
	private double height = 0;
	private Rectangle rectangle;
	private StackPane stackPane;
	private Label label;
	private List<itemContainer> collectionOfItemContainers = new ArrayList<itemContainer>();
    private List<item> collectionOfItems = new ArrayList<item>();
	
	public item(String name, double price, double locationX, double locationY, double length, double width, double height) {
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
	public void changeName(String newName) {
		this.name = newName;
		
	}

	@Override
	public void changePrice(double newPrice) {
		this.price = newPrice;
		
	}

	@Override
	public void changeLocationX(double newLocationX) {
		this.locationX = newLocationX;
		
	}

	@Override
	public void changeLocationY(double newLocationY) {
		this.locationY = newLocationY;
		
	}

	@Override
	public void changeLength(double newLength) {
		this.length = newLength;
		
	}

	@Override
	public void changeWidth(double newWidth) {
		this.width = newWidth;
		
	}

	@Override
	public void changeHeight(double newHeight) {
		this.height = newHeight;
		
	}

	@Override
	public void changeStackPane(StackPane newStackPane) {
		this.stackPane = newStackPane;
	}
	
	@Override
	public void changeLabel(Label newLabel) {
		this.label = newLabel;
		
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public double getLocationX() {
		return this.locationX;
	}

	@Override
	public double getLocationY() {
		return this.locationY;
	}

	@Override
	public double getLength() {
		return this.length;
	}

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public double getHeight() {
		return this.height;
	}

	@Override
	public Rectangle getRectangle() {
		return rectangle;
	}

	@Override
	public List<item> getItems() {
		return collectionOfItems;
	}

	@Override
	public List<itemContainer> getItemContainers() {
		return collectionOfItemContainers;
	}

	@Override
	public StackPane getStackPane() {
		return this.stackPane;
	}

	@Override
	public Label getLabel() {
		return label;
	}
}
