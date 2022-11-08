package Model;

import java.util.List;

public class itemContainer extends item{
	
	List<FarmInterface> collectionOfItems;
	private String name = "";
	private double price = 0;
	private double locationX = 0;
	private double locationY = 0;
	private double length = 0;
	private double width = 0;
	private double height = 0;
	
	public itemContainer(String name, List<FarmInterface> children, double price, double locationX, double locationY, double length, double width, double height) {
		super(name, price, locationX, locationY, length, width, height);
		this.name = name;
		this.price = price;
		this.locationX = locationX;
		this.locationY = locationY;
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	public void deleteItem(item Item) {
		collectionOfItems.remove(Item);
    }
	
	public void addItem(item Item) {
		collectionOfItems.add(Item);
	}
	
	public List<FarmInterface> getList(){
		return collectionOfItems;
	}
}
