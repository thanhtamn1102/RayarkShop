package vn.edu.iuh.fit.rayarkshop.utils;

import java.util.UUID;

public class UUIDGenerator {

    public static String generate() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }

}
