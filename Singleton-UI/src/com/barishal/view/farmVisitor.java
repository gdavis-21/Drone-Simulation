package com.barishal.view;

import java.util.ArrayList;
import java.util.List;

public class farmVisitor implements Visitor {
	double sum = 0;
	
	@Override
	public void visit(Farm farm) {
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
		
		for (item i: itemContainer.getCollectionOfItems()) {
			purchasePrice+= i.getPrice();
			currentMarketValue += i.getPrice();
		}
		for (itemContainer ic: itemContainer.getCollectionOfItemContainers()) {
			purchasePrice+= ic.getPrice();
			for (item i: ic.getCollectionOfItems()) {
				currentMarketValue += i.getPrice();
			}
		}
				
		List<Double> priceList = new ArrayList<>();
		priceList.add(purchasePrice);
		priceList.add(currentMarketValue);
		return priceList;
	}

}
