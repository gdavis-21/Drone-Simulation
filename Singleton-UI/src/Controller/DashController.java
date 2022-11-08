package Controller;

import java.util.List;

import Model.FarmInterface;
import Model.item;
import Model.itemContainer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DashController {

	@FXML
	AnchorPane anchorPane;
    
	@FXML
	private TreeView<FarmInterface> treeView;
	@FXML
	private Button renameButton;
	@FXML
	private Button changeLocationButton;
	@FXML
	private Button changePriceButton;
	@FXML
	private Button changeDimensionButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button addItemButton;
	@FXML
	private Button addItemContainerButton;
	@FXML
	private Label buttonsLabel;
	@FXML
	private Button scanFarmButton;
	@FXML
	private Button goToItemButton;
	@FXML
	private Button goToHomeButton;
    @FXML
    private SubScene visual;
	@FXML
	private ImageView droneVisual;
	
	@FXML
	public void initialize() {
		Singleton.getInstance();
		
		TreeItem<FarmInterface> root = new TreeItem<FarmInterface>(new itemContainer("Root", 0, 0, 0, 0, 0, 0));
		TreeItem<FarmInterface> commandCenter = new TreeItem<FarmInterface>(new itemContainer("Command Center", 0, 300, 50, 100, 100, 0));
		TreeItem<FarmInterface> drone = new TreeItem<FarmInterface>(new item("Drone", 0, 330, 75, 40, 40, 0));
		commandCenter.setExpanded(true);
		
		anchorPane.getChildren().add(commandCenter.getValue().getStackPane());
		anchorPane.getChildren().add(drone.getValue().getStackPane());
		commandCenter.getValue().getStackPane().setTranslateX(commandCenter.getValue().getLocationX());
		commandCenter.getValue().getStackPane().setTranslateY(commandCenter.getValue().getLocationY());
		
		drone.getValue().getStackPane().setTranslateX(drone.getValue().getLocationX());
		drone.getValue().getStackPane().setTranslateY(drone.getValue().getLocationY());
		
		droneVisual.toFront();
		
		treeView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			TreeItem<FarmInterface> selectedItem = treeView.getSelectionModel().getSelectedItem();
			if (selectedItem.getValue().getName().equalsIgnoreCase("Root")) {
				renameButton.setDisable(true);
				changeLocationButton.setDisable(true);
				changePriceButton.setDisable(true);
				changeDimensionButton.setDisable(true);
				deleteButton.setDisable(true);
				goToItemButton.setDisable(false);
			}
			else if (selectedItem.getValue().getName().equalsIgnoreCase("Command Center")) {
				renameButton.setDisable(true);
				changeLocationButton.setDisable(false);
				changePriceButton.setDisable(false);
				changeDimensionButton.setDisable(false);
				deleteButton.setDisable(true);
				goToItemButton.setDisable(false);
			}
			else if (selectedItem.getValue().getName().equalsIgnoreCase("Drone")) {
				renameButton.setDisable(true);
				changeLocationButton.setDisable(false);
				changePriceButton.setDisable(false);
				changeDimensionButton.setDisable(true);
				deleteButton.setDisable(true);
				goToItemButton.setDisable(true);
			}
			else {
				renameButton.setDisable(false);
				changeLocationButton.setDisable(false);
				changePriceButton.setDisable(false);
				changeDimensionButton.setDisable(false);
				deleteButton.setDisable(false);
				goToItemButton.setDisable(false);
			}
		});
		
		// Occurs when user clicks an item on the tree view.
		EventHandler<MouseEvent> onTreeViewClick = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				
				TreeItem<FarmInterface> selectedItem = treeView.getSelectionModel().getSelectedItem();
				
				if (selectedItem != null) {
					if (selectedItem.getValue() instanceof itemContainer) {
						buttonsLabel.setText("Item Container Commands");
						addItemButton.setVisible(true);
						addItemContainerButton.setVisible(true);
						List<itemContainer> selectedItemContainerList = selectedItem.getValue().getItemContainers(); //list
						List<item> selectedItemList = selectedItem.getValue().getItems(); //list

						treeView.refresh();
					}
					else if (selectedItem.getValue() instanceof item) {
						buttonsLabel.setText("Item Commands");
						addItemButton.setVisible(false);
						addItemContainerButton.setVisible(false);
						treeView.refresh();
					}
				}
			}
		};
		
		
		treeView.setOnMouseClicked(onTreeViewClick);
		
		
		root.getChildren().add(commandCenter);
		
		commandCenter.getChildren().add(drone);
	    root.setExpanded(true);
	    treeView.setRoot(root);
	}
}
