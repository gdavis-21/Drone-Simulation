module Barishal {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens com.barishal to javafx.graphics, javafx.fxml;
	opens com.barishal.view to javafx.fxml;
	exports com.barishal.view to javafx.fxml;
}
