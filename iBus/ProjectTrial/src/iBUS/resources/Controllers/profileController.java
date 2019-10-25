package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Persons.Admin;
import iBUS.Project.Persons.Customer;
import iBUS.Project.Persons.Driver;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class profileController implements Initializable {

    public TextField id;
    public TextField name;
    public TextField username;
    public TextField password;
    public TextField gender;
    public TextField email;
    public TextField phone;
    public TextField age;
    public TextField security;
    public TextField answer;
    public TextField credit;
    public TextField secNumber;
    public TextField balance;
    public Label cardLabel;
    public Label secNumberLabel;
    public Label balanceLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(Global.getPerson().getNationalID());
        name.setText(Global.getPerson().getName());
        username.setText(Global.getPerson().getUsername());
        password.setText(Global.getPerson().getPassword());
        gender.setText(Global.getPerson().getGender());
        email.setText(Global.getPerson().getEmail());
        age.setText(String.valueOf(Global.getPerson().getAge()));
        security.setText(Global.getPerson().getSequrityQuestion());
        answer.setText(Global.getPerson().getSequrityAnswer());
        phone.setText(Global.getPerson().getPhone());
        if (Global.getPerson() instanceof Admin || Global.getPerson() instanceof Driver) {
            credit.setVisible(false);
            secNumber.setVisible(false);
            balance.setVisible(false);
            cardLabel.setVisible(false);
            secNumberLabel.setVisible(false);
            balanceLabel.setVisible(false);
        } else if (Global.getPerson() instanceof Customer) {
            Customer x = (Customer) Global.getPerson();
            credit.setText(x.getCreditCard().getCardNumber());
            secNumber.setText(x.getCreditCard().getSecurityNumber());
            balance.setText(String.valueOf(x.getCreditCard().getBalance()));

        }

    }
}
