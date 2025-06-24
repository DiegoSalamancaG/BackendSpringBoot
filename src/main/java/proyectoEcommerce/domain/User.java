package proyectoEcommerce.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import proyectoEcommerce.utils.Auditable;

@Entity
@Table(name = "users")
public class User extends Auditable {

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

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActive(){ return active;}

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
