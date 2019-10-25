package iBUS.Project.Trips;

import iBUS.Global;
import iBUS.Project.Persons.Driver;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Trip {
    private static ArrayList<Trip> trips = new ArrayList<>();
    private double cost;
    private Driver driver;
    private String pickupLocation;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String tripStatus;
    private int availableSeats;


    public Trip(Trip t) {
        this.cost = t.cost;
        this.driver = t.driver;
        this.pickupLocation = t.pickupLocation;
        this.destination = t.destination;
        this.departureTime = t.departureTime;
        this.arrivalTime = t.arrivalTime;
        this.tripStatus = t.tripStatus;
        this.availableSeats = t.availableSeats;
    }

    public Trip(double cost, Driver driver, String pickupLocation, String destination, String departureTime, String arrivalTime, String tripStatus, int availableSeats) {
        this.cost = cost;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.tripStatus = tripStatus;
        this.availableSeats = availableSeats;
        trips.add(this);
        driver.getMyTrips().add(this);
    }

    public static ArrayList<Trip> getTrips() {
        return trips;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

  public   String getDepartureTime() {
        return departureTime;
    }


    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }


   public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isDeletableByAdmin() {
        return tripStatus.equals("Delayed") || tripStatus.equals("In Station");
   }

    public boolean isEditableByAdmin() {
        return !tripStatus.equals("Arrived");
    }

    public boolean isEditableByUser() {
        return tripStatus.equals("In Station");
    }

    public boolean isDeletableByUser() {
        return tripStatus.equals("In Station") || tripStatus.equals("Delayed");
    }

    public void addSeats(int seats) {
        this.availableSeats += seats;
    }

    private boolean isRemovalOfSeatsValid(int seats) {
        return availableSeats >= seats;
    }

    public boolean removeSeats(int seats) {
        if (isRemovalOfSeatsValid(seats)) {
            this.availableSeats -= seats;
            return true;
        } else {
            Global.showAlert(Alert.AlertType.ERROR,"Error !", "Number of Chosen seats exceeded number of available seats :(");
            return false;
        }

    }

    @Override
    public String toString() {
        return "From : " + pickupLocation + '\t' + "To : " + destination + "     " + "Departure Time : " + departureTime + '\t'
                + "Arrival Time : " + arrivalTime + "     " + "Cost : " + cost + " $" + '\t' + "Trip Status : " + tripStatus + '\t' + "     Number of Available Seats: " + availableSeats;
    }
}
