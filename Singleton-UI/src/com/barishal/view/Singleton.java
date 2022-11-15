package com.barishal.view;
import java.awt.TextArea;
import java.util.List;
import java.util.Optional;


import javafx.animation.TranslateTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.util.Pair;

public class Singleton {
	
	private static Singleton singleton;
	farmVisitor v = new farmVisitor();
	double purchasePrice;
	double currentMarketValue = 0;
	
	@FXML
	AnchorPane anchorPane;
	@FXML
	private TreeView<Farm> treeView;
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
	private Button launchSimulatorButton;
	@FXML
	private Button launchDroneButton;
    @FXML
    private SubScene visual;
	@FXML
	private ImageView droneVisual;
	@FXML
	private Label purchaseLabel;
	@FXML
	private Label currentMarketLabel;
	
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		
		if (singleton == null) { 
			singleton = new Singleton();
		}
		return singleton;
	}

	// Called after the Constructor, cannot interact with @FXML objects inside Constructor.
	@FXML
	public void initialize() {
		
		// ############# ############################# Sample Data ##########################################
		
		TreeItem<Farm> root = new TreeItem<Farm>(new itemContainer("Root", 0, 0, 0, 0, 0, 0));
		
		TreeItem<Farm> commandCenter = new TreeItem<Farm>(new itemContainer("Command Center", 0, 300, 50, 100, 100, 0));
		
		TreeItem<Farm> drone = new TreeItem<Farm>(new item("Drone", 0, 330, 75, 40, 40, 0));
		
		commandCenter.setExpanded(true);
		
		root.getChildren().add(commandCenter);
		
		commandCenter.getChildren().add(drone);
		
		anchorPane.getChildren().add(commandCenter.getValue().getStackPane());
		anchorPane.getChildren().add(drone.getValue().getStackPane());
		commandCenter.getValue().getStackPane().setTranslateX(commandCenter.getValue().getLocationX());
		commandCenter.getValue().getStackPane().setTranslateY(commandCenter.getValue().getLocationY());
		
		drone.getValue().getStackPane().setTranslateX(drone.getValue().getLocationX());
		drone.getValue().getStackPane().setTranslateY(drone.getValue().getLocationY());
		
		droneVisual.toFront();
		
		treeView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
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
		
		
		
		// ########################################## Sample Data ##########################################

		
		// ########################################## Event Handlers ##########################################
		
		// Occurs when user clicks an item on the tree view.
		EventHandler<MouseEvent> onTreeViewClick = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
				double currentMarketValue;
				double purchasePrice;
				
				if (selectedItem != null) {
					if (selectedItem.getValue() instanceof itemContainer) {
						buttonsLabel.setText("Item Container Commands");
						addItemButton.setVisible(true);
						addItemContainerButton.setVisible(true);
						
						treeView.refresh();
					}
					else if (selectedItem.getValue() instanceof item) {
						buttonsLabel.setText("Item Commands");
						addItemButton.setVisible(false);
						addItemContainerButton.setVisible(false);
						treeView.refresh();
					}
				}
				purchasePrice = selectedItem.getValue().accept(v).get(0);

				currentMarketValue =  selectedItem.getValue().accept(v).get(1);
				purchaseLabel.setText("Purchase Price: " + purchasePrice);
				currentMarketLabel.setText("Current Market Value: " + currentMarketValue);
			}
		};
		
		// Occurs when user clicks the rename button.
		EventHandler<ActionEvent> onRename = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
				
				// Check to see if the user has selected an item on the tree view.
				if (selectedItem != null) {
					TextInputDialog tDialog = new TextInputDialog(selectedItem.getValue().toString());
					tDialog.setTitle("Rename");
					tDialog.setHeaderText("Enter the New Name:");
					tDialog.setContentText("Name:");
					Optional<String> resultOptional = tDialog.showAndWait();
					if (resultOptional.isPresent()) {
						treeView.getSelectionModel().getSelectedItem().getValue().changeName(resultOptional.get());
						treeView.refresh();
						
						selectedItem.getValue().getStackPane().getChildren().remove(selectedItem.getValue().getRectangle());
						selectedItem.getValue().getStackPane().getChildren().remove(selectedItem.getValue().getLabel());
						
						anchorPane.getChildren().remove(selectedItem.getValue().getStackPane());
						
						StackPane stackPane = selectedItem.getValue().getStackPane();

						stackPane.getChildren().add(selectedItem.getValue().getRectangle());
						stackPane.getChildren().add(selectedItem.getValue().getLabel());
						
									
						anchorPane.getChildren().add(stackPane);
				
					}
				}
			}
		};
		
		// Occurs when user clicks the change location button.
		// I modeled the dialog view after https://code.makery.ch/blog/javafx-dialogs-official/
		EventHandler<ActionEvent> onChangeLocation = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
				
				if (selectedItem != null) {
					Dialog<Pair<String, String>> tDialog = new Dialog<>();
					tDialog.setTitle("Change Location");
					tDialog.setHeaderText("Enter the New Location:");
					
					ButtonType saveButtonType = new ButtonType("OK", ButtonData.OK_DONE);
					tDialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
					
					GridPane gridPane = new GridPane();
					gridPane.setHgap(10);
					gridPane.setVgap(10);
					gridPane.setPadding(new Insets(20, 75, 10, 10));
					
					TextField locationX = new TextField();
					locationX.setText(selectedItem.getValue().getLocationX().toString());
					TextField locationY = new TextField();
					locationY.setText(selectedItem.getValue().getLocationY().toString());
					
					gridPane.add(new Label("X-Location:"), 0, 0);
					gridPane.add(locationX, 1, 0);
					gridPane.add(new Label("Y-Location:"), 0, 1);
					gridPane.add(locationY, 1, 1);
					
					tDialog.getDialogPane().setContent(gridPane);
					
					tDialog.setResultConverter(dialogButton ->  {
						if (dialogButton == saveButtonType) {
							return new Pair<String, String>(locationX.getText(), locationY.getText());
						}
						return null;
					});
					
					Optional<Pair<String, String>> resultOptional = tDialog.showAndWait();
					if (resultOptional.isPresent()) {
						selectedItem.getValue().changeLocationX(Double.parseDouble(resultOptional.get().getKey()));
						selectedItem.getValue().changeLocationY(Double.parseDouble(resultOptional.get().getValue()));
						
						// Force Update the Anchor View By Removing and Adding Back Elements
//						
//						selectedItem.getValue().getStackPane().getChildren().remove(selectedItem.getValue().getRectangle());
//						selectedItem.getValue().getStackPane().getChildren().remove(selectedItem.getValue().getLabel());
//						
//						anchorPane.getChildren().remove(selectedItem.getValue().getStackPane());
//						
						StackPane stackPane = selectedItem.getValue().getStackPane();
//						
//						selectedItem.getValue().setRectangle(new Rectangle(100, 100, 100, 100));
//
//						stackPane.getChildren().add(selectedItem.getValue().getRectangle());
//						stackPane.getChildren().add(selectedItem.getValue().getLabel());
//						
//									
//						anchorPane.getChildren().add(stackPane);
						
//						if (selectedItem == drone )
//						{
//							stackPane.setTranslateX(Double.parseDouble(resultOptional.get().getKey()));
//							stackPane.setTranslateY(Double.parseDouble(resultOptional.get().getValue()));						
//						}
//						else
//						{
//							stackPane.setTranslateX(Double.parseDouble(resultOptional.get().getKey()));
//							stackPane.setTranslateY(Double.parseDouble(resultOptional.get().getValue()));
//						}
						
						stackPane.setTranslateX(Double.parseDouble(resultOptional.get().getKey()));
						stackPane.setTranslateY(Double.parseDouble(resultOptional.get().getValue()));
					}
				}		
			}
		};
		
		// Occurs when user clicks change price button.
		EventHandler<ActionEvent> onChangePrice = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
				
				if (selectedItem != null) {
					TextInputDialog tDialog = new TextInputDialog(selectedItem.getValue().getPrice().toString());
					tDialog.setTitle("Change Price");
					tDialog.setHeaderText("Enter the New Price:");
					tDialog.setContentText("Price:");
					
					Optional<String> resultOptional = tDialog.showAndWait();
					if (resultOptional.isPresent()) {
						selectedItem.getValue().changePrice(Double.parseDouble(resultOptional.get()));
						treeView.refresh();
					}
				}
				
				if(selectedItem.getChildren() == null){
					purchasePrice = selectedItem.getValue().accept(v).get(0);
					currentMarketValue =  selectedItem.getValue().accept(v).get(1);
					purchaseLabel.setText("Purchase Price: " + purchasePrice);
					currentMarketLabel.setText("Current Market Value: " + currentMarketValue);
				}else{
					purchasePrice = selectedItem.getValue().accept(v).get(0);
					currentMarketValue =  selectedItem.getValue().accept(v).get(1);
					purchaseLabel.setText("Purchase Price: " + purchasePrice);
					currentMarketLabel.setText("Current Market Value: " + currentMarketValue);
				}
			}
		};
		
		// Occurs when user clicks the change dimensions button.
		// I modeled the dialog view after https://code.makery.ch/blog/javafx-dialogs-official/
		EventHandler<ActionEvent> onChangeDimensions = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
				
				if (selectedItem != null) {
					Dialog<Pair<String, String>> tDialog = new Dialog<>();
					tDialog.setTitle("Change Dimensions");
					tDialog.setHeaderText("Enter the New Dimensions:");
					
					ButtonType saveButtonType = new ButtonType("OK", ButtonData.OK_DONE);
					tDialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
					
					GridPane gridPane = new GridPane();
					gridPane.setHgap(10);
					gridPane.setVgap(10);
					gridPane.setPadding(new Insets(20, 75, 10, 10));
					
					TextField length = new TextField();
					length.setText(treeView.getSelectionModel().getSelectedItem().getValue().getLength().toString());
					TextField width = new TextField();
					width.setText(treeView.getSelectionModel().getSelectedItem().getValue().getWidth().toString());
					
					gridPane.add(new Label("Length:"), 0, 0);
					gridPane.add(length, 1, 0);
					gridPane.add(new Label("Width"), 0, 1);
					gridPane.add(width, 1, 1);
					
					tDialog.getDialogPane().setContent(gridPane);
					
					tDialog.setResultConverter(dialogButton ->  {
						if (dialogButton == saveButtonType) {
							return new Pair<String, String>(length.getText(), width.getText());
						}
						return null;
					});
					
					Optional<Pair<String, String>> resultOptional = tDialog.showAndWait();
					if (resultOptional.isPresent()) {
						
						treeView.getSelectionModel().getSelectedItem().getValue().changeLength((Double.parseDouble(resultOptional.get().getKey())));
						treeView.getSelectionModel().getSelectedItem().getValue().changeWidth(Double.parseDouble(resultOptional.get().getValue()));
						
						// Force Update the Anchor View By Removing and Adding Back Elements
						
						selectedItem.getValue().getStackPane().getChildren().remove(selectedItem.getValue().getRectangle());
						selectedItem.getValue().getStackPane().getChildren().remove(selectedItem.getValue().getLabel());
						
						anchorPane.getChildren().remove(selectedItem.getValue().getStackPane());
						
						StackPane stackPane = selectedItem.getValue().getStackPane();

						stackPane.getChildren().add(selectedItem.getValue().getRectangle());
						stackPane.getChildren().add(selectedItem.getValue().getLabel());
						
									
						anchorPane.getChildren().add(stackPane);
					}
				}
				
			}
		};
		
		// Occurs when user clicks the add item button.
		EventHandler<ActionEvent> onAddItem = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
				
				if (selectedItem != null) {
					TextInputDialog tDialog = new TextInputDialog();
					tDialog.setTitle("Add Item");
					tDialog.setHeaderText("Enter the Name of the New Item:");
					tDialog.setContentText("Name:");
					Optional<String> resultOptional = tDialog.showAndWait();
					
					if (resultOptional.isPresent()) {
						
						item newItem = new item(resultOptional.get(), 0, 0, 0, 100, 100, 0);	
						
						newItem.getRectangle().setFill(Color.WHITE);
						newItem.getRectangle().setStroke(Color.BLACK);
						newItem.getRectangle().setStrokeWidth(1);
						
						newItem.setLabel(new Label());
						newItem.getLabel().setText(resultOptional.get());
						newItem.getLabel().setFont(Font.font("Verdana", FontWeight.BOLD, 10));
						
						newItem.setStackPane(new StackPane());
						newItem.getStackPane().getChildren().add(newItem.getRectangle());
						newItem.getStackPane().getChildren().add(newItem.getLabel());
						
						newItem.getStackPane().setAlignment(Pos.TOP_CENTER);
						
						selectedItem.getChildren().add(new TreeItem<Farm>(newItem));
						
						
						
						anchorPane.getChildren().add(newItem.getStackPane());
						droneVisual.toFront();
					}
				}
			}
		};
		
		// Occurs when user clicks the add container button.
		EventHandler<ActionEvent> onAddItemContainer = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
				
				if (selectedItem != null) {
					TextInputDialog tDialog = new TextInputDialog();
					tDialog.setTitle("Add Item Container");
					tDialog.setHeaderText("Enter the Name of the New Item Container:");
					tDialog.setContentText("Name:");
					Optional<String> resultOptional = tDialog.showAndWait();
					if (resultOptional.isPresent()) {
						
						itemContainer newItemContainer = new itemContainer(resultOptional.get(), 0, 0, 0, 200, 200, 0);
						
						newItemContainer.setLabel(new Label());
						newItemContainer.getLabel().setText(resultOptional.get());
						newItemContainer.getLabel().setFont(Font.font("Verdana", FontWeight.BOLD, 10));
						
						newItemContainer.getRectangle().setFill(Color.WHITE);
						newItemContainer.getRectangle().setStroke(Color.BLACK);
						newItemContainer.getRectangle().setStrokeWidth(1);
						
						newItemContainer.setStackPane(new StackPane());
						newItemContainer.getStackPane().getChildren().add(newItemContainer.getRectangle());
						newItemContainer.getStackPane().getChildren().add(newItemContainer.getLabel());
						
						newItemContainer.getStackPane().setAlignment(Pos.TOP_CENTER);
						
						TreeItem<Farm> treeItemContainer = new TreeItem<Farm>(newItemContainer);
						treeItemContainer.setExpanded(true);
						
						selectedItem.getChildren().add(treeItemContainer);
						
						anchorPane.getChildren().add(newItemContainer.getStackPane());
						droneVisual.toFront();
					}
				}
				}
		};
		
		// Occurs when the user clicks the on delete button.
		EventHandler<ActionEvent> onDelete = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
				
				if (selectedItem != null) {
					selectedItem.getParent().getChildren().remove(selectedItem);
					
					selectedItem.getValue().getStackPane().getChildren().remove(selectedItem.getValue().getRectangle());
					selectedItem.getValue().getStackPane().getChildren().remove(selectedItem.getValue().getLabel());
					
					anchorPane.getChildren().remove(selectedItem.getValue().getStackPane());
				}
			}
		};
		
		// ########################################## Animation Handlers ##########################################
		TranslateTransition translate = new TranslateTransition();
		TranslateTransition translate1 = new TranslateTransition();
		Path path = new Path();
		
		// Occurs when the user clicks the on the scan farm button.
		EventHandler<ActionEvent> onScan = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				translate.setNode(droneVisual);
		        translate.setToX(- droneVisual.getLayoutX());
				translate.setToY(- droneVisual.getLayoutY());
				translate.play();
				
				translate.setOnFinished(e -> {
					traverseFarm();
				});
				
			}
			
			public void traverseFarm() {
				PathTransition scantransition = new PathTransition();
				
				path.getElements().add(new MoveTo(-200, -240)); // top left
				path.getElements().add(new LineTo(-200, 350)); // bottom left
				path.getElements().add(new LineTo(-100, 350)); // bottom at x = 0
				path.getElements().add(new LineTo(-100, -240)); // top at x = 0
				path.getElements().add(new LineTo(0, -240)); //move x over 200
				path.getElements().add(new LineTo(0, 350)); // bottom at 200
				path.getElements().add(new LineTo(100, 350)); 
				path.getElements().add(new LineTo(100, -240));
				path.getElements().add(new LineTo(200, -240)); //move x over 200
				path.getElements().add(new LineTo(200, 350)); // bottom at 200
				path.getElements().add(new LineTo(300, 350)); 
				path.getElements().add(new LineTo(300, -240));
				path.getElements().add(new LineTo(400, -240)); //move x over 200
				path.getElements().add(new LineTo(400, 350)); // bottom at 200
				path.getElements().add(new LineTo(500, 350)); 
				path.getElements().add(new LineTo(500, -240));
				path.getElements().add(new LineTo((commandCenter.getValue().getLocationX() - droneVisual.getLayoutX() + 50),
						(commandCenter.getValue().getLocationY()- droneVisual.getLayoutY()) + 50 ));
				
		        scantransition.setNode(droneVisual);
		        scantransition.setDuration(Duration.seconds(9.5));
		        scantransition.setCycleCount(1);
		        scantransition.setPath(path);
		        scantransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
				
		        scantransition.play();
		        path.getElements().clear();
		        scantransition.setPath(null);
			}
		};
		
		// Occurs when the user clicks the on the Go To Home Button
		EventHandler<ActionEvent> onGoToHome = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				path.getElements().clear();
				translate1.setNode(droneVisual);
				translate1.setToX(commandCenter.getValue().getLocationX() - droneVisual.getLayoutX() + 10);
				translate1.setToY(commandCenter.getValue().getLocationY() - droneVisual.getLayoutY() + 10);
				translate1.play();
				
				path.getElements().clear();
			}
		};
		
		// Occurs when the user clicks the on the Go To Item/ Item Container
		EventHandler<ActionEvent> onGoToItem = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				translate1.setNode(droneVisual);
				TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
				translate1.setToX(selectedItem.getValue().getLocationX() - droneVisual.getLayoutX() + 10);
				translate1.setToY(selectedItem.getValue().getLocationY() - droneVisual.getLayoutY() + 10);
				translate1.play();
			}
		};
		
		
		// ########################################## Event Handlers ##########################################
		
		treeView.setOnMouseClicked(onTreeViewClick);
		renameButton.setOnAction(onRename);
		changeLocationButton.setOnAction(onChangeLocation);
		changePriceButton.setOnAction(onChangePrice);
		changeDimensionButton.setOnAction(onChangeDimensions);
		addItemButton.setOnAction(onAddItem);
		addItemContainerButton.setOnAction(onAddItemContainer);
		deleteButton.setOnAction(onDelete);
		scanFarmButton.setOnAction(onScan);
		goToHomeButton.setOnAction(onGoToHome);
		goToItemButton.setOnAction(onGoToItem);
		

	    root.setExpanded(true);
	    treeView.setRoot(root);
		
	}
	
}
