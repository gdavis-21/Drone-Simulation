package com.barishal.view;
import java.util.List;

public interface Visitor {
	void visit(Farm farm); //overall interface
	
	List<Double> visit(item item);
	List<Double> visit(itemContainer itemContainer, double sum);
}
