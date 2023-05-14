package vn.edu.iuh.fit.rayarkshop.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AccountInfoUpdateRequest {

    private String fullName;
    private String phone;
    private boolean gender;
    private LocalDate birthday;

}
