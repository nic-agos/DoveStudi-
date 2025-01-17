package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.bean.AccountBean;
import logic.bean.PersonBean;
import logic.controller.RegistrationController;
import logic.exception.DatabaseException;
import logic.exception.AccountException;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

/*Linked FXML file: Registration.fxml */
public class RegistrationGC implements Initializable{
	
	@FXML
	private Button regBtn;
	@FXML
	private Button backBtn;
	@FXML 
	private BorderPane main;
	@FXML
	private TextField nameLbl;
	@FXML
	private TextField surnameLbl;
	@FXML
	private TextField cfLbl;
	@FXML
	private DatePicker birthLbl;
	@FXML
	private ChoiceBox<String> gradeBox;
	@FXML
	private TextField schoolLbl;
	@FXML
	private TextField unameLbl;
	@FXML
	private TextField emailLbl;
	@FXML
	private PasswordField pswLbl;
	
	private static final String ERROR = "ERROR";
	
	@Override //Initialize the school grade choiceBox
	public void initialize(URL location, ResourceBundle resources) {
		gradeBox.getItems().addAll("Elementary","MiddleSchool","HighSchool","University","PhD");
	}
	
	@FXML /*Action associated to the regBtn*/
	public void register() {
		PersonBean pBean = new PersonBean();
		AccountBean aBean = new AccountBean();
		boolean value;
		// Populate the beans with the values in the fields
		pBean.setUsername(unameLbl.getText());
		pBean.setStudyGrade(gradeBox.getSelectionModel().getSelectedItem());
		pBean.setSchool(schoolLbl.getText());
		
		aBean.setCf(cfLbl.getText());
		aBean.setName(nameLbl.getText());
		aBean.setSurname(surnameLbl.getText());
		aBean.setEmail(emailLbl.getText());
		aBean.setPassword(pswLbl.getText());
		aBean.setDateBirth(String.valueOf(birthLbl.getValue()));		
		
		try {
		// Validation of beans' data
			pBean.validate();
			aBean.validate();
			RegistrationController regCtrl = RegistrationController.getInstance();
		// Registration and get the return value
			value = regCtrl.register(aBean, pBean);
		// If all ok go to login	
			if(value) {
				Stage stage = (Stage) main.getScene().getWindow();
				stage.setScene(ViewSwitcher.switchTo(Views.LOGIN,null));
			}
		}
		catch (AccountException e){
			JOptionPane.showMessageDialog(null,e.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
		catch (DatabaseException ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@FXML /*Action associated to the backBtn*/
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
}
