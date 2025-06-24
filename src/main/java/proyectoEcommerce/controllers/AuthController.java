package proyectoEcommerce.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import proyectoEcommerce.auth.*;
import proyectoEcommerce.domain.User;
import proyectoEcommerce.dto.UserDTO;
import proyectoEcommerce.repository.UserRepository;

/**
 * Controlador REST responsable de manejar autenticación y registro de usuarios.
 * Expuesto en la ruta /api/v1/auth
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor con inyección de dependencias necesarias para el proceso de autenticación.
     */
    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          CustomUserDetailsService userDetailsService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Endpoint para iniciar sesión.
     * Verifica las credenciales del usuario, y si son válidas, genera un token JWT.
     *
     * @param req contiene username y password
     * @return JWT en caso de éxito, o error 401 si las credenciales son inválidas
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            // Se intenta autenticar con las credenciales provistas
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );
        } catch (Exception e) {
            // Si la autenticación falla, se retorna un error 401
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        // Si la autenticación fue exitosa, se genera el token JWT
        CustomUserDetail user = userDetailsService.loadUserByUsername(req.getUsername());
        String token = jwtUtil.generateToken(user.getUsername());

        System.out.println("Token: " + token); // Solo para depuración (puedes eliminar esto en producción)

        return ResponseEntity.ok(new AuthResponse(token)); // Se retorna el token al cliente
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     * Valida que el username no esté en uso y guarda el nuevo usuario en la base de datos.
     *
     * @param req objeto DTO con los datos del nuevo usuario
     * @return respuesta indicando éxito o error
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO req) {
        // Validación: evitar usuarios duplicados por username
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuario ya existe!");
        }

        // Se construye el nuevo usuario desde el DTO
        User newUser = new User();
        newUser.setName(req.getName());
        newUser.setUsername(req.getUsername());
        newUser.setEmail(req.getEmail());

        // La contraseña se codifica para no guardarla en texto plano
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));

        // Se establecen los estados administrativos y de activación
        newUser.setAdmin(req.getAdmin());
        newUser.setActive(req.getActive());

        // Persistencia del nuevo usuario
        userRepository.save(newUser);

        return ResponseEntity.status(201).body("Usuario creado exitosamente!");
    }

}
