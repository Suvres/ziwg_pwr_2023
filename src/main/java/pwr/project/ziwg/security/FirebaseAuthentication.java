package pwr.project.ziwg.security;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import pwr.project.ziwg.entity.User;

import java.util.Collection;
import java.util.List;

public class FirebaseAuthentication extends AbstractAuthenticationToken {
    private FirebaseToken user;
    public FirebaseAuthentication(FirebaseToken user) {
        super(List.of());
        this.user = user;
    }
    public FirebaseAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    public FirebaseToken getToken() {
        return user;
    }

}
