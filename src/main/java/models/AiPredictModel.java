/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author maher
 */
public class AiPredictModel{
    public String licenseNumber;
    public VehicleType vehicleType;
    public ArrayList<Ticket> tickets;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "AiPredictModel{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", vehicleType=" + vehicleType +
                ", tickets="+stringTickets()+
                '}';
    }
    private String stringTickets(){
        String tic = "";
        for(Ticket t :tickets){
            tic+=tickets.toString();
        }
        return tic;
    }
}
