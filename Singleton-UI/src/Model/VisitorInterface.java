package Model;

import java.util.List;

public interface VisitorInterface {
	void visit(FarmInterface farm); //overall interface
	
	List<Double> visit(item item);
	List<Double> visit(itemContainer itemContainer, double sum);
}
