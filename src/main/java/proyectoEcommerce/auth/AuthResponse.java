package proyectoEcommerce.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO utilizado para devolver el token JWT al cliente
 * después de una autenticación exitosa.
 */
@Setter
@Getter
@Schema(description = "Respuesta con el token JWT luego de una autenticación exitosa")
public class AuthResponse {

    @Schema(description = "Token JWT generado para autenticación", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

}
