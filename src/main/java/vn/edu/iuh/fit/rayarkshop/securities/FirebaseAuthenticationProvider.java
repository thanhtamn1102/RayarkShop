package vn.edu.iuh.fit.rayarkshop.securities;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.NewAccount;
import vn.edu.iuh.fit.rayarkshop.services.FirebaseAuthenticationService;
import vn.edu.iuh.fit.rayarkshop.utils.UUIDGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private FirebaseAuthenticationService firebaseAuthenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            NewAccount account = firebaseAuthenticationService.loginWithEmailAndPassword(email, password);

            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            if (account.getRoles() != null) {
                for (String role : account.getRoles().keySet()) {
                    GrantedAuthority authority = new SimpleGrantedAuthority(role);
                    authorities.add(authority);
                }
            }

            return new UsernamePasswordAuthenticationToken(account.getLocalId(), password, authorities);

        } catch (FirebaseAuthException e) {
            throw new BadCredentialsException("Invalid email or password");
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Invalid token");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
