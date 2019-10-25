package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Persons.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {
    private Person person;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField answer;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField rePass;
    @FXML
    private ComboBox security;
    @FXML
    private Button saveButton;
    @FXML

    private boolean validate() {
        return !username.getText().isEmpty() && !email.getText().isEmpty() && !answer.getText().isEmpty() && security.getValue() != null;
    }

    public void changePasswordClicked() {
        if (validate()) {
            person = Person.isInfoValid(username.getText(), email.getText(), security.getValue().toString(), answer.getText());
            if (person == null) {
                Global.showAlert(Alert.AlertType.ERROR,"Error","Information NOT Valid");
            } else {
                Global.showAlert(Alert.AlertType.CONFIRMATION,"Identified","Information is correct you can change your password");
                pass.setVisible(true);
                rePass.setVisible(true);
                saveButton.setVisible(true);
            }
        } else {
            Global.showAlert(Alert.AlertType.ERROR,"Error","Please fill in all Info");
        }
    }

    public void saveNewPassword(ActionEvent event) throws IOException {
        if (person != null) {
            if (pass.getText().equals(rePass.getText())) {
                person.setPassword(pass.getText());
                Global.showAlert(Alert.AlertType.CONFIRMATION,"Confirmed !","Password Changed !");
                Parent pane = FXMLLoader.load(getClass().getResource(Global.getLOGIN()));
                Global.GOTO(event, pane);
            } else {
                Global.showAlert(Alert.AlertType.ERROR,"Error","Please check that 2 passwords are equal");
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pass.setVisible(false);
        rePass.setVisible(false);
        saveButton.setVisible(false);
    }
    public void Home(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(Global.getLOGIN()));
        Global.GOTO(event, pane);
    }
}