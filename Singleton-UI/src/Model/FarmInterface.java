package Model;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import java.util.List;

public interface FarmInterface {
	
		//Setters
		void changeName(String newName);
		void changePrice(double newPrice);
		void changeLocationX(double newLocationX);
		void changeLocationY(double newLocationY);
		void changeLength(double newLength);
		void changeWidth(double newWidth);
		void changeHeight(double newHeight);
		void changeStackPane(StackPane newStackPane);
		void changeLabel(Label newLabel);
		
		//Getters
		String getName();
		double getPrice();
		double getLocationX();
		double getLocationY();
	    double getLength();
	    double getWidth();
	    double getHeight();
	    
	    Rectangle getRectangle();
	    List<item> getItems();
	    List<itemContainer> getItemContainers();
		StackPane getStackPane();
		Label getLabel();
		
		//Ect....
	    String toString();
		
	    /*
	    public void addItemContainer(itemContainer ItemContainer);
	    public void deleteItemContainer(itemContainer ItemContainer);
	    public void addItem(item Item);
	    public double accept(Visitor v); // [10/06] addition visitor pattern
	    */
}
