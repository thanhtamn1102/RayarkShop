package vn.edu.iuh.fit.rayarkshop.services.impls;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.iuh.fit.rayarkshop.constants.AppRole;
import vn.edu.iuh.fit.rayarkshop.models.NewAccount;
import vn.edu.iuh.fit.rayarkshop.services.FirebaseAuthenticationService;
import vn.edu.iuh.fit.rayarkshop.utils.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FirebaseAuthenticationServiceImpl implements FirebaseAuthenticationService {

    @Qualifier("FAuthRestApiServiceImpl")
    @Autowired
    private FAuthRestApiService fAuthRestApiService;

    @Value("${firebase.api.web.key}")
    private String FIREBASE_API_WEB_KEY;

    @Override
    public UserRecord createLoginWithEmailAndPassword(String email, String password, List<AppRole> roles) throws FirebaseAuthException, IOException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password);
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

        Map<String, Object> claims = new HashMap<>();
        roles.forEach(role -> claims.put(role.getRoleName(), true));

        FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);

        return userRecord;
    }

    @Override
    public NewAccount loginWithEmailAndPassword(String email, String password) throws IOException, FirebaseAuthException {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        Response<FAuthResponse> response = fAuthRestApiService.loginWithEmailAndPassword(
                FIREBASE_API_WEB_KEY,
                new FAuthLoginRequest(email, password, 36000)
        ).execute();

        NewAccount account = new NewAccount();

        if(response.isSuccessful() && response.body() != null) {
            String uid = response.body().getLocalId();
            String customToken = auth.createCustomToken(uid);

            account.setLocalId(response.body().getLocalId());
            account.setIdToken(customToken);

            Map<String, Object> roles = FirebaseAuth.getInstance().getUser(uid).getCustomClaims();
            account.setRoles(roles);
        }

        return account;
    }

    @Override
    public boolean changePassword(String email, String currentPassword, String newPassword) throws IOException, FirebaseAuthException {
        NewAccount newAccount = loginWithEmailAndPassword(email, currentPassword);

        System.out.println(newAccount);

        if(newAccount != null && newAccount.getLocalId() != null) {

            String uid = FirebaseAuth.getInstance().getUser(newAccount.getLocalId()).getUid();

            UserRecord.UpdateRequest updateRequest = new UserRecord.UpdateRequest(uid);
            updateRequest.setPassword(newPassword);

            FirebaseAuth.getInstance().updateUser(updateRequest);

            return true;
        }
        return false;
    }

    @Override
    public boolean changePhotoUrl(String uid, String photoUrl) throws IOException, FirebaseAuthException {
        UserRecord.UpdateRequest updateRequest = new UserRecord.UpdateRequest(uid);
        updateRequest.setPhotoUrl(photoUrl);

        FirebaseAuth.getInstance().updateUser(updateRequest);

        return true;
    }


}
