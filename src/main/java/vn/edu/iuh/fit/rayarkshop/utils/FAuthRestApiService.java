package vn.edu.iuh.fit.rayarkshop.utils;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.Map;

@Service
public interface FAuthRestApiService {

    @POST("v1/accounts:signInWithPassword")
    Call<FAuthResponse> loginWithEmailAndPassword(@Query("key") String key, @Body FAuthLoginRequest firebaseAuthenticationLoginRequest);

//    @POST("v1/accounts:signUp")
//    Call<FAuthResponse> signUpWithEmailAndPassword(@Query("key") String key, @Body)

    @POST("v1/accounts:sendOobCode")
    Call<Object> sendEmailVerification(@Query("key") String key, @Body FAuthSendEmailVerificationRequest sendEmailVerificationRequest);

    @POST("v1/accounts:update")
    Call<Map<String, Object>> changePassword(@Query("key") String key, @Body FAuthChangePasswordRequest changePasswordReset);

    @POST("v1/accounts:update")
    Call<Map<String, Object>> updateAccountInfo(@Query("key") String key, @Body FAuthUpdateAccountInfoRequest updateAccountInfoRequest);

}
