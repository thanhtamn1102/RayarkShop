package vn.edu.iuh.fit.rayarkshop.services.impls;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.iuh.fit.rayarkshop.services.FirebaseStorageService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Component
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    @Value("${firebase.storage.bucket-name}")
    private String BUCKET_NAME;

    @Value("${firebase.service-account-file}")
    private String SERVICE_ACCOUNT_FILE;

    @Bean
    private Tika getTika() {
        return new Tika();
    }

    @Autowired
    public void FirebaseStorageServiceImpl() {
        try {
            ClassPathResource serviceAccount = new ClassPathResource(SERVICE_ACCOUNT_FILE);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                    .setStorageBucket(BUCKET_NAME)
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String uploadFile(InputStream inputStream, String fileName) throws IOException {
        Blob blob = StorageClient.getInstance().bucket().create(fileName, inputStream, getTika().detect(inputStream));

        long expirationTime = 365;
        String signedUrl = blob.signUrl(expirationTime, TimeUnit.DAYS).toString();

        return signedUrl;
    }

    public boolean deleteFile(String fileName) {
        return StorageClient.getInstance().bucket().getStorage().delete(BlobId.of(BUCKET_NAME, fileName));
    }

    public String getFileURL(String fileName, String token) {
        return "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/" + fileName + "?alt=media&token=" + token;
    }

}
