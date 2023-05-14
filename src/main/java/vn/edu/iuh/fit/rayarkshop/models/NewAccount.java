package vn.edu.iuh.fit.rayarkshop.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class NewAccount {

    private String localId;
    private String email;
    private boolean emailVerified;
    private String displayName;
    private String photoUrl;
    private String passwordHash;
    private Object passwordUpdatedAt;
    private long validSince;
    private boolean disabled;
    private long lastLoginAt;
    private long createdAt;
    private boolean customAuth;
    private Map<String, Object> roles;
    private String idToken;

    @Override
    public String toString() {
        return "NewAccount{" +
                "localId='" + localId + '\'' +
                ", email='" + email + '\'' +
                ", emailVerified=" + emailVerified +
                ", displayName='" + displayName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", passwordUpdatedAt=" + passwordUpdatedAt +
                ", validSince=" + validSince +
                ", disabled=" + disabled +
                ", lastLoginAt=" + lastLoginAt +
                ", createdAt=" + createdAt +
                ", customAuth=" + customAuth +
                ", roles=" + roles +
                ", idToken='" + idToken + '\'' +
                '}';
    }
}
