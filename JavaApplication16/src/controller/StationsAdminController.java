package controller;
import java.util.Iterator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.ApplicationSystem.ApplicationSystem;
import model.Routes.Graph;
import model.Routes.Stop;
import javafx.scene.control.ToggleGroup;

public class StationsAdminController implements Initializable {
    Parent root;
    Stage stage;
    private ApplicationSystem app;
    private Graph graph;
    @FXML
    private ComboBox StationsComboBox;
    @FXML
    private Button submitBtn;
    @FXML
    private Pane StationPane;
    @FXML
    private Text stationNameText;
    @FXML
    private Text stationNameField;
    @FXML
    private RadioButton yesradiobutton;
    @FXML
    private RadioButton noradiobutton;
    @FXML
    private GridPane Buslistgrid;
    LinkedHashMap<String, Stop> stopLookup;
    String origin;
    Alert a = new Alert(AlertType.NONE);
    final ToggleGroup group = new ToggleGroup();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
        this.app = ApplicationSystem.getInstance();
       graph = app.getGraph();
        Set<String> keySet = graph.getStoplookup().keySet();
        stopLookup = graph.getStoplookup();
        List<String> arr = new ArrayList<>(keySet);
        arr.replaceAll(String::toUpperCase);
        ObservableList ObList = FXCollections.observableList(arr);
        StationsComboBox.setItems(ObList);
        StationPane.setVisible(false);
        yesradiobutton.setToggleGroup(group);
        yesradiobutton.setUserData(true);
        noradiobutton.setUserData(false); // Set Valuyeus for radio button 
        noradiobutton.setToggleGroup(group);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
        	public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
              if (group.getSelectedToggle().getUserData() != null) {
        		  Stop stop = stopLookup.get(origin.toLowerCase());
            	  if(group.getSelectedToggle().getUserData().toString()=="false")
            	  {

            		  stop.setActive(false); 
            	  }
            	  else
            	  {
            		  stop.setActive(true);
            	  }
                System.out.println(group.getSelectedToggle().getUserData().toString());
                
              }
             
            }
          });
	}
	@FXML
    private void submitBtnClicked(ActionEvent event) throws IOException {
		  origin = (String) StationsComboBox.getValue();
	        if (origin == null || origin.isBlank() || origin.isEmpty()) {
	            a.setAlertType(AlertType.WARNING);
	            a.setContentText("Please choose a station");
	            a.show();
	        }
	        else
	        { Buslistgrid.getChildren().clear();
	        	StationPane.setVisible(true);
//	        	Buslistgrid.setGridLinesVisible(true);
//	        	StationPane.setStyle("-fx-background-image:url('stations_adminpage.jpg');");
	        	
	        	stationNameField.setText(origin);
	        	Stop stop = stopLookup.get(origin.toLowerCase());
	        	if(stop.isActive())
	        	{
	        		yesradiobutton.setSelected(true);
	        	}
	        	else
	        	{
	        		noradiobutton.setSelected(true);
	        	}
	    		HashMap<String, String> map =graph.getStationInfo(stop);
	    		Text Heading1 = new Text("Bus\\Train Name");
	    		Heading1.setTextAlignment(TextAlignment.CENTER);
	    		Buslistgrid.setPadding(new Insets(10, 10, 10, 10));
	    		Heading1.setFont(Font.font("Verdana",FontWeight.BOLD,16));
	    		Buslistgrid.add(Heading1, 0, 0);
	    		Buslistgrid.setHalignment(Heading1, HPos.CENTER);
	       		Text Heading2 = new Text("Adjacent Stops");
	       		Heading2.setFont(Font.font("Verdana",FontWeight.BOLD,16));
	       		Heading2.setTextAlignment(TextAlignment.CENTER);
	       		Buslistgrid.setHalignment(Heading2, HPos.CENTER);
	     		Buslistgrid.add(Heading2, 1, 0);
	     		Set entrySet = map.entrySet();
	     		Iterator it = entrySet.iterator();
	     		int i =1;
//	     		Buslistgrid.setGridLinesVisible(true);
	     		Buslistgrid.setAlignment(Pos.TOP_CENTER);
	     		while(it.hasNext())
	     		{
	     	       Map.Entry enteries = (Map.Entry)it.next();
	     	       Text busname  = new Text(enteries.getKey().toString());
	     	      busname.setFill(Color.BLACK);
	     	     busname.setFont(Font.font("Verdana",FontWeight.BOLD,12));
	     	    busname.setTextAlignment(TextAlignment.CENTER);
	     	   Buslistgrid.setHalignment(busname, HPos.CENTER);
		    		Buslistgrid.add(busname, 0, i);
		    		Text station  = new Text(enteries.getValue().toString());
		    		station.setFill(Color.BLACK);
		    		station.setFont(Font.font("Verdana",FontWeight.BOLD,12));
		    		station.setTextAlignment(TextAlignment.CENTER);		    		
		     		Buslistgrid.add(station, 1, i);
		     		Buslistgrid.setHalignment(station, HPos.CENTER);
		     			i++;
		     			
//	     	       enteries.getKey();
//	     	       enteries.getValue());
	     	   }
	        }
	}
	
	
}
