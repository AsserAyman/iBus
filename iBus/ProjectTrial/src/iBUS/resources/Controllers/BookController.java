package iBUS.resources.Controllers;

import iBUS.Global;
import iBUS.Project.Persons.Customer;
import iBUS.Project.Trips.Trip;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    public ListView<Trip> table;
    public TextField noSeats;
    private Dialog dialog = new Dialog<>();
    private TextField cardNum = new TextField();
    private TextField secNum = new TextField();
    private CheckBox sale = new CheckBox("Have a Promo Code ?");
    private Dialog d = new Dialog();
    private TextField promoCode = new TextField();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getItems().setAll(Trip.getTrips());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setCreditCardDialog();
        setPromo();
        Global.makeOnlyDigits(secNum);
        Global.makeOnlyDigits(cardNum);
        Global.makeOnlyDigits(noSeats);
    }

    public void OnBookClicked() {
        Customer c = (Customer) Global.getPerson();
        dialog.showAndWait();
        if (cardNum.getText().equals(c.getCreditCard().getCardNumber()) && secNum.getText().equals(c.getCreditCard().getSecurityNumber())) {
            cardNum.clear();
            secNum.clear();
            if (sale.isSelected()){
                d.showAndWait();
                if (promoCode.getText().equals("DrFaridaAgmadDrFelkoleya")){
                    Global.showAlert(Alert.AlertType.CONFIRMATION,"Valid PromoCode !","PromoCode works,\n Your Reservation is being processed !");
                    double dummyCost= Trip.getTrips().get(table.getSelectionModel().getSelectedIndex()).getCost();
                    Trip.getTrips().get(table.getSelectionModel().getSelectedIndex()).setCost(dummyCost*0.8);
                    c.book(Trip.getTrips().get(table.getSelectionModel().getSelectedIndex()), Integer.valueOf(noSeats.getText()));
                    Trip.getTrips().get(table.getSelectionModel().getSelectedIndex()).setCost(dummyCost);
                }else {
                    Global.showAlert(Alert.AlertType.ERROR,"Failed","Promo Code not valid");
                }
            }else{
                c.book(Trip.getTrips().get(table.getSelectionModel().getSelectedIndex()), Integer.valueOf(noSeats.getText()));
            }
        } else {
            cardNum.clear();
            secNum.clear();
            Global.showAlert(Alert.AlertType.ERROR,"Credit Card Info","Your Credit Card Information is incorrect !");
        }
    }

    private void setCreditCardDialog() {
        dialog.setTitle("Please Enter Credit Card Info");
        dialog.setHeaderText(null);

        ButtonType conformation = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(conformation, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
//        grid.setPadding(new Insets(20,150,10,10));

        cardNum.setPromptText("Card Number");
        secNum.setPromptText("Security Number");
        grid.add(cardNum, 0, 0);
        grid.add(secNum, 0, 1);
        grid.add(sale,1,1);
        dialog.getDialogPane().setContent(grid);
    }
    private void setPromo(){
        d.setTitle("Please Enter Credit Card Info");
        d.setHeaderText(null);

        ButtonType conformation = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().addAll(conformation, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(promoCode,0,0);
        d.getDialogPane().setContent(grid);
    }
}
