package iBUS.Project.Trips;

public class Reservation {
    private Trip trip;
    private int reservedSeats;
    private double totalPrice;

    public Reservation(Trip trip, int reservedSeats, double totalPrice) {
        this.trip = trip;
        this.reservedSeats = reservedSeats;
        this.totalPrice = totalPrice;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "From : " + trip.getPickupLocation() + "     To : " + trip.getDestination() + "     Departure at: " + trip.getDepartureTime()
                + "     Arrives at : " + trip.getArrivalTime() + "     Number of reserved Seats : " + reservedSeats +
                "     Total Price : " + totalPrice + '$' + "     Trip Status : " + trip.getTripStatus();
    }
}
