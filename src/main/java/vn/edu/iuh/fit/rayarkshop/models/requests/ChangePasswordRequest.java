package vn.edu.iuh.fit.rayarkshop.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordRequest {

    private String currentPassword;
    private String newPassword;

}
