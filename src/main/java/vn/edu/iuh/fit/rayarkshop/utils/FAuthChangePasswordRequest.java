package vn.edu.iuh.fit.rayarkshop.utils;

import lombok.Data;

@Data
public class FAuthChangePasswordRequest {

    private String idToken;
    private String password;
    private boolean returnSecureToken = true;

    public FAuthChangePasswordRequest(String idToken, String password) {
        this.idToken = idToken;
        this.password = password;
    }
}
