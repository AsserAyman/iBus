package iBUS.Project.Persons;

import iBUS.Global;
import iBUS.Project.Buses.Bus;
import iBUS.Project.Trips.Trip;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Driver extends Person {
    private static int counter = 0;
    private Bus personalBus;
    private ArrayList<Trip> myTrips; //Check

    public Driver(String nationalID, String name, String gender, int age, String username, String password, String email, String sequrityQuestion, String sequrityAnswer, String phone, Bus personalBus) {
        super(nationalID, name, gender, age, username, password, email, sequrityQuestion, sequrityAnswer, phone);
        this.personalBus = personalBus;
        myTrips = new ArrayList<Trip>();
        counter++;
        Person.getPeople().add(this);
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Driver.counter = counter;
    }

    public Bus getPersonalBus() {
        return personalBus;
    }

    public void setPersonalBus(Bus personalBus) {
        this.personalBus = personalBus;
    }

    public ArrayList<Trip> getMyTrips() {
        return myTrips;
    }
    public void setMyTrips(ArrayList<Trip> myTrips) {
        this.myTrips = myTrips;
    }

    public boolean isNewPickupLocationValid(int departure , String pickup){
        for (Trip t:myTrips
             ) {
            if(Integer.valueOf(t.getArrivalTime())==departure){
                if(pickup.equals(t.getDestination())){
                    return true;
                }else{
                    Global.showAlert(Alert.AlertType.ERROR,"Invalid trip ", "Can't get this trip from this pickup location DriverController will be in another city");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isNewTripValid(int departure,int arrival){
        for (Trip t:myTrips
             ) {
            if(departure > Integer.valueOf(t.getDepartureTime()) &&  departure < Integer.valueOf(t.getArrivalTime())
            || departure == Integer.valueOf(t.getDepartureTime()) || arrival == Integer.valueOf(t.getArrivalTime())
            || arrival > Integer.valueOf(t.getDepartureTime()) &&  arrival < Integer.valueOf(t.getArrivalTime())){
                return  false;
            }
        }
        return true;
    }

}
