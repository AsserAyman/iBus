package iBUS.resources.Controllers;

import iBUS.Global;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane changable;
    public ToggleButton admin;
    public ToggleButton driver;
    public ToggleButton trip;
    public ToggleButton profile;
    public Label name;
    private ToggleGroup g = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin.setSelected(true);
        try {
            Scenes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        name.setText("Hello : " + Global.getPerson().getName());
        admin.setToggleGroup(g);
        driver.setToggleGroup(g);
        profile.setToggleGroup(g);
        trip.setToggleGroup(g);
    }
    @FXML
    private void Home(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(Global.getLOGIN()));
        Global.GOTO(event, pane);
    }

    public void Scenes() throws IOException {
        if (admin.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getADDADMIN()));
            Global.change(pane, changable);
        } else if (driver.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getADDDRIVER()));
            Global.change(pane, changable);
        } else if (trip.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getTRIPS()));
            Global.change(pane, changable);
        } else if (profile.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getPROFILE()));
            Global.change(pane, changable);
        }
    }

}
