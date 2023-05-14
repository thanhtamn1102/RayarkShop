package vn.edu.iuh.fit.rayarkshop.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FAuthLoginRequest {

    private String email;
    private String password;
    private long expiresIn;

}
