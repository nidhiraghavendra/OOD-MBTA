package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ApplicationSystem.ApplicationSystem;
import model.Commute.Ride;
import model.Routes.Graph;

public class TrainBusRoutesController implements Initializable {
//	@FXML
//	private HBox HBoxid;
	@FXML
	private AnchorPane paneid;
    @FXML
    private ComboBox<String> OriginComboBox;
    @FXML
    private ComboBox<String> DestinationComboBox;
    @FXML
    private Button submit;
    @FXML
    private Pane pane;
	ApplicationSystem app;
	Graph graph;
	 Alert a = new Alert(AlertType.NONE);
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	this.app = ApplicationSystem.getInstance();
    	graph = this.app.initalizeGraph();
    	Set<String> keySet = graph.getStoplookup().keySet(); 
    	 List<String> arr = new ArrayList<>(keySet);
    	 arr.replaceAll(String::toUpperCase);
    	ObservableList ObList = FXCollections.observableList(arr);
//    	ObservableList listOfKeys = (ObservableList) new ArrayList<String>(keySet);
    	
    	OriginComboBox.setItems(ObList);
    	DestinationComboBox.setItems(ObList);
//    	graph.search(OriginComboBox.getValue().toLowerCase(),DestinationComboBox.getValue().toLowerCase());
//    	graph.search("ruggles","green street");
    

}
	public void setModel(ApplicationSystem app2) {
		// TODO Auto-generated method stub
		
	}
    @FXML
    private void SearchButtonClicked(ActionEvent event) throws IOException {
    	String origin = OriginComboBox.getValue();
    	String Destination = DestinationComboBox.getValue();
       if(OriginComboBox.getValue()==null ||DestinationComboBox.getValue()==null)
       { 
    	   a.setAlertType(AlertType.WARNING);
    	   a.setContentText("Origin and Destination cannot be empty");
    	   a.show();
       }
       else if(origin.equalsIgnoreCase(Destination))
       {
    	   a.setAlertType(AlertType.WARNING);
    	   a.setContentText("Origin and Destination cannot be same");
    	   a.show();
       }
       else
       {
    	   HashMap<String, List<String>> routes = graph.search(OriginComboBox.getValue().toLowerCase(),DestinationComboBox.getValue().toLowerCase());
    	   HBox hbox = new HBox();
    	   pane.getChildren().clear();
    	   hbox.setPrefWidth(1000);
    	   hbox.setPrefHeight(500);
    	   hbox.setPadding(new Insets(100,100,100,100));
    	   for(String key: routes.keySet()) 
    	   {
    		   List val = routes.get(key);
    		   String busName=key;
    		   String route =busName+": "  ;
        	   route =route+ "-> "+OriginComboBox.getValue();
        	   GridPane grid = new GridPane();
        	   grid.setHgap(10);
        	    grid.setVgap(12);
        	   grid.setAlignment(Pos.CENTER);
        	   grid.setPrefSize(500, 500);
        	   Label label = new Label(busName);
        	   if(key.equalsIgnoreCase("Green E"))
        	   {
        		   label.setTextFill(Color.GREEN); 
        	   }
        	   if(key.equalsIgnoreCase("Orange Line"))
        	   {
        		   label.setTextFill(Color.ORANGE); 
        	   }
        	   Image img = new Image("file:bus-lane.png");
        	      ImageView view = new ImageView(img);
        	      view.setFitHeight(40);
        	      view.setPreserveRatio(true);
        	      label.setGraphic(view);
        	      grid.add(label, 0, 0);
        	      Text Origin = new Text(OriginComboBox.getValue().toUpperCase());
        	      Origin.setFill(Color.BLUE);
        	      Origin.setFont(Font.font ("Verdana", 15));
        	      grid.add(Origin, 1, 0);
        	      
    		   for(int i=0;i<val.size();i++)
    		   {	Circle circle = new Circle();
    		   		circle.setRadius(5);
    		   		circle.setStroke(Color.BLACK);
    		   		circle.setFill(new Color(0.5, 0.5, 0.5, 0.1));
    			   grid.add(circle, 0, i+1);
    			   Text asd = new Text();
    			   asd.setText(val.get(i).toString().toUpperCase());
            	   asd.setFill(Color.BLUE);
            	   asd.setFont(Font.font ("Verdana", 15));
    			   grid.add(asd, 1, i+1);
    			   route =  route +"-> "+val.get(i).toString().toUpperCase();
    		   }
    		   VBox vbox1 = new VBox(); 
    		   vbox1.setStyle(
                       "-fx-border-style: solid inside;" + 
                       " -fx-border-width: 0 0 0 4;" +
                       "-fx-border-insets: 0;" + 
                       "-fx-border-radius: 0;" + 
                       "-fx-border-color: blue;");
    		   vbox1.getChildren().add(grid);
    		   hbox.getChildren().add(vbox1);
    		   hbox.setLayoutY(20);
    		   System.out.println(route);

    		    }
    	   pane.getChildren().add(hbox);
    	   
    }
   }

}
