package vn.edu.iuh.fit.rayarkshop.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionPasswordGenerator {

    public static String encrypt(String password) {
        BCryptPasswordEncoder encoder = getBCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        String password = "Thanhtam@101002";
        String encryptionPassword = encrypt(password);

        System.out.println("Password encrypted: " + encryptionPassword);
    }

}
