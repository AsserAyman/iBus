package iBUS.Project.Persons;

import iBUS.Global;
import javafx.scene.control.Alert;

public class CreditCard {
    private final String cardNumber;
    private final String securityNumber;
    private double balance;

    public CreditCard(String cardNumber, String securityNumber, double balance) {
        this.cardNumber = cardNumber;
        this.securityNumber = securityNumber;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public double getBalance() {
        return balance;
    }

    private boolean isWithdrawValid(double tripCost) {
        return (tripCost <= this.balance);
    }

    boolean withdraw(double tripCost) {
        if (isWithdrawValid(tripCost)) {
            this.balance -= tripCost;
            return true;
        } else {
            Global.showAlert(Alert.AlertType.ERROR,"Error !","Total Cost Exceeded Your Credit Card Balance ");
            return false;
        }
    }

    void deposit(double money) {
        this.balance += money;
    }
}
