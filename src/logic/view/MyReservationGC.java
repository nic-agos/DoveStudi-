package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.AccountBean;
import logic.bean.ReservationBean;
import logic.controller.ReservationController;
import logic.exception.DatabaseException;
import logic.exception.ReservationException;
import logic.model.Person;
import logic.model.Reservation;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;
import logic.util.gmaps.GoogleMapsWindow;

/*Linked FXML file: MyReservations.fxml*/

public class MyReservationGC implements Initializable{
	@FXML
	private BorderPane main;
	@FXML
	private ListView<Reservation> past;
	@FXML
	private ListView<Reservation> future;
	
	private ObservableList<Reservation> allFuture;
	private ObservableList<Reservation> allPast;
	
	private static final String ERROR = "Error";
	
	//Initialize the ListViews with the reservations
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setAllPast();
		setAllFuture();
		//This view has got two different list for past and future reservation
		if(!allPast.isEmpty()) {
			past.setItems(allPast);
			past.setCellFactory(list -> new PastResCell());
		}	
		
		if(!allFuture.isEmpty()) {
			future.setItems(allFuture);
			future.setCellFactory(list -> new FutureResCell());
		}
	}
	/*Cell style of the past ListView*/
	class PastResCell extends ListCell<Reservation>{
		@Override
		public void updateItem(Reservation item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				VBox v = new VBox();
				Label title = new Label("Room Name: "+item.getLinkedRoom().getName());
				Label host = new Label("Host: ");
				Label description = new Label(item.getLinkedRoom().getSpecification().getDescription());
				Label address = new Label ("Address: " + item.getLinkedRoom().getAddress());
				Label cap = new Label("CAP: " + item.getLinkedRoom().getSpecification().getCap());
				Label date = new Label("Date: " + item.getDate());
				Label start = new Label ("Start Time: " + item.getStartTime());
				Label end = new Label ("End Time: " + item.getEndTime());
				
				description.setWrapText(true); //this method make the label go automatically new line 
				
				ObservableList<Hyperlink> linkList = FXCollections.observableArrayList(); 
				// get all the partecipant and put them in a list as links
				for (Person p  : item.getLinkedRoom().getParticipants()) {
					Hyperlink link = new Hyperlink();
					link.setText(p.getUsername());
					link.setOnAction(e->{
						Stage stage = (Stage) main.getScene().getWindow();
						stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(link.getText())));
					});
					linkList.add(link);
				}
				// Fill the partecipants list
				ListView<Hyperlink> partecipants = new ListView<>();
				partecipants.setItems(linkList);
				partecipants.setOrientation(Orientation.HORIZONTAL);
				partecipants.setPrefHeight(30);
				partecipants.setMaxHeight(USE_PREF_SIZE);
				//Link to the host of the room
				Hyperlink hostLink = new Hyperlink(item.getRoomOwner().getUsername());
				
				hostLink.setOnAction(e ->{
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(hostLink.getText())));
				});
				
				v.getChildren().addAll(title,host,hostLink,description,address,cap,date,start,end,partecipants);			
				v.setAlignment(Pos.CENTER);
				v.setSpacing(5);
				v.setMaxWidth(480);
				setGraphic(v);
			}
		}
	}
	/*Cell style of the future ListView*/
	class FutureResCell extends ListCell<Reservation>{
		@Override
		public void updateItem(Reservation item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				VBox v = new VBox();
				Label title = new Label("Room Name: "+item.getLinkedRoom().getName());
				Label host = new Label("Host: ");
				Label description = new Label(item.getLinkedRoom().getSpecification().getDescription());
				Label address = new Label ("Address: " + item.getLinkedRoom().getAddress());
				Label cap = new Label("CAP: " + item.getLinkedRoom().getSpecification().getCap());
				Label date = new Label("Date: " + item.getDate());
				Label start = new Label ("Start Time: " + item.getStartTime());
				Label end = new Label ("End Time: " + item.getEndTime());
				
				description.setWrapText(true);
				
				ObservableList<Hyperlink> linkList = FXCollections.observableArrayList();
				
				for (Person p  : item.getLinkedRoom().getParticipants()) {
					Hyperlink link = new Hyperlink();
					link.setText(p.getUsername());
					link.setOnAction(e->{
						Stage stage = (Stage) main.getScene().getWindow();
						stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(link.getText())));
					});
					linkList.add(link);
				}
				
				ListView<Hyperlink> partecipants = new ListView<>();
				partecipants.setItems(linkList);
				partecipants.setOrientation(Orientation.HORIZONTAL);
				partecipants.setPrefHeight(30);
				partecipants.setMaxHeight(USE_PREF_SIZE);
				
				Hyperlink hostLink = new Hyperlink(item.getRoomOwner().getUsername());
				
				hostLink.setOnAction(e ->{
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(hostLink.getText())));
				});
				
				Button cancel = new Button("Cancel");
				
				cancel.setOnAction(e ->{
					ReservationBean bean = new ReservationBean();
					bean.setId(item.getId());
					ReservationController ctrl = ReservationController.getInstance();
					try {
						ctrl.deleteReservation(bean);
					
					}catch (DatabaseException exc) {
						JOptionPane.showMessageDialog(null,exc.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
					}catch (ReservationException ex) {
						JOptionPane.showMessageDialog(null,ex.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
					}
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.MYRESERVATIONS,null));
					
				});
				
				Button maps = new Button ("GMaps Link");
				maps.setOnAction(e->{
					GoogleMapsWindow gWindow = new GoogleMapsWindow(item.getLinkedRoom().getAddress());
					Stage stage = new Stage();
					stage.setScene(new Scene(gWindow.createBorderPane()));
					stage.show();
				});
				
				v.getChildren().addAll(title,host,hostLink,description,address,cap,date,start,end,partecipants,cancel,maps);			
				v.setAlignment(Pos.CENTER);
				v.setSpacing(5);
				v.setMaxWidth(480);
				setGraphic(v);
			}
		}
	}
	/*This method get all the past reservation of an account and set a local observable list*/
	private void setAllPast() {
		ReservationController resCtrl = ReservationController.getInstance();
		AccountBean aBean = new AccountBean();
		aBean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
		// just current user CF packed in a bean is needed
		try {
			this.allPast = FXCollections.observableArrayList(resCtrl.getMyPastReservations(aBean));
		}
		catch(DatabaseException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	/*same as above, but for the future ones*/
	private void setAllFuture() {
		ReservationController resCtrl = ReservationController.getInstance();
		AccountBean aBean = new AccountBean();
		aBean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
		
		try{
			this.allFuture = FXCollections.observableArrayList(resCtrl.getMyFutureReservations(aBean));	
		}
		catch(DatabaseException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
}
