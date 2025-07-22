package proyectoEcommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Objeto de transferencia de datos para operaciones con usuarios")
public class UserDTO {

    // Getters y Setters
    @Schema(description = "Nombre completo del usuario", example = "Diego Salamanca", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Nombre de usuario único para iniciar sesión", example = "diego123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "Correo electrónico válido del usuario", example = "diego@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Contraseña del usuario (mínimo 6 caracteres)", example = "MiPassSegura123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "Define si el usuario tiene rol de administrador", example = "false")
    private Boolean admin;

    @Schema(description = "Indica si la cuenta del usuario está activa", example = "true")
    private Boolean active;


}
