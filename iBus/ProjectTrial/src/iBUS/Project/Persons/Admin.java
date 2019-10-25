package iBUS.Project.Persons;

import iBUS.Project.Trips.Trip;

public class Admin extends Person {
    private static int counter = 0;

    public Admin(String nationalID, String name, String gender, int age, String username, String password, String email, String sequrityQuestion, String sequrityAnswer, String phone) {
        super(nationalID, name, gender, age, username, password, email, sequrityQuestion, sequrityAnswer, phone);
        counter++;
        Person.getPeople().add(this);
    }

    public void changeTripStatus(Trip trip, String newStatus) {
        trip.setTripStatus(newStatus);
    }
}
