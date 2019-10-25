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

public class CustomerController implements Initializable {
    public Label name;
    @FXML
    private ToggleButton delete;
    @FXML
    private ToggleButton edit;
    @FXML
    private BorderPane changable;
    @FXML
    private ToggleButton book;
    @FXML
    private ToggleButton history;
    @FXML
    private ToggleButton info;
    @FXML
    private ToggleButton profile;
    private ToggleGroup g = new ToggleGroup();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        book.setSelected(true);
        try {
            Scenes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        name.setText("Hello : " + Global.getPerson().getName());
        book.setToggleGroup(g);
        info.setToggleGroup(g);
        history.setToggleGroup(g);
        profile.setToggleGroup(g);
        edit.setToggleGroup(g);
        delete.setToggleGroup(g);
    }

    public void Scenes() throws IOException {
        if (book.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getBOOK()));
            Global.change(pane, changable);
        } else if (info.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getBUSINFO()));
            Global.change(pane, changable);
        } else if (history.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getHISTORY()));
            Global.change(pane, changable);
        } else if (profile.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getPROFILE()));
            Global.change(pane, changable);
        } else if (edit.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getEDIT()));
            Global.change(pane, changable);
        } else if (delete.isSelected()) {
            BorderPane pane = FXMLLoader.load(getClass().getResource(Global.getDELETE()));
            Global.change(pane, changable);
        } else {
            book.setSelected(true);
            Scenes();
        }
    }

    public void Home(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(Global.getLOGIN()));
        Global.GOTO(event, pane);
    }


}
