package com.barishal.view;

import java.util.Optional;

import org.w3c.dom.css.Rect;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;

public class Singleton {
	
	private static Singleton singleton;
    @FXML
    private Rectangle barnBox;
    @FXML
    private Text barnText;
    @FXML
    private Rectangle cowBox;
    @FXML
    private Text cowText;
    @FXML
    private Rectangle cropBox;
    @FXML
    private Text cropText;
    @FXML
    private Rectangle milkBox;
    @FXML
    private Text milkText;
    @FXML
    private Rectangle soyBox;
    @FXML
    private Text soyText;
    @FXML
    private Rectangle storageBox;
    @FXML
    private Text storageText;
    @FXML
    private Rectangle tillerBox;
    @FXML
    private Text tillerText;
    @FXML
    private Rectangle tractorBox;
    @FXML
    private Text tractorText;
    @FXML 
    private Text commandCenterText;
    @FXML
    private Rectangle commandCenterBox;
    
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
    private SubScene visual;
	@FXML
	private ImageView droneVisual;
	
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
		
		TreeItem<Farm> commandCenter = new TreeItem<Farm>(new itemContainer("Command Center", 0, 0, 0, 0, 0, 0));
		TreeItem<Farm> drone = new TreeItem<Farm>(new item("drone", 0, 0, 0, 0, 0, 0));
		commandCenter.setExpanded(true);

		
		TreeItem<Farm> barn = new TreeItem<Farm>(new itemContainer("Barn", 0, 0, 0, 0, 0, 0));
		barn.setExpanded(true);
		TreeItem<Farm> milkStorage = new TreeItem<Farm>(new item("Milk Storage", 0, 0, 0, 0, 0, 0));
		TreeItem<Farm> cow = new TreeItem<Farm>(new item("Cow", 0, 0, 0, 0, 0, 0));
		
		TreeItem<Farm> storageBuilding = new TreeItem<Farm>(new itemContainer("Storage Building", 0, 0, 0, 0, 0, 0));
		storageBuilding.setExpanded(true);
		TreeItem<Farm> tractor = new TreeItem<Farm>(new item("Tractor", 0, 0, 0, 0, 0, 0));
		TreeItem<Farm> tiller = new TreeItem<Farm>(new item("Tiller", 0, 0, 0, 0, 0, 0));
		
		root.getChildren().add(commandCenter);
		root.getChildren().add(barn);
		root.getChildren().add(storageBuilding);
		
		commandCenter.getChildren().add(drone);
		
		barn.getChildren().add(milkStorage);
		barn.getChildren().add(cow);
		
		storageBuilding.getChildren().add(tractor);
		storageBuilding.getChildren().add(tiller);
		
		// ########################################## Sample Data ##########################################
		
		
		// ########################################## Event Handlers ##########################################
		
		// Occurs when user clicks an item on the tree view.
		EventHandler<MouseEvent> onTreeViewClick = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				
				TreeItem<Farm> selectedItem = treeView.getSelectionModel().getSelectedItem();
				
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
						selectedItem.getChildren().add(new TreeItem<Farm>(new item(resultOptional.get(), 0, 0, 0, 0, 0, 0)));
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
						TreeItem<Farm> treeItemContainer = new TreeItem<Farm>(new itemContainer(resultOptional.get(), 0, 0, 0, 0, 0, 0));
						treeItemContainer.setExpanded(true);
						selectedItem.getChildren().add(treeItemContainer);
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
				}
			}
		};
		
		// ########################################## Animation Handlers ##########################################
		TranslateTransition translate = new TranslateTransition();
		RotateTransition rotate = new RotateTransition();
		
		// Occurs when the user clicks the on the scan farm button.
		EventHandler<ActionEvent> onScan = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				translate.setNode(droneVisual);
				//rotate.setNode(droneVisual);
				//rotate.setByAngle(90);
				translate.setByX(150);
				translate.setDuration(Duration.millis(1000));
				translate.play();
				//rotate.play();
			}
		};
		
		// Occurs when the user clicks the on the scan farm button.
		EventHandler<ActionEvent> onGoToHome = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				translate.setNode(droneVisual);
				translate.setByX(150);
				commandCenterBox
				translate.setDuration(Duration.millis(1000));
				translate.play();
				//rotate.play();
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
		

	    root.setExpanded(true);
	    treeView.setRoot(root);
		
	}
	
}
