package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Persons.Customer;
import iBUS.Project.Trips.Reservation;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {
    public ListView<Reservation> table;
    Customer c = (Customer) Global.getPerson();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getItems().setAll(c.getMyReservations());
    }

    public void onDeleteClicked() {
        c.deleteReservation(c.getMyReservations().get(table.getSelectionModel().getSelectedIndex()));
    }
}
