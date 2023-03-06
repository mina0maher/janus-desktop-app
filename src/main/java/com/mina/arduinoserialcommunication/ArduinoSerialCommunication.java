
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
 import com.fasterxml.jackson.annotation.JsonProperty;
import models.AiPredictModel;
import models.LicenseNumberModel;
import org.apache.commons.lang3.StringEscapeUtils;


/**
 *
 * @author maher
 */
public class ArduinoSerialCommunication {

    
    

    
    public static void main(String[] args) throws Exception{
      

            SerialComm s = new SerialComm();
            s.show(true);
   CloseableHttpClient httpClient = HttpClients.createDefault();
HttpPost uploadFile = new HttpPost("https://janus-gates.up.railway.app/api/ai/predict");
MultipartEntityBuilder builder = MultipartEntityBuilder.create();

// This attaches the file to the POST:
File f = new File("D:\\image.jpg");
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

System.out.print("this is the response \n \n");
ObjectMapper om = new ObjectMapper();
AiPredictModel root = om.readValue(convertStreamToString(responseEntity.getContent()), AiPredictModel.class);
for(LicenseNumberModel model : root.licenseNumber){
    System.out.print( StringEscapeUtils.unescapeJava(model.myclass)+" ");
}

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


