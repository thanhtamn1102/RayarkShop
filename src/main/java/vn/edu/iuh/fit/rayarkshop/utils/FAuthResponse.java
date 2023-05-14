package vn.edu.iuh.fit.rayarkshop.utils;

import lombok.Data;

@Data
public class FAuthResponse {

    private String kind;
    private String localId;
    private String email;
    private String displayName;
    private String idToken;
    private boolean registered;
    private String refreshToken;
    private long expiresIn;

}
