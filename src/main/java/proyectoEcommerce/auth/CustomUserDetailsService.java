package proyectoEcommerce.auth;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import proyectoEcommerce.domain.User;
import proyectoEcommerce.repository.UserRepository;

/**
 * Servicio que implementa UserDetailsService para integrar la autenticación con Spring Security.
 * Busca al usuario por su email y lo transforma en un CustomUserDetail que Spring pueda usar.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Inyección de dependencias por constructor.
     * Spring inyectará automáticamente el UserRepository.
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Método requerido por UserDetailsService. Carga al usuario desde la base de datos
     * y lo convierte en una instancia de CustomUserDetail.
     *
     * @param username El nombre de usuario (o email en este caso)
     * @return Detalles del usuario autenticado
     * @throws UsernameNotFoundException si no se encuentra el usuario
     */
    @Override
    public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

            return new CustomUserDetail(user);

        } catch (Exception e) {
            // Logueo opcional si usas un logger
            throw new UsernameNotFoundException("Error al cargar usuario: " + e.getMessage(), e);
        }
    }
}
