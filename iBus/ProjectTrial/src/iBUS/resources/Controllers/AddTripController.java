package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Persons.Driver;
import iBUS.Project.Persons.Person;
import iBUS.Project.Trips.Trip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTripController implements Initializable {
    @FXML
    private ComboBox status;
    @FXML
    private ListView<Trip> table;
    @FXML
    private TextField destination;
    @FXML
    private TextField pickupLocation;
    @FXML
    private TextField departureTime;
    @FXML
    private TextField arrivalTime;
    @FXML
    private TextField cost;
    @FXML
    private ComboBox driver;


    private boolean validate() {
        return !destination.getText().isEmpty() && !pickupLocation.getText().isEmpty() && !departureTime.getText().isEmpty() && !arrivalTime.getText().isEmpty() && !cost.getText().isEmpty()
                && driver.getValue() != null;
    }

    public void addButtonClicked() {
        if (validate()) {
            Driver d = getDriver(driver.getValue().toString());
            if(d.isNewTripValid(Integer.valueOf(departureTime.getText()),Integer.valueOf(arrivalTime.getText()))){
                if(d.isNewPickupLocationValid(Integer.valueOf(departureTime.getText()),pickupLocation.getText())){
                Trip t = new Trip(Double.valueOf(cost.getText()), d, pickupLocation.getText(), destination.getText(), departureTime.getText(), arrivalTime.getText(), "In Station", d.getPersonalBus().getSeats());
                arrivalTime.clear();
                cost.clear();
                pickupLocation.clear();
                destination.clear();
                departureTime.clear();
                Global.showAlert(Alert.AlertType.CONFIRMATION,"Success !", "Trip Added Successfully !");
                }
            }else{
                Global.showAlert(Alert.AlertType.ERROR,"OverLapping !", "Failed to add this trip to this DriverController as his schedule is Full at this time ");
            }

        } else {
            Global.showAlert(Alert.AlertType.ERROR,"Failed !", "Missing Input !");
        }
    }

    private Driver getDriver(String name) {
        for (Person person : Person.getPeople()
        ) {
            if (person instanceof Driver) {
                if (person.getName().equals(name))
                    return (Driver) person;
            }
        }
        return null;
    }

    public void ShowTrips() {
        table.getItems().setAll(Trip.getTrips());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void setTripStatus() {
        if (Trip.getTrips().get(table.getSelectionModel().getSelectedIndex()).isEditableByAdmin()) {
            Trip.getTrips().get(table.getSelectionModel().getSelectedIndex()).setTripStatus(status.getValue().toString());
            ShowTrips();
            Global.showAlert(Alert.AlertType.CONFIRMATION,"Success !","Trip has been edited");
        } else {
            Global.showAlert(Alert.AlertType.ERROR,"Cannot Add Trip ", "Sorry, you cannot edit an arrived trip");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        driver.getItems().clear();
        System.out.println(driver.getItems());
        for (Person p : Person.getPeople()
        ) {
            if (p instanceof Driver) {
                driver.getItems().add(p.getName());
            }
        }
        Global.makeOnlyDigits(cost);
        Global.addTextLimiterOnlyDigits(departureTime,2);
        Global.addTextLimiterOnlyDigits(arrivalTime,2);

    }
}
