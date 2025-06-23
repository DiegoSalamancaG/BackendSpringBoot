package proyectoEcommerce.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Clase encargada de generar, validar y extraer datos del token JWT.
 */
@Component
public class JwtUtil {

    // Clave secreta leída desde application.properties
    @Value("${jwt.secret}")
    private String jwtSecretKey;

    // Tiempo de expiración del token
    @Value("${jwt.expiration}")
    private long expirationTime;

    // Clave firmada con algoritmo HMAC
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
    }

    /**
     * Genera un token JWT válido por un tiempo definido.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrae el nombre de usuario desde el token.
     */
    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Valida que el token coincida con el usuario y no esté expirado.
     */
    public boolean validateToken(String token, CustomUserDetail userDetail) {
        String username = extractUsername(token);
        return username != null
                && username.equals(userDetail.getUsername())
                && !isExpired(token);
    }

    /**
     * Verifica si el token ya expiró.
     */
    private boolean isExpired(String token) {
        try {
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return true;
        }
    }
}
