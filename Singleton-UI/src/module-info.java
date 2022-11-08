module Barishal {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	requires javafx.graphics;
	requires jdk.xml.dom;
	
	opens Main to javafx.graphics, javafx.fxml;
	//opens View to javafx.fxml;
	//exports View to javafx.fxml;
}
