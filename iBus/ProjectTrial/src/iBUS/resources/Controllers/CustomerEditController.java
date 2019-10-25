package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Persons.Customer;
import iBUS.Project.Trips.Reservation;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerEditController implements Initializable {
    public TextField noSeats;
    public TextField destination;
    public ListView<Reservation> table;
    public ListView<Reservation> table1;
    Customer c = (Customer) Global.getPerson();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getItems().setAll(c.getMyReservations());
        table1.getItems().setAll(c.getMyReservations());
    }

    public void onEditClicked() {
        c.editNumberOfSeats(Integer.valueOf(noSeats.getText()), c.getMyReservations().get(table1.getSelectionModel().getSelectedIndex()).getTrip());
    }

    public void onSaveClicked() {
        if (c.editDestination(c.getMyReservations().get(table.getSelectionModel().getSelectedIndex()), destination.getText())) {

        }
    }
}
