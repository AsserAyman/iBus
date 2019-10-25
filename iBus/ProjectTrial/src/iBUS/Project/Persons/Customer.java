package iBUS.Project.Persons;

import iBUS.Global;
import iBUS.Project.Trips.Reservation;
import iBUS.Project.Trips.Trip;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Customer extends Person {

    private static int counter = 0;
    private CreditCard creditCard;
    private ArrayList<Reservation> myReservations;

    public Customer(String nationalID, String name, String gender, int age, String username, String password, String email, String sequrityQuestion, String sequrityAnswer, String phone, CreditCard creditCard) {
        super(nationalID, name, gender, age, username, password, email, sequrityQuestion, sequrityAnswer, phone);
        this.creditCard = creditCard;
        myReservations = new ArrayList<>();
        counter++;
        Person.getPeople().add(this);
    }

    public ArrayList<Reservation> getMyReservations() {
        return myReservations;
    }

    public void setMyReservations(ArrayList<Reservation> myReservations) {
        this.myReservations = myReservations;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void book(Trip trip, int seats) {
        if (isNewTripValid(Integer.valueOf(trip.getDepartureTime()), Integer.valueOf(trip.getArrivalTime()))) {
            if (isNewPickupLocationValid(Integer.valueOf(trip.getDepartureTime()), trip.getPickupLocation())) {
                if (trip.removeSeats(seats)) {
                    if (creditCard.withdraw(seats * trip.getCost())) {
                        Reservation reservation = new Reservation(trip, seats, seats * trip.getCost());
                        myReservations.add(reservation);
                        Global.showAlert(Alert.AlertType.CONFIRMATION, "Confirmed !", "New Trip Successfully Reserved");
                    } else {
                        Global.showAlert(Alert.AlertType.ERROR, "Failed !", "Total price exceeded your balance :(");
                    }
                } else {
                    Global.showAlert(Alert.AlertType.ERROR, "Failed !", "Number of selected seats exceeded number of available seats");
                }
            }
        } else {
            Global.showAlert(Alert.AlertType.ERROR, "Overlapping !", "Can't reserve this trip as customer schedule is busy");
        }

    }

    public void editNumberOfSeats(int seats, Trip trip) {
        if (trip.isEditableByUser()) {
            for (Reservation r : myReservations
            ) {
                if (trip == r.getTrip()) {
                    if (seats - r.getReservedSeats() <= trip.getAvailableSeats()) {
                        if (seats - r.getReservedSeats() <= 0) {
                            creditCard.deposit((r.getReservedSeats() - seats) * trip.getCost());
                            trip.addSeats(r.getReservedSeats() - seats);
                            r.setReservedSeats(seats);
                            r.setTotalPrice((r.getReservedSeats()) * trip.getCost());
                            Global.showAlert(Alert.AlertType.CONFIRMATION, "Confirmed !", "Reservation is successufely Edited");
                        } else {
                            if (trip.removeSeats(seats - r.getReservedSeats())) {
                                if (creditCard.withdraw((seats - r.getReservedSeats()) * trip.getCost())) {
                                    r.setReservedSeats(seats);
                                    r.setTotalPrice((r.getReservedSeats() * trip.getCost()));
                                    Global.showAlert(Alert.AlertType.CONFIRMATION, "Confirmed !", "Reservation is successufely Edited");
                                }
                            }
                        }
                    } else {
                        Global.showAlert(Alert.AlertType.ERROR, "Error !", "Number of Required Seats exceeded number of available seats");
                    }
                }
            }
        } else {
            Global.showAlert(Alert.AlertType.ERROR, "Error !", "Can't Edit number of seats for a trip not in station");
        }
    }

    public void deleteReservation(Reservation reservation) {
        if (reservation.getTrip().isDeletableByUser()) {
            creditCard.deposit(reservation.getTotalPrice());
            reservation.getTrip().addSeats(reservation.getReservedSeats());
            myReservations.remove(reservation);
            Global.showAlert(Alert.AlertType.CONFIRMATION, "Confirmed !", "Old Reservation is successufely Deleted");
        } else {
            Global.showAlert(Alert.AlertType.CONFIRMATION, "Confirmed !", "Reservation Selected is not Deletable :(");
        }
    }

    private boolean isNewTripValid(int departure, int arrival) {
        for (Reservation t : myReservations
        ) {
            if (departure > Integer.valueOf(t.getTrip().getDepartureTime()) && departure < Integer.valueOf(t.getTrip().getArrivalTime())
                    || departure == Integer.valueOf(t.getTrip().getDepartureTime()) || arrival == Integer.valueOf(t.getTrip().getArrivalTime())
                    || arrival > Integer.valueOf(t.getTrip().getDepartureTime()) && arrival < Integer.valueOf(t.getTrip().getArrivalTime())) {
                return false;
            }
        }
        return true;
    }

    private boolean isNewPickupLocationValid(int departure, String pickup) {
        for (Reservation t : myReservations
        ) {
            if (Integer.valueOf(t.getTrip().getArrivalTime()) == departure) {
                if (pickup.equals(t.getTrip().getDestination())) {
                    return true;
                } else {
                    Global.showAlert(Alert.AlertType.ERROR, "Invalid trip ", "Can't get this trip from this pickup location you will be in another city");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean editDestination(Reservation reservation, String destination) {
        Trip dummyTrip = new Trip(reservation.getTrip());
        dummyTrip.setDestination(destination);
        for (Trip t : Trip.getTrips()
        ) {
            if (t.getPickupLocation().equals(dummyTrip.getPickupLocation()) &&
                    t.getDestination().equals(dummyTrip.getDestination())) {
                book(t, reservation.getReservedSeats());
                deleteReservation(reservation);
                Global.showAlert(Alert.AlertType.CONFIRMATION, "Confirmed !", "Reservation is successfully edited to new Destination " + destination);
                return true;
            }
        }

        Global.showAlert(Alert.AlertType.ERROR, "Error !", "Cannot Edit Destination, Such Trip not found !");
        return false;
    }
}
