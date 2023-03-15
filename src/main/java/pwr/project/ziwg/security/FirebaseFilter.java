package pwr.project.ziwg.security;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.cloud.FirestoreClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pwr.project.ziwg.repository.Firebase;
import pwr.project.ziwg.repository.UserRepository;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
@Slf4j
public class FirebaseFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authKey = request.getHeader("X-Authorization");
        if (authKey == null) {
           throw new AccessDeniedException("Niepoprawny token");
        }

        FirebaseToken firebaseToken = getFirebaseUserByToken(authKey);

        Authentication authentication = new FirebaseAuthentication(firebaseToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }


    private FirebaseToken getFirebaseUserByToken(String authToken) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        try {
            return auth.verifyIdToken(authToken);
        } catch (FirebaseAuthException e) {
            log.warn(String.format("Problem z tokenem: %s", e.getMessage()));
            throw new SecurityException(e);
        }
    }
}
