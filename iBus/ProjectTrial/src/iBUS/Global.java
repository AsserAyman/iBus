package iBUS;

import iBUS.Project.Persons.Person;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Global {
    private static final String LOGIN = "/iBUS/resources/fxml/Login.fxml";
    private static final String SIGNUP = "/iBUS/resources/fxml/SignUp.fxml";
    private static final String CUSTOMERPAGE = "/iBUS/resources/fxml/Customer HomePage.fxml";
    private static final String DRIVER = "/iBUS/resources/fxml/driverMainpanel.fxml";
    private static final String ADMINPAGE = "/iBUS/resources/fxml/AdminHome.fxml";
    private static final String FORGOTPASSWORD = "/iBUS/resources/fxml/Pass.fxml";
    private static final String ADDADMIN = "/iBUS/resources/fxml/AddAdmin.fxml";
    private static final String ADDDRIVER = "/iBUS/resources/fxml/AddDriver.fxml";
    private static final String TRIPS = "/iBUS/resources/fxml/AddTrip.fxml";
    private static final String BOOK = "/iBUS/resources/fxml/Book.fxml";
    private static final String BUSINFO = "/iBUS/resources/fxml/Bus-Info.fxml";
    private static final String HISTORY = "/iBUS/resources/fxml/History.fxml";
    private static final String PROFILE = "/iBUS/resources/fxml/Edit Profile.fxml";
    private static final String EDIT = "/iBUS/resources/fxml/CustomerEdit.fxml";
    private static final String DELETE = "/iBUS/resources/fxml/Delete.fxml";
    private static final String DRIVERTRIPS = "/iBUS/resources/fxml/DriverTrips.fxml";
    private static Person person;

    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person person) {
        Global.person = person;
    }

    public static String getDRIVERTRIPS() {
        return DRIVERTRIPS;
    }

    public static String getLOGIN() {
        return LOGIN;
    }

    public static String getSIGNUP() {
        return SIGNUP;
    }

    public static String getCUSTOMERPAGE() {
        return CUSTOMERPAGE;
    }

    public static String getDRIVER() {
        return DRIVER;
    }

    public static String getADMINPAGE() {
        return ADMINPAGE;
    }

    public static String getFORGOTPASSWORD() {
        return FORGOTPASSWORD;
    }

    public static String getADDADMIN() {
        return ADDADMIN;
    }

    public static String getADDDRIVER() {
        return ADDDRIVER;
    }

    public static String getTRIPS() {
        return TRIPS;
    }

    public static String getBOOK() {
        return BOOK;
    }

    public static String getBUSINFO() {
        return BUSINFO;
    }

    public static String getHISTORY() {
        return HISTORY;
    }

    public static String getPROFILE() {
        return PROFILE;
    }

    public static String getEDIT() {
        return EDIT;
    }

    public static String getDELETE() {
        return DELETE;
    }

    public static void change(BorderPane pane, BorderPane changable) {
        changable.setCenter(pane.getCenter());
        changable.setBottom(pane.getBottom());
        changable.setLeft(pane.getLeft());
        changable.setRight(pane.getRight());
        changable.setTop(pane.getTop());
    }

    public static void GOTO(ActionEvent event, Parent pane) {
        Scene scene = new Scene(pane);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    public static void makeOnlyDigits(final TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public static void addTextLimiterOnlyDigits(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((ov, oldValue, newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    public static void showAlert(Alert.AlertType t, String title, String message){
        Alert a = new Alert(t);
        a.setHeaderText(null);
        a.setTitle(title);
        a.setContentText(message);
        a.show();
    }

}
