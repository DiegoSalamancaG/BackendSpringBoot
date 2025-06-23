package proyectoEcommerce.auth;

/**
 * DTO utilizado para devolver el token JWT al cliente
 * después de una autenticación exitosa.
 */
public class AuthResponse {

    private String token;

    /**
     * Constructor con el token.
     * @param token JWT generado
     */
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter del token
    public String getToken() {
        return token;
    }

    // Setter del token
    public void setToken(String token) {
        this.token = token;
    }
}
