package proyectoEcommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de transferencia de datos para operaciones con usuarios")
public class UserDTO {

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



    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getAdmin() { return admin; }
    public void setAdmin(Boolean admin) { this.admin = admin; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
