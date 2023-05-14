package vn.edu.iuh.fit.rayarkshop.services;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.constants.AppRole;
import vn.edu.iuh.fit.rayarkshop.models.NewAccount;

import java.io.IOException;
import java.util.List;

@Service
public interface FirebaseAuthenticationService {

    UserRecord createLoginWithEmailAndPassword(String email, String password, List<AppRole> roles) throws FirebaseAuthException, IOException;

    NewAccount loginWithEmailAndPassword(String email, String password) throws IOException, FirebaseAuthException;

    boolean changePassword(String email, String currentPassword, String newPassword) throws IOException, FirebaseAuthException;

    boolean changePhotoUrl(String uid, String photoUrl) throws IOException, FirebaseAuthException;

}
