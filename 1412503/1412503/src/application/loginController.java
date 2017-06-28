package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.QueryGetData;


public class loginController implements Initializable {
	 @FXML
	 private Button btnLogin;
	 @FXML
	 private Button btnCancel;
	 @FXML
	 private TextField textUsername;
	 @FXML
	 private PasswordField textPassword;
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void showLogin(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Login");
		try {
			if(QueryGetData.checkUserName(textUsername.getText()))
				alert.setHeaderText("Incorrect username or password!!!");
			else {
				if(QueryGetData.getData(textUsername.getText(), textPassword.getText())){
					alert.setHeaderText("Login successful!!!");
				}
				else
					alert.setHeaderText("Incorrect username or password!!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		alert.showAndWait();
		textPassword.setText("");
	   }
	public void showCancel(ActionEvent event) {
		 Stage stage = (Stage) btnCancel.getScene().getWindow();
		 QueryGetData.closeConn();
		 stage.close();
	   }
}
