package pwr.project.ziwg.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pwr.project.ziwg.entity.User;
import pwr.project.ziwg.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class FirebaseAuthorizationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        FirebaseAuthentication firebaseAuthentication = (FirebaseAuthentication) authentication;
        User user = userRepository.getDocumentByUid(firebaseAuthentication.getToken().getUid());

        if(user == null){
            throw new UnknownUserException("Could not find user with ID: " + firebaseAuthentication.getToken().getUid());
        }

        return firebaseAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return FirebaseAuthentication.class.isAssignableFrom(authentication);
    }
}
