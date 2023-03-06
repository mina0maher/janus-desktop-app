/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 *
 * @author maher
 */
public class AiPredictModel{
    @JsonProperty("License Number") 
    public ArrayList<LicenseNumberModel> licenseNumber;
    @JsonProperty("Vehicle Class") 
    public String vehicleClass;
}
