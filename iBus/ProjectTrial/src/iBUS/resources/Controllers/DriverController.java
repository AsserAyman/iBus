package iBUS.resources.Controllers;

import iBUS.Global;
import javafx.event.ActionEvent;
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

public class DriverController implements Initializable {
    public Label name;

    public ToggleButton trips;
    public ToggleButton profile;
    public BorderPane changable;
    private ToggleGroup g = new ToggleGroup();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText("Hello : " + Global.getPerson().getName());

        trips.setSelected(true);
        try {
            Scenes(new ActionEvent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        trips.setToggleGroup(g);
        profile.setToggleGroup(g);
    }


    public void Home(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(Global.getLOGIN()));
        Global.GOTO(event, pane);
    }

    public void Scenes(ActionEvent e) throws IOException {
        if (trips.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getDRIVERTRIPS()));
            Global.change(pane, changable);

        } else if (profile.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getPROFILE()));
            Global.change(pane, changable);
        }
    }

}
