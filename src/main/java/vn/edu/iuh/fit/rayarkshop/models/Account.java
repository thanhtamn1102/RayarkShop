package vn.edu.iuh.fit.rayarkshop.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private long id;

    @Column(name = "user_name", length = 64, nullable = false)
    private String userName;

    @Column(name = "email", length = 128, nullable = false)
    private String email;

    @Column(name = "hash_pass", length = 255, nullable = false)
    private String hashPass;

    @Column(name = "avatar", columnDefinition = "text")
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns =  @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    public Account(String userName, String email, String hashPass, List<Role> roles) {
        this.userName = userName;
        this.email = email;
        this.hashPass = hashPass;
        this.roles = roles;
    }

    public Account(String userName, String email, String hashPass) {
        this.userName = userName;
        this.email = email;
        this.hashPass = hashPass;
    }
}
