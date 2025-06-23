package proyectoEcommerce.auth;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para recibir las credenciales del usuario al hacer login.
 * Contiene el nombre de usuario y la contraseña.
 */
public class AuthRequest {

    // Validación: no se permite que el campo esté vacío o en blanco
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

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
