package proyectoEcommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

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

@Tag(name = "Autenticación", description = "Endpoints para login y registro de usuarios")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica al usuario y devuelve un token JWT en caso de éxito",
            requestBody = @RequestBody(
                    description = "Credenciales del usuario",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AuthRequest.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticación exitosa",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Credenciales inválidas",
                            content = @Content(schema = @Schema(example = "Credenciales inválidas"))
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        CustomUserDetail user = userDetailsService.loadUserByUsername(req.getUsername());
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Operation(
            summary = "Registro de nuevo usuario",
            description = "Crea un nuevo usuario en la base de datos",
            requestBody = @RequestBody(
                    description = "Datos del usuario a registrar",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "El usuario ya existe")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuario ya existe!");
        }

        User newUser = new User();
        newUser.setName(req.getName());
        newUser.setUsername(req.getUsername());
        newUser.setEmail(req.getEmail());
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));
        newUser.setAdmin(req.getAdmin());
        newUser.setActive(req.getActive());

        userRepository.save(newUser);
        return ResponseEntity.status(201).body("Usuario creado exitosamente!");
    }
}
