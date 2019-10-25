package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Persons.Admin;
import iBUS.Project.Persons.Customer;
import iBUS.Project.Persons.Driver;
import iBUS.Project.Persons.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public Button face;
    public Button twt;
    public Button inst;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField username;


    private void goToCustomerPanel(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(Global.getCUSTOMERPAGE()));
        Global.GOTO(event, pane);
    }

    public void onFaceClicked() {
        face.setOnAction(e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.facebook.com/asser.ayman.1/posts/2237877662958581?notif_id=1557087116286074&notif_t=feedback_reaction_generic"));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
        );
    }

    public void onTWTClicked() {
        twt.setOnAction(e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("https://twitter.com/Asseraman"));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
        );
    }

    public void onInstClikced() {
        inst.setOnAction(e -> {
                    try {
                        Desktop.getDesktop().browse(new URI("https://www.instagram.com/asserayman1/"));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
        );
    }

    public void onPhoneClicked() {
        JOptionPane.showMessageDialog(null, "Abdelrahman :\t +20 114 179 6824 \n Asser :\t +20 128 988 8674");
    }

    public void login(ActionEvent e) throws IOException {

        Person person = Person.login(username.getText(), pass.getText());
        Global.setPerson(person);
        if (person instanceof Customer) {
            goToCustomerPanel(e);
        } else if (person instanceof Admin) {
            Parent pane = FXMLLoader.load(getClass().getResource(Global.getADMINPAGE()));
            Global.GOTO(e, pane);
        } else if (person instanceof Driver) {
            Parent pane = FXMLLoader.load(getClass().getResource(Global.getDRIVER()));
            Global.GOTO(e, pane);
        } else {
            Global.showAlert(Alert.AlertType.ERROR,"Failed !", "Username or Password invalid :(");
        }
    }

    public void goToForgotPass(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(Global.getFORGOTPASSWORD()));
        Global.GOTO(event, pane);
    }

    public void signUP(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(Global.getSIGNUP()));
        Global.GOTO(event, pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setTooltip(new Tooltip("Username"));
        pass.setTooltip(new Tooltip("Password"));
    }

}
