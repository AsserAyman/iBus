package iBUS.DataBasePackage;


import iBUS.Project.Buses.Bus;
import iBUS.Project.Buses.ClassicBus;
import iBUS.Project.Buses.EliteBus;
import iBUS.Project.Persons.Driver;
import iBUS.Project.Persons.*;
import iBUS.Project.Trips.Reservation;
import iBUS.Project.Trips.Trip;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DataBaseClass {
    final String DataBaseName = "iBusDataBAse.db";
    final String DataBasePath = "jdbc:sqlite:C:\\Users\\Asser Ayman\\Desktop\\oopp\\Prepare to Final Project\\ProjectTrial\\" + DataBaseName;

    public static final String tripTableName = "Trips";
    public static final String tripDate = "Date";
    public static final String tripDepartureTime = "Departure_Time";
    public static final String tripArrivalTime = "Arrival_Time";
    public static final String tripPickupLocation = "Pickup_Location";
    public static final String tripDestinationLocation = "Destination_Location";
    public static final String tripBusClass = "Bus_Class";
    public static final String tripBusPlate = "Bus_Plate";
    public static final String tripCost = "Cost";

    public static final String busesTableName = "Buses";
    public static final String busPlateNumber = "Plate_Number";
    public static final String busClass = "Class";
    public static final String busNumberOfSeats = "Seats";
    public static final int eliteSeatsNumber = 12;
    public static final int classicSeatsNumber = 24;
    public static final String eliteBusInfo = "This class offers buses with luxurious wide seats and salons. This bus is oriented to have multiple four seats facing each other with a table in the middle. In addition to a salon at the end of the bus that holds 6 luxurious wide seats with a table in the middle. The journey is direct, with an attendant offering free drinks and a free meal";
    public static final String classicBusInfo = "This is an air-conditioned bus, equipped with a video player, bathroom, The trip on this bus is direct to the drop-off station, making the journey time minimalistic. iBus offers a free snack to keep you busy along the journey.";

    public static final String adminsTableName = "Admins";
    public static final String customersTableName = "Customers";
    public static final String driversTableName = "Drivers";

    public static final String personNationalID = "National_ID";
    public static final String personName = "Name";
    public static final String personGender = "Gender";
    public static final String personAge = "Age";
    public static final String personUsername = "Username";
    public static final String personPassword = "Password";
    public static final String personEmail= "Email";
    public static final String personSecurityQuestion = "Security_Question";
    public static final String personSecurityAnswer = "Security_Answer";

    public static final String customerCreditCardNumber = "Credit_Card_Number";
    public static final String creditCardSecurityNumber = "Credit_Card_Security_Number";
    public static final String customerCreditCardBalance = "Credit_Card_Balance";

    public static final String driverBusPlate = "Bus_Plate";


    public static final String addTripString = "INSERT INTO " + tripTableName + " ("
            + tripDate + ", " + tripDepartureTime + ", " + tripArrivalTime + ", " + tripPickupLocation + ", "
            + tripDestinationLocation + ", " + tripBusClass + ", " + tripBusPlate + ", " + tripCost + ")"
            + " VALUES (?,?,?,?,?,?,?,?)";

    public static final String addBussString =
            "INSERT INTO " + busesTableName + "( "
                    + busPlateNumber + ", " + busClass + ", " + busNumberOfSeats + ") VALUES (?,?,?)";

    public static final String addAdminString =
            "INSERT INTO "+ adminsTableName +"( "
                    +personNationalID+", "+personName+", "+personGender+", "
                    +personAge+", "+personUsername+", "+personPassword+", "
                    +personEmail+", "+personSecurityQuestion+", "+personSecurityAnswer
                    + ") VALUES (?,?,?,?,?,?,?,?,?)";

    public static final String addCustomerString =
            "INSERT INTO "+ customersTableName +"( "
                    +personNationalID+", "+personName+", "+personGender+", "
                    +personAge+", "+personUsername+", "+personPassword+", "
                    +personEmail+", "+personSecurityQuestion+", "+personSecurityAnswer+", "
                    +customerCreditCardNumber+", "+creditCardSecurityNumber+", "
                    +customerCreditCardBalance+") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    public static final String addDriverString =
            "INSERT INTO "+ driversTableName +"( "
                    +personNationalID+", "+personName+", "+personGender+", "
                    +personAge+", "+personUsername+", "+personPassword+", "
                    +personEmail+", "+personSecurityQuestion+", "+personSecurityAnswer+", "
                    +driverBusPlate+ ") VALUES (?,?,?,?,?,?,?,?,?,?)";



    static Connection connection;
    static PreparedStatement addTripsStatement;
    static PreparedStatement addBussStatement;
    static PreparedStatement addAdminStatement;
    static PreparedStatement addCustomerStatement;
    static PreparedStatement addDriverStatement;

    public boolean open() {
        try {
            connection = DriverManager.getConnection(DataBasePath);
            return true;
        } catch (SQLException e) {
            System.out.println("Error connecting to the database " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Can't close the database " + e.getMessage());
        }
    }

    public static void addTRip(String date, String departureTime, String arrivalTime, String pickupLocation, String destinationLocation, String busClass, String busPlate, int cost) {
        try {

            addTripsStatement = connection.prepareStatement(addTripString);

            addTripsStatement.setString(1, date);
            addTripsStatement.setString(2, departureTime);
            addTripsStatement.setString(3, arrivalTime);
            addTripsStatement.setString(4, pickupLocation);
            addTripsStatement.setString(5, destinationLocation);
            addTripsStatement.setString(6, busClass);
            addTripsStatement.setString(7, busPlate);
            addTripsStatement.setInt(8, cost);

            addTripsStatement.execute();

        } catch (SQLException e) {
            System.out.println("Can't add trip " + e.getMessage());
        } finally {

            try {
                if (addTripsStatement != null) {
                    addTripsStatement.close();
                }
            } catch (SQLException e) {
                addTripsStatement = null;
            }
        }
    }

    public static void addBus(String plateNum, String busClass) {

        try {
            addBussStatement = connection.prepareStatement(addBussString);

            addBussStatement.setString(1, plateNum);
            addBussStatement.setString(2, busClass);
            if (busClass.equals("Elite")) {
                addBussStatement.setInt(3, eliteSeatsNumber);
            } else if (busClass.equals("Classic")) {
                addBussStatement.setInt(3, classicSeatsNumber);
            }

            addBussStatement.execute();

        } catch (SQLException e) {
            System.out.println("Couldn't add bus " + e.getMessage());
        } finally {

            try {
                if (addBussStatement != null) {
                    addBussStatement.close();
                }
            } catch (SQLException e) {
                addBussStatement = null;
            }
        }
    }

    public static void addAdmin(String nationalID,String name,String gender,int age,String username, String password,String email,String securityQuestion,String securityAnswer){
        try{
            addAdminStatement = connection.prepareStatement(addAdminString);

            addAdminStatement.setString(1,nationalID);
            addAdminStatement.setString(2,name);
            addAdminStatement.setString(3,gender);
            addAdminStatement.setInt(4,age);
            addAdminStatement.setString(5,username);
            addAdminStatement.setString(6,password);
            addAdminStatement.setString(7,email);
            addAdminStatement.setString(8,securityQuestion);
            addAdminStatement.setString(9,securityAnswer);

            addAdminStatement.execute();
        }catch(SQLException e){
            System.out.println("Can't add admin"+e.getMessage());
        }
        finally {
            try {
                if (addAdminStatement!= null)
                {
                    addAdminStatement.close();
                }
            }catch(SQLException e){
                addAdminStatement = null;
            }
        }
    }

    public static void addDriver(String nationalID,String name,String gender,int age,String username, String password,String email,String securityQuestion,String securityAnswer,String busPlate){
        try{
            addDriverStatement = connection.prepareStatement(addDriverString);

            addDriverStatement.setString(1,nationalID);
            addDriverStatement.setString(2,name);
            addDriverStatement.setString(3,gender);
            addDriverStatement.setInt(4,age);
            addDriverStatement.setString(5,username);
            addDriverStatement.setString(6,password);
            addDriverStatement.setString(7,email);
            addDriverStatement.setString(8,securityQuestion);
            addDriverStatement.setString(9,securityAnswer);
            addDriverStatement.setString(10,busPlate);

            addDriverStatement.execute();

        }catch(SQLException e){
            System.out.println("Can't add driver"+e.getMessage());
        }
        finally {
            try {
                if (addDriverStatement!= null)
                {
                    addDriverStatement.close();
                }
            }catch(SQLException e){
                addDriverStatement = null;
            }
        }
    }

    public static void addCustomer(String nationalID,String name,String gender,int age,String username, String password,String email,String securityQuestion,String securityAnswer,String creditCardNumber,String creditCardSecurityNumber,int creditCardBalance){
        try{
            addCustomerStatement = connection.prepareStatement(addCustomerString);

            addCustomerStatement.setString(1,nationalID);
            addCustomerStatement.setString(2,name);
            addCustomerStatement.setString(3,gender);
            addCustomerStatement.setInt(4,age);
            addCustomerStatement.setString(5,username);
            addCustomerStatement.setString(6,password);
            addCustomerStatement.setString(7,email);
            addCustomerStatement.setString(8,securityQuestion);
            addCustomerStatement.setString(9,securityAnswer);
            addCustomerStatement.setString(10,creditCardNumber);
            addCustomerStatement.setString(11,creditCardSecurityNumber);
            addCustomerStatement.setInt(12,creditCardBalance);

            addCustomerStatement.execute();
        }catch(SQLException e){
            System.out.println("Can't add customer"+e.getMessage());
        }
        finally
        {
            try
            {
                if (addCustomerStatement!= null)
                {
                    addCustomerStatement.close();
                }
            }catch(SQLException e){
                addCustomerStatement = null;
            }
        }
    }

    public static int isAccountFound(String username) {

        try {

            PreparedStatement st = connection.prepareStatement("SELECT * FROM Admins WHERE Username = ? COLLATE NOCASE");
            st.setString(1,username);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return 1;
            }

            st = connection.prepareStatement("SELECT * from Customers WHERE Username = ? COLLATE NOCASE");
            st.setString(1,username);
            rs = st.executeQuery();
            if (rs.next()){
                return 2;
            }

            st = connection.prepareStatement("SELECT * FROM Drivers WHERE Username = ? COLLATE NOCASE");
            st.setString(1,username);
            rs = st.executeQuery();
            if (rs.next()){
                return 3;
            }


            return 4;
        }catch (SQLException e){
            System.out.println("Something went wrong "+e.getMessage());
            return 5;
        }
    }

    public static int signIn(String username,String password){
        int searchingTable = isAccountFound(username);
        try{
            if (searchingTable == 1){
                PreparedStatement st = connection.prepareStatement("SELECT Password FROM Admins WHERE Username = ? COLLATE NOCASE ");
                st.setString(1,username);
                ResultSet rs = st.executeQuery();
                if (rs.next()){
                    if(rs.getString(1).equals(password)){
                        return 1;
                    }
                }
            }
            else if (searchingTable == 2){
                PreparedStatement st = connection.prepareStatement("SELECT Password FROM Customers WHERE Username = ? COLLATE NOCASE ");
                st.setString(1,username);
                ResultSet rs = st.executeQuery();
                if (rs.next()){
                    if(rs.getString(1).equals(password)){
                        return 2;
                    }
                }
            }
            else if (searchingTable == 3){
                PreparedStatement st = connection.prepareStatement("SELECT Password FROM Drivers WHERE Username = ? COLLATE NOCASE ");
                st.setString(1,username);
                ResultSet rs = st.executeQuery();
                if (rs.next()){
                    if(rs.getString(1).equals(password)){
                        return 3;
                    }
                }
            }

            return 4;
        }catch(SQLException e){
            System.out.println("something went wrong "+e.getMessage());
            return 5;
        }
    }

    public static ArrayList<Person> getAccounts(){
        try
        {

            ArrayList<Person> personsList = new ArrayList<Person>();

            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM Admins");
            while (rs.next()){
                Person temp = new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
                personsList.add(temp);
            }

            rs = st.executeQuery("SELECT * FROM Customers");
            while (rs.next()){
                CreditCard tempCreditCard = new CreditCard(rs.getString(11),rs.getString(12),rs.getDouble(13));
                Person temp = new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),tempCreditCard);
                personsList.add(temp);
            }

            rs = st.executeQuery("SELECT Drivers.National_ID, Drivers.Name, Drivers.Gender, Drivers.Age, Drivers.Username, Drivers.Password, Drivers.Email, Drivers.Security_Question, Drivers.Security_Answer, Drivers.phone, Buses.Class, Buses.Plate_Number, Buses.Seats FROM Drivers JOIN Buses ON Drivers.Bus_Plate = Buses.Plate_Number ;");
            while (rs.next()) {

                Bus tempBus = createBus(rs.getString(12),rs.getString(11));

                if (tempBus != null) {
                    Person tempPerson = new Driver(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), tempBus);
                    personsList.add(tempPerson);
                }
            }

            return personsList;

        }catch(SQLException e){
            System.out.println("can't load accounts"+e.getMessage());
            return null;
        }
    }

    public static ArrayList<Trip> getTrips() {
        try {
            ArrayList<Trip> tripsList = new ArrayList<Trip>();

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT Drivers.*,Buses.Class, Trips.Cost,Trips.Pickup_Location, Trips.Destination_Location ,Trips.Departure_Time, Trips.Arrival_Time,Trips.Trip_Status, Trips.Available_Seats FROM Trips INNER JOIN Drivers ON Drivers.Bus_Plate = Trips.Bus_Plate INNER JOIN Buses ON Buses.Plate_Number = Drivers.Bus_Plate;");

            while (rs.next()) {

           Trip trip = createTrip(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getDouble(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getInt(19));
           if (trip != null){
               tripsList.add(trip);
           }
                /*               Bus tempBus = createBus(rs.getString(11),rs.getString(12));
               if (tempBus!=null){
                   Driver tempDriver = new Driver(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),tempBus);
                   Trip tempTrip = new Trip(rs.getDouble(13),tempDriver,rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getInt(19));
                   tripsList.add(tempTrip);
*/
            }
            return tripsList;
        }catch (SQLException e){
            System.out.println("Can't load trips"+e.getMessage());
            return  null;
        }
    }

    public static ArrayList<Reservation> getReservations (){
        return null;
    }

    private static Bus createBus (String busPlate, String busClass){
        Bus tempBus = null;
        if (busClass.equals("Elite")){
            tempBus = new EliteBus(busPlate);
        }
        else if (busClass.equals("Classic")){
            tempBus = new ClassicBus(busPlate);
        }
        return tempBus;
    }

    private static Trip createTrip(String driverNationalID, String driverName, String driverGender, int driverAge, String driverUsername, String driverPassword, String driverEmail, String driverSecurityQuestion, String driverSecurityAnswer, String driverPhone, String busPlate, String busClass, double cost, String pickupLocation, String destinationLocation, String departureTime, String arrivalTime, String tripStatus, int availableSeats){

        Trip tempTrip = null;

        Bus tempBus = createBus(busPlate,busClass);
        if (tempBus!=null){
            Driver tempDriver = new Driver(driverNationalID,driverName,driverGender,driverAge,driverUsername,driverPassword,driverEmail,driverSecurityQuestion,driverSecurityAnswer,driverPhone,tempBus);

                tempTrip = new Trip(cost,tempDriver,pickupLocation,destinationLocation,departureTime,arrivalTime,tripStatus,availableSeats);
        }
        return tempTrip;
    }
    public static void initializeTrips (Driver D){

        try
        {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Trips WHERE Trips.Bus_Plate = '"+D.getPersonalBus().getPlateNumber()+"'");
            while (rs.next()){
                Trip tempTrip = new Trip(rs.getDouble(8),D,rs.getString(4),rs.getString(5),rs.getString(2),rs.getString(3),rs.getString(9),rs.getInt(10));
            }
        }catch(SQLException e){
            System.out.println("Error loading trips "+e.getMessage());
        }
    }
}


