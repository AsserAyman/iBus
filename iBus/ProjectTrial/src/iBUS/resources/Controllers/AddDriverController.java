package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Buses.Bus;
import iBUS.Project.Buses.ClassicBus;
import iBUS.Project.Buses.EliteBus;
import iBUS.Project.Persons.Driver;
import iBUS.Project.Persons.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddDriverController implements Initializable {
    @FXML
    private TextField ID;
    @FXML
    private TextField username;
    @FXML
    private PasswordField Pass;
    @FXML
    private TextField rePass;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField age;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton male;
    @FXML
    private TextField answer;
    @FXML
    private ComboBox security;
    @FXML
    private TextField name;
    @FXML
    private TextField plateNum;
    @FXML
    private RadioButton elite;
    @FXML
    private RadioButton classic;
    ToggleGroup gender = new ToggleGroup();
    ToggleGroup type = new ToggleGroup();
    private boolean validate() {
        return (!ID.getText().isEmpty() && !username.getText().isEmpty() && !Pass.getText().isEmpty() && !rePass.getText().isEmpty()
                && !email.getText().isEmpty() && !phone.getText().isEmpty() && !age.getText().isEmpty()
                && !name.getText().isEmpty() && security.getValue() != null && !answer.getText().isEmpty() && !plateNum.getText().isEmpty());

    }

    private String isInputValid() {
        if (ID.getLength() != 14) {
            return "Please Check your ID";
        } else if (!Pass.getText().equals(rePass.getText())) {
            return "Please Check your Password";
        } else if (!email.getText().endsWith("@gmail.com")) {
            return "Please Check your Email";
        } else if (!male.isSelected() && !female.isSelected()) {
            return "Please Select Gender";
        } else if (!elite.isSelected() && !classic.isSelected()) {
            return "Please Select Bus Type";
        } else if (phone.getLength() != 11) {
            return "please check your phone number";
        } else if (security.getSelectionModel().isEmpty()) {
            return "Please choose security question";
        } else if (!Person.isUsernameUnqiue(username.getText())) {
            return "We are sorry this username is already taken please try another one";
        } else if (plateNum.getText().length() != 10) {
            return "Plate number must consist of 10 characters";
        } else {
            return null;
        }
    }

    public void isAddClicked() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        if (validate()) {
            String check = isInputValid();
            if (check == null) {
                String gender;
                if (male.isSelected())
                    gender = "Male";
                else
                    gender = "Female";
                Bus bus;
                if (elite.isSelected())
                    bus = new EliteBus(plateNum.getText());
                else
                    bus = new ClassicBus(plateNum.getText());

                new Driver(ID.getText(), name.getText(), gender, Integer.valueOf(age.getText()), username.getText(), Pass.getText(), email.getText(), security.getValue().toString(), phone.getText(), answer.getText(), bus);
                Global.showAlert(Alert.AlertType.CONFIRMATION,"Success !","Registration is completed successfully !");
            } else {
                Global.showAlert(Alert.AlertType.ERROR,"Error", check);
            }
        } else {
            a.setContentText("Please fill in all the fields");
            a.setTitle("Empty Fields");
            a.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Global.makeOnlyDigits(age);
        Global.addTextLimiterOnlyDigits(phone, 11);
        Global.addTextLimiterOnlyDigits(ID, 14);
        male.setToggleGroup(gender);
        female.setToggleGroup(gender);
        classic.setToggleGroup(type);
        elite.setToggleGroup(type);
    }
}
