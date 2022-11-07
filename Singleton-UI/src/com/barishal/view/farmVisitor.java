package com.barishal.view;

public class farmVisitor implements Visitor {
	/*
	 * Market Value: Total=800 Barn=(Tractor, Cow) Tractor=500 Cow=300
	 * Purchase Value: Total=8500 Barn=(Barn, MilkStorage, Cow) Barn=5000 MilkStorage =3000 Cow=5000
	 * 
	 * Override Event Action to display text within fx:id="valueTextArea" 
	 * */
	
	@Override
	public void visit(Farm farm) {
		// Overall Interface
	}

	@Override
	public double visit(item item) {
		return item.getPrice();
	}
	
	/*
	 * Calculates Market Value, does not include container values
	 * double total = 0;
		for(int x=0; x <=(itemContainer.getCollectionOfItemContainers().size()); x++) {
			for(item i : itemContainer.getCollectionOfItemContainers().get(x).getCollectionOfItems()) { //grabs price of items within
				total += i.getPrice();
			}
		}
		return total ;
	*/
	
	@Override
	//Calculates Purchase Value
	public double visit(itemContainer itemContainer) {
		double total = 0;
		for(int x=0; x <=(itemContainer.getCollectionOfItemContainers().size()); x++) {
			total += itemContainer.getCollectionOfItemContainers().get(x).getPrice(); //grabs price of itemContainer
			for(item i : itemContainer.getCollectionOfItemContainers().get(x).getCollectionOfItems()) { //grabs price of items within
				total += i.getPrice();
			}
		}
		return total;
	}

}
