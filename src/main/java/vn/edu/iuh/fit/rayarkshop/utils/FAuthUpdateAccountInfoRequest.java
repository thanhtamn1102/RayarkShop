package vn.edu.iuh.fit.rayarkshop.utils;

import lombok.Data;

@Data
public class FAuthUpdateAccountInfoRequest {

    private String idToken;
    private String photoUrl;
    private boolean returnSecureToken = true;

    public FAuthUpdateAccountInfoRequest(String idToken, String photoUrl) {
        this.idToken = idToken;
        this.photoUrl = photoUrl;
    }
}
