package proyectoEcommerce.configuration;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth==null || !auth.isAuthenticated()){
            return Optional.of("System");
        }
        return Optional.of(auth.getName());
    }
}
