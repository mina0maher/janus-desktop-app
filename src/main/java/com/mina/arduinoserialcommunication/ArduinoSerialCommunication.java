
package com.mina.arduinoserialcommunication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
 import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import models.GatesModel;
import models.Price;

/**
 *
 * @author maher
 */
public class ArduinoSerialCommunication {

    
    

    
    public static void main(String[] args) throws Exception{
      

            SerialComm s = new SerialComm();
            s.show(true);

            
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
    
    
    
    public static <T>T getObjectFromJson(HttpEntity httpEntity,Class<T> valueType) throws IOException{
        return new ObjectMapper().readValue(httpEntity.getContent(), valueType);
    }
    public static <T>T getObjectFromJson(String json,Class<T> valueType) throws IOException{
        return new ObjectMapper().readValue(json, valueType);
    }
    private static HttpEntity postToPredictAPI(String imagePath)throws FileNotFoundException, IOException{
           CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost uploadFile = new HttpPost("https://janus-gates.up.railway.app/api/ai/predict");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            // This attaches the file to the POST:
            File f = new File(imagePath);
            builder.addBinaryBody(
                "image",
                new FileInputStream(f),
                ContentType.APPLICATION_OCTET_STREAM,
                f.getName()
            );

        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        CloseableHttpResponse response = httpClient.execute(uploadFile);
        HttpEntity responseEntity = response.getEntity();
        return responseEntity;
    }
    private static String getGates() throws MalformedURLException, IOException{
        String urlString = "https://janus-gates.up.railway.app/api/gates";
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        return convertStreamToString(is);
    }
    
    private static String convertStreamToString(InputStream is) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line ;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            return sb.toString();
        }
}


