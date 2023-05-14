package vn.edu.iuh.fit.rayarkshop.constants;

import lombok.Data;

@Data
public class AppRole {

    public static AppRole USER = new AppRole("ROLE_USER");
    public static AppRole ADMIN = new AppRole("ROLE_ADMIN");
    public static AppRole ANONYMOUS = new AppRole("ROLE_ANONYMOUS");


    private String roleName;

    public AppRole(String roleName) {
        this.roleName = roleName;
    }

}
