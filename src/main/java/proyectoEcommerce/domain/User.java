package proyectoEcommerce.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import proyectoEcommerce.utils.Auditable;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends Auditable {

    // Getters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 30)
    @Column(unique = true)
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    @NotNull(message = "El campo admin no puede estar vacío")
    private Boolean admin = false;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    @NotNull(message = "El estado activo es obligatorio")
    private Boolean active = true;

    // Constructor vacío
    public User() {}

    // Constructor
    public User(String name, String username, String password, String email, Boolean admin, Boolean active) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.active = active;
    }
}
