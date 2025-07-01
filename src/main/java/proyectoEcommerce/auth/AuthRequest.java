package proyectoEcommerce.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para recibir las credenciales del usuario al hacer login.
 * Contiene el nombre de usuario y la contraseña.
 */
@Schema(description = "Solicitud de autenticación del usuario(login)")
public class AuthRequest {

    // Validación: no se permite que el campo esté vacío o en blanco
    @Schema(description = "Nombre de usuario registrado", example = "diego123")
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @Schema(description = "Contraseña del usuario", example = "12345abc")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public AuthRequest() {}

    // Getter y setter de password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter y setter de username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
