module Barishal {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	requires javafx.graphics;
	
	opens com.barishal to javafx.graphics, javafx.fxml;
	opens com.barishal.view to javafx.fxml;
	exports com.barishal.view to javafx.fxml;
}
