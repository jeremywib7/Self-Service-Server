package com.j23.server.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseConfig {

  @PostConstruct
  public void configureFirebaseConnection() throws IOException {

    File file = ResourceUtils.getFile("classpath:config/serviceAccountKey.json");

    FileInputStream serviceAccount = new FileInputStream(file);

    FirebaseOptions options = FirebaseOptions.builder()
      .setCredentials(GoogleCredentials.fromStream(serviceAccount))
      .setDatabaseUrl("https://self-service-4820d-default-rtdb.asia-southeast1.firebasedatabase.app")
      .build();

    if (FirebaseApp.getApps().isEmpty()) { //<--- check with this line
      FirebaseApp.initializeApp(options);
    }

  }
}