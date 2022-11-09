package Controller;

import java.util.*;
import Model.*;

public class VisitorController implements VisitorInterface {
	double sum = 0;
	
	@Override
	public void visit(FarmInterface farm) {
	}

	@Override
	public List<Double> visit(item item) {
		List<Double> priceList = new ArrayList<Double>();
		double purchasePrice = item.getPrice();
		double currentMarketValue = item.getPrice();
		priceList.add(purchasePrice);
		priceList.add(currentMarketValue);
		return priceList;
	}
	
	@Override
	public List<Double> visit(itemContainer itemContainer, double sum) {
		double purchasePrice = itemContainer.getPrice();
		double currentMarketValue = 0;
		
		for (item i: itemContainer.getItems()) {
			purchasePrice+= i.getPrice();
			currentMarketValue += i.getPrice();
		}
		for (itemContainer ic: itemContainer.getItemContainers()) {
			purchasePrice+= ic.getPrice();
			for (item i: ic.getItems()) {
				currentMarketValue += i.getPrice();
			}
		}
				
		List<Double> priceList = new ArrayList<>();
		priceList.add(purchasePrice);
		priceList.add(currentMarketValue);
		return priceList;
	}

}
