package com.example.kaylee.pilot;

/**
 * Created by Kaylee on 3/28/2017.
 */

public class DataStore {

    private static DataStore instance = null;
    private DataStore(){

    }
    public static DataStore getInstance(){
        if(instance == null)
            instance = new DataStore();
        return instance;
    }

    private Integer MPG;
    private Integer distance;
    private Double gasPrice;
    private Double ticketPrice;

    public String calculate(){
        double carPrice = (distance*gasPrice)/MPG;
        if(carPrice<ticketPrice)
            return "Car";
        return "Plane";
    }

    public void setMPG(Integer MPG) {
        this.MPG = MPG;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setGasPrice(Double gasPrice) {
        this.gasPrice = gasPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getDistance() {

        return distance;
    }

    public Double getGasPrice() {
        return gasPrice;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public Integer getMPG() {

        return MPG;
    }
}
