/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author maher
 */
public class Utilities {
    
    
    public static <T>T getObjectFromJson(HttpEntity httpEntity,Class<T> valueType) throws IOException{
       
        return new ObjectMapper().readValue(httpEntity.getContent(), valueType);
    }
    public static <T>T getObjectFromJson(String json,Class<T> valueType) throws IOException{
                  
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
        return  objectMapper.readValue(json, valueType);
    }
    public static HttpEntity postToPredictAPI(String imagePath)throws IOException{
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
        if( response.getStatusLine().getStatusCode()== 200){
            HttpEntity responseEntity = response.getEntity();
            System.out.print(convertStreamToString(responseEntity.getContent()));
            return responseEntity;
       }else{
            System.out.print(response.getStatusLine());
           return postToPredictAPI(imagePath);
       }
    }
    public static HttpEntity getGates() throws MalformedURLException, IOException{
        
        
         CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet ("https://janus-gates.up.railway.app/api/gates");

    
        CloseableHttpResponse response = httpClient.execute(request);
       if( response.getStatusLine().getStatusCode()== 200){
            HttpEntity responseEntity = response.getEntity();
            return responseEntity;
       }else{
           return null;
       }
    }
    
    public static String convertStreamToString(InputStream is){
           
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
