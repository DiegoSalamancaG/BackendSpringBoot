package proyectoEcommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "DTO de respuesta para datos públicos del usuario")
public class UserResponseDTO {

    // Getters y Setters
    @Schema(description = "ID único del usuario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre completo del usuario", example = "Diego Salamanca")
    private String name;

    @Schema(description = "Correo electrónico del usuario", example = "diego@example.com")
    private String email;

    @Schema(description = "Nombre de usuario", example = "diego123")
    private String username;

    @Schema(description = "Indica si el usuario tiene permisos de administrador", example = "false")
    private Boolean admin;

    @Schema(description = "Indica si el usuario está activo", example = "true")
    private Boolean active;

    public UserResponseDTO() {}

}
