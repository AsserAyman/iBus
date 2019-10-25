package iBUS.resources.Controllers;

import iBUS.DataBasePackage.DataBaseClass;
import iBUS.Global;
import iBUS.Project.Buses.EliteBus;
import iBUS.Project.Persons.Admin;
import iBUS.Project.Persons.Customer;
import iBUS.Project.Persons.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        DataBaseClass DB = new DataBaseClass();
        if (DB.open()) {
            Person.setPeople(DataBaseClass.getAccounts());

            for (Person p :Person.getPeople()) {
                if(p instanceof Driver){
                    DataBaseClass.initializeTrips((Driver)p);
                }
            }
        }
        DB.close();

//        Person.getPeople().add(new Admin("1234567", "ABdo", "male", 12, "abdo", "123",
//                "ayhaga", "dfthuj", "tcvb", "984132015"));
//        Person.getPeople().add(new Driver("234567", "Asser", "male",
//                12, "assor", "123", "yvb", "af", "rcvhb", "9785132", new EliteBus("ASSOR12345")));
//        Person.getPeople().add(new Customer("12", "Amr", "Others", 12, "amr", "123",
//                "cgfv", "gvh", "tgh", "978516", new CreditCard("51", "14", 15434)));


        Parent root = FXMLLoader.load(getClass().getResource(Global.getLOGIN()));
//        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        primaryStage.setTitle("Bus Reservation Sys.");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.show();
    }
}
