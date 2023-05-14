package vn.edu.iuh.fit.rayarkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.edu.iuh.fit.rayarkshop.constants.AppRole;
import vn.edu.iuh.fit.rayarkshop.services.FirebaseAuthenticationService;

import java.util.Arrays;

@SpringBootApplication
public class RayarkShopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RayarkShopApplication.class, args);
    }

    @Autowired
    private FirebaseAuthenticationService firebaseAuthenticationService;

    @Override
    public void run(String... args) throws Exception {
//        firebaseAuthenticationService.createLoginWithEmailAndPassword("admin@gmail.com", "Thanhtam@101002", Arrays.asList(AppRole.ADMIN));
    }
}
