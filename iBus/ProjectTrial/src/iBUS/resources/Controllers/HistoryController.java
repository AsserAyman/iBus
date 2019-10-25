package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Persons.Customer;
import iBUS.Project.Trips.Reservation;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    public ListView<Reservation> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customer c = (Customer) Global.getPerson();
        table.getItems().setAll(c.getMyReservations());
    }
}
