package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Buses.EliteBus;
import iBUS.Project.Persons.Driver;
import iBUS.Project.Trips.Trip;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class showTrips implements Initializable {
    public ListView<Trip> table;
    public TextField Plate;
    public TextField type;
    public TextField seats;
    Driver d = (Driver) Global.getPerson();


    private void showTrips() {
        table.getItems().setAll(d.getMyTrips());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Plate.setText(d.getPersonalBus().getPlateNumber());
        if (d.getPersonalBus() instanceof EliteBus) {
            type.setText("Elite");
            seats.setText("12");
        } else {
            type.setText("Classic");
            seats.setText("24");
        }
        showTrips();

    }
}
