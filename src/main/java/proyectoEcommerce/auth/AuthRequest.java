package proyectoEcommerce.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO utilizado para recibir las credenciales del usuario al hacer login.
 * Contiene el nombre de usuario y la contraseña.
 */
@Setter
@Getter
@Schema(description = "Solicitud de autenticación del usuario(login)")
public class AuthRequest {

    // Getter y setter de username
    // Validación: no se permite que el campo esté vacío o en blanco
    @Schema(description = "Nombre de usuario registrado", example = "diego123")
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    // Getter y setter de password
    @Schema(description = "Contraseña del usuario", example = "12345abc")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public AuthRequest() {}

}
