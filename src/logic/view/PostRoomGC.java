package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class PostRoomGC implements Initializable{
	
	@FXML
	Button postBtn;
	@FXML
	Button backBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
	}
	
	@FXML
	public void postRoom() {
		ViewSwitcher.switchTo(Views.MYROOMS, null);
	}
	
	@FXML
	public void back() {
		ViewSwitcher.back();
	}
}
