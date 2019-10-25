package iBUS.Project.Buses;

public abstract class Bus {
    private final String busInfo;
    private final String plateNumber;
    private final int seats;

    public Bus(String busInfo, String plateNumber, int seats) {
        this.busInfo = busInfo;
        this.plateNumber = plateNumber;
        this.seats = seats;
    }

    public String getBusInfo() {
        return busInfo;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getSeats() {
        return seats;
    }
}
