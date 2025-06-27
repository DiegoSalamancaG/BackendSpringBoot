package proyectoEcommerce.auth;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO utilizado para devolver el token JWT al cliente
 * después de una autenticación exitosa.
 */
@Schema(description = "Respuesta con el token JWT luego de una autenticación exitosa")
public class AuthResponse {

    @Schema(description = "Token JWT generado para autenticación", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }
}
