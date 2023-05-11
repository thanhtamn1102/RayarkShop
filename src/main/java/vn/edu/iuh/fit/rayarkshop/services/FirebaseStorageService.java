package vn.edu.iuh.fit.rayarkshop.services;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface FirebaseStorageService {

    String uploadFile(InputStream inputStream, String fileName) throws IOException;

    String getFileURL(String fileName, String token) throws FirebaseAuthException;

    public boolean deleteFile(String fileName);

}
