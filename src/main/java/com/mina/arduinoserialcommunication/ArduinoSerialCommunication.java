
package com.mina.arduinoserialcommunication;


import models.GatesModel;
import static utilities.Utilities.postToPredictAPI;

/**
 *
 * @author maher
 */
public class ArduinoSerialCommunication {

    
    private static GatesModel SELECTED_GATE = null; 

    
    public static void main(String[] args) throws Exception{
      
//
            ChooseGate c = new ChooseGate();
            c.show();

    ///    postToPredictAPI("D://image.jpg");
//            AiPredictModel root = getObjectFromJson(postToPredictAPI("D://image.jpg"), AiPredictModel.class);
//            for(LicenseNumberModel model : root.licenseNumber){
//                System.out.print( model.myclass+" ");
//            }
//            System.out.println("\n"+root.vehicleClass);
//GatesModel[] gatesModel = getObjectFromJson(getGates(), GatesModel[].class);
//
//for(GatesModel model:gatesModel){
//    System.out.println(model.name);
//    for(Price price:model.prices){
//        System.out.println(price.vehicleType.name + "  -  "+price.price);
//    }
//}
        }
    
    public static void setSelectedGate(GatesModel gate){
        SELECTED_GATE = gate;
    }
    public static GatesModel getSelectedGate(){
        return SELECTED_GATE;
    }
}


