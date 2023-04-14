package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Announcement.Announcement;
import model.ApplicationSystem.ApplicationSystem;
import javafx.scene.control.cell.PropertyValueFactory;
public class AnnouncementController implements Initializable {
    Parent root;
    Stage stage;
    private ApplicationSystem app;
    @FXML
    private Button submitBtn;
    @FXML
    private TextField titletext;
    @FXML
    private TextArea descriptiontextarea;
    @FXML
    private TableView announcementtable;
    @FXML 
    private TableColumn<Announcement, String> Title;
	@FXML 
	private TableColumn<Announcement, String> Description;
	@FXML
	private Button DeleteBtn;
    Alert a = new Alert(AlertType.NONE);
	 @Override
	    public void initialize(URL url, ResourceBundle rb) {
		 app = ApplicationSystem.getInstance();
		 Title.setCellValueFactory(new PropertyValueFactory<Announcement, String>("title"));
		 Description.setCellValueFactory(new PropertyValueFactory<Announcement, String>("description"));
		System.out.println(app.getAnnouncementslist().size());
		 announcementtable.setItems(app.getAnnouncementslist());
		 
	    }
	    @FXML
	    private void submitBtnClicked(ActionEvent event) throws IOException
	    {
////	        Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./resources/AddAnnouncement.fxml"));
//	        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./resources/AddAnnouncement.fxml"));
//	        root = loader.load();
//	        //((MainFXMLController)loader.getController()).setModel(this.app);
//	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//	        Scene scene = new Scene(root, 1000, 1000);
//	        stage.setScene(scene);
//	        stage.show();
//	        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./resources/MainFXML.fxml"));
//	        root = loader.load();
//	        //((MainFXMLController)loader.getController()).setModel(this.app);
//	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//	        Scene scene = new Scene(root, 1000, 1000);
//	        stage.setScene(scene);
//	        stage.show();
	    	if(titletext.getText().isEmpty() ||titletext.getText() == null || descriptiontextarea.getText().isEmpty() ||descriptiontextarea.getText() == null )
	    	{
	     	   a.setAlertType(AlertType.WARNING);
	    	   a.setContentText("Title cannot be Empty");
	    	   a.show();
	    	}
	    	else if(descriptiontextarea.getText().isEmpty() ||descriptiontextarea.getText() == null )
	    	{
	     	   a.setAlertType(AlertType.WARNING);
	    	   a.setContentText("Announcement Description cannot be empty");
	    	   a.show();
	    	}
	    	String title = titletext.getText();
	    	String descritpion = descriptiontextarea.getText();
	    	Announcement announcement = new Announcement(title,descritpion);
	    	app.addAnnouncement(announcement);
	    	announcementtable.setItems(app.getAnnouncementslist());
	    	titletext.clear();
	    	descriptiontextarea.clear();
//	    	app.write(app.getAnnouncementslist());
	    	

	    }
	    @FXML
	    private void DeleteBtnClicked(ActionEvent event) throws IOException
	    {
	    	announcementtable.getItems().removeAll(announcementtable.getSelectionModel());
	    	int index = announcementtable.getSelectionModel().getFocusedIndex();
	    	app.getAnnouncementslist().remove(0);
	     	   a.setAlertType(AlertType.INFORMATION);
	    	   a.setContentText("The announcement has been successfully deleted");
	    	   a.show();
//	    	   app.getAnnouncementslist().remove(index+1);
	    	   announcementtable.setItems(app.getAnnouncementslist());   
	    }
	    @FXML
	    private void viewButtonClicked(ActionEvent event)
	    {
	    	Announcement announcementitem = (Announcement) announcementtable.getSelectionModel().getSelectedItem();
	     	   a.setAlertType(AlertType.INFORMATION);
	    	   a.setContentText(announcementitem.getDescription());
	    	   a.show();
	    	
	    }
}
