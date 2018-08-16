package com.example.autocodetemplate.ohter.practice;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 */
public class ObserverPattern {

    public static void main(String[] args) {
        House house = new House(1000);
        PurchaseUser purchaseUser1 = new PurchaseUser("yates");
        PurchaseUser purchaseUser2 = new PurchaseUser("yangchao");
        PurchaseUser purchaseUser3 = new PurchaseUser("yangchaowife");
        house.addObserver(purchaseUser1);
        house.addObserver(purchaseUser2);
        house.addObserver(purchaseUser3);
        house.setPrice(8000);
        house.setPrice(2000);

    }
}

class House extends Observable {
    private double price;

    public House(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        setChanged();
        message mes = new message();
        mes.setStatus("ok");
        mes.setMes("price change" + getPrice());
        mes.setTime(LocalDateTime.now().toString());
        this.notifyObservers(mes);
    }

    @Override
    public String toString() {
        return "current price :" + getPrice();
    }

}

class message {
    private String status;
    private String mes;
    private String time;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

class PurchaseUser implements Observer {
    private String name;

    public PurchaseUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(Observable o, Object arg) {
        if (o instanceof House) {
            message mes = (message) arg;

            System.out.println("user " + getName() + " observe  " + mes.getMes() + " time " + mes.getTime());
        }
    }
}