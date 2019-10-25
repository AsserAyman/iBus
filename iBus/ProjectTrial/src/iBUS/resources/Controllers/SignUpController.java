package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Persons.CreditCard;
import iBUS.Project.Persons.Customer;
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

public class SignUpController implements Initializable {

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
    private TextField cardNum;
    @FXML
    private TextField name;
    @FXML
    private Button signUp;
    @FXML
    private TextField secNum;
    @FXML
    private TextField balance;
    private ToggleGroup g = new ToggleGroup();

    public void Home(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(Global.getLOGIN()));
        Global.GOTO(event, pane);
    }

    public void onSignUpClicked(ActionEvent e) throws IOException {
        if (validate()) {
            String check = isInputValid();
            if (check == null) {
                String gender;
                if (male.isSelected())
                    gender = "Male";
                else
                    gender = "Female";
//                Person.getPeople().add(new Customer(ID.getText(), name.getText(), gender, Integer.valueOf(age.getText()), username.getText(), Pass.getText(), email.getText(), security.getValue().toString(), answer.getText(), phone.getText(),
//                        new CreditCard(cardNum.getText(), secNum.getText(), Integer.valueOf(balance.getText()))));
                 new Customer(ID.getText(), name.getText(), gender, Integer.valueOf(age.getText()), username.getText(), Pass.getText(), email.getText(), security.getValue().toString(), answer.getText(), phone.getText(),
                        new CreditCard(cardNum.getText(), secNum.getText(), Integer.valueOf(balance.getText())));
                Global.showAlert(Alert.AlertType.CONFIRMATION,"Success !", "Registration is completed successfully !");
                Home(e);
            } else {
                Global.showAlert(Alert.AlertType.ERROR,"Error !",check);
            }
        } else {
            Global.showAlert(Alert.AlertType.ERROR, "Empty Fields !", "Please Fill in all the fields ");
        }

    }

    private boolean validate() {
        return (!ID.getText().isEmpty() && !username.getText().isEmpty() && !Pass.getText().isEmpty() && !rePass.getText().isEmpty()
                && !email.getText().isEmpty() && !phone.getText().isEmpty() && !age.getText().isEmpty() && !cardNum.getText().isEmpty()
                && !secNum.getText().isEmpty() && !balance.getText().isEmpty() && !name.getText().isEmpty() && security.getValue() != null);

    }

    private String isInputValid() {
        if (ID.getLength() != 14) {
            return "Please Check your ID";
        } else if (!Pass.getText().equals(rePass.getText())) {
            return "Please Check your Password";
        } else if (!email.getText().endsWith("@gmail.com")) {
            return "Please Check your Email";
        } else if (cardNum.getLength() != 16) {
            return "Please Check Your Credit Card number";
        } else if (secNum.getLength() != 4) {
            return "Please Check Your Credit Card Security number";
        } else if (!male.isSelected() && !female.isSelected()) {
            return "Please Select Gender";
        } else if (phone.getLength() != 11) {
            return "please check your phone number";
        } else if (security.getSelectionModel().isEmpty()) {
            return "Please choose security question";
        } else if (!Person.isUsernameUnqiue(username.getText())) {
            return "We are sorry this username is already taken please try another one";
        } else {
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Global.addTextLimiterOnlyDigits(phone, 11);
        Global.addTextLimiterOnlyDigits(ID, 14);
        Global.addTextLimiterOnlyDigits(cardNum, 16);
        Global.addTextLimiterOnlyDigits(secNum, 4);
        Global.makeOnlyDigits(age);
        Global.makeOnlyDigits(balance);
        male.setToggleGroup(g);
        female.setToggleGroup(g);
    }

}


