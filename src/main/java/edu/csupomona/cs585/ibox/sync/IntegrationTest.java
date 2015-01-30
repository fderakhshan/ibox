package edu.csupomona.cs585.ibox.sync;

import static org.junit.Assert.*;



import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;


public class IntegrationTest {
Drive Service;
GoogleDriveFileSyncManager GoogleDriveFileSyncManager_test;
private void setup() throws IOException{
initGoogleDriveServices(); 
GoogleDriveFileSyncManager_test = new GoogleDriveFileSyncManager(Service);
}


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	  public void initGoogleDriveServices() throws IOException {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        try{
            GoogleCredential credential = new  GoogleCredential.Builder()
              .setTransport(httpTransport)
              .setJsonFactory(jsonFactory)
              .setServiceAccountId("160677659330-04hvpbrcv3vddh1pbtlk840hmsjnf9cp@developer.gserviceaccount.com")
              .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
              .setServiceAccountPrivateKeyFromP12File(new java.io.File("My Project-f4d97d7d5a4b.p12"))
              .build();

            Service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName("ibox").build();  
        }catch(GeneralSecurityException e){
            e.printStackTrace();
        }
               
    }
}
