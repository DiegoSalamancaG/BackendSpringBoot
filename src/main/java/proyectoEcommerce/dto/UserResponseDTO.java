package proyectoEcommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de respuesta para datos públicos del usuario")
public class UserResponseDTO {

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

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Boolean getAdmin() { return admin; }
    public void setAdmin(Boolean admin) { this.admin = admin; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
