package vn.edu.iuh.fit.rayarkshop.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.storage.bucket-name}")
    private String BUCKET_NAME;

    @Value("${firebase.service-account-file}")
    private String SERVICE_ACCOUNT_FILE;

    @Value("${firebase.database.url}")
    private String FIREBASE_DATABASE_URL;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        ClassPathResource serviceAccount = new ClassPathResource(SERVICE_ACCOUNT_FILE);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                .setStorageBucket(BUCKET_NAME)
                .setDatabaseUrl(FIREBASE_DATABASE_URL)
                .build();

        return FirebaseApp.initializeApp(options);
    }

}
