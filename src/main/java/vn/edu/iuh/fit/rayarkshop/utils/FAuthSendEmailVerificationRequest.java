package vn.edu.iuh.fit.rayarkshop.utils;

public class FAuthSendEmailVerificationRequest {

    private String requestType = "VERIFY_EMAIL";
    private String idToken;
    private int oobCodeDuration;

    public FAuthSendEmailVerificationRequest(String idToken, int oobCodeDuration) {
        this.idToken = idToken;
        this.oobCodeDuration = oobCodeDuration;
    }
}
