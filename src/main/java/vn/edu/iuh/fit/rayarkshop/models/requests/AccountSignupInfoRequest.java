package vn.edu.iuh.fit.rayarkshop.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountSignupInfoRequest {

    private String fullName;
    private String phone;
    private String email;
    private String password;
    private String confirmPassword;

}
