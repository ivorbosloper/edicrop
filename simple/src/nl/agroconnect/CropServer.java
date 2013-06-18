package nl.agroconnect;

import javax.xml.ws.Endpoint;

import nl.agroconnect.wsEdiCrop.v4_0.EdiPortTypeImpl;
 
public class CropServer{

    protected CropServer() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new EdiPortTypeImpl();
        String address = "http://localhost:8080/edi";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new CropServer();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
