package vn.edu.iuh.fit.rayarkshop.utils;

import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Map;

@Component
public class FAuthRestApiServiceImpl implements FAuthRestApiService {

    private FAuthRestApiService fAuthRestApiService;

    public FAuthRestApiServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://identitytoolkit.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fAuthRestApiService = retrofit.create(FAuthRestApiService.class);
    }

    @Override
    public Call<FAuthResponse> loginWithEmailAndPassword(String key, FAuthLoginRequest firebaseAuthenticationLoginRequest) {
        return fAuthRestApiService.loginWithEmailAndPassword(key, firebaseAuthenticationLoginRequest);
    }

    @Override
    public Call<Object> sendEmailVerification(String key, FAuthSendEmailVerificationRequest sendEmailVerificationRequest) {
        return fAuthRestApiService.sendEmailVerification(key, sendEmailVerificationRequest);
    }

    @Override
    public Call<Map<String, Object>> changePassword(String key, FAuthChangePasswordRequest changePasswordReset) {
        return fAuthRestApiService.changePassword(key, changePasswordReset);
    }

    @Override
    public Call<Map<String, Object>> updateAccountInfo(String key, FAuthUpdateAccountInfoRequest updateAccountInfoRequest) {
        return fAuthRestApiService.updateAccountInfo(key, updateAccountInfoRequest);
    }
}
