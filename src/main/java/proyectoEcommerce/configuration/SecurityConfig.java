package proyectoEcommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import proyectoEcommerce.auth.CustomUserDetailsService;
import proyectoEcommerce.auth.JwtAuthFilter;
import proyectoEcommerce.repository.UserRepository;

/**
 * Clase de configuración principal de seguridad para la aplicación.
 * Configura la seguridad HTTP, autenticación JWT, codificador de contraseñas
 * y la política de sesiones.
 */
@Configuration
@EnableWebSecurity // Habilita la seguridad web de Spring Security
@EnableMethodSecurity // Permite usar anotaciones como @PreAuthorize en los controladores
public class SecurityConfig {

    /**
     * Bean para codificar contraseñas con BCrypt.
     * Es usado automáticamente por Spring Security en los procesos de autenticación.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean de servicio personalizado para cargar los detalles del usuario.
     * Se usa para buscar usuarios por email o username desde la base de datos.
     *
     * @param userRepository Repositorio de usuarios inyectado por Spring
     * @return instancia del servicio de detalles de usuario
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }

    /**
     * Configura el filtro de seguridad principal para la aplicación.
     * - Desactiva CSRF (ya que es una API REST).
     * - Establece política de sesión como STATELESS (sin sesiones).
     * - Permite acceso libre a rutas bajo /api/v1/auth/**
     * - Requiere autenticación para todas las demás rutas.
     * - Agrega el filtro JWT personalizado antes del filtro por defecto de autenticación.
     *
     * @param http instancia de HttpSecurity proporcionada por Spring
     * @param jwtFilter filtro personalizado que valida el JWT en las peticiones
     * @return SecurityFilterChain configurado
     * @throws Exception si ocurre un error de configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtFilter) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // No se usa CSRF en APIs REST
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin sesiones
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll() // Permitir acceso sin autenticación a rutas públicas
                        .anyRequest().authenticated() // El resto requiere autenticación
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Colocar filtro JWT antes del filtro estándar
                .build();
    }

    /**
     * Bean que expone el AuthenticationManager para usar en el proceso de login.
     *
     * @param authConfig configuración de autenticación de Spring
     * @return AuthenticationManager configurado
     * @throws Exception si ocurre un error
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
