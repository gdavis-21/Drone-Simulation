package com.barishal.view;

public interface Visitor {
	void visit(Farm farm); //overall interface
	double visit(item item);
	double visit(itemContainer itemContainer);
}
