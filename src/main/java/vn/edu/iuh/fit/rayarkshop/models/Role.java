package vn.edu.iuh.fit.rayarkshop.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "app_role")
public class Role {

    public static final Role ROLE_USER = new Role("ROLE_USER");
    public static final Role ROLE_ADMIN = new Role("ROLE_ADMIN");


    @Id
    @Column(name = "role_name", length = 64)
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

}
