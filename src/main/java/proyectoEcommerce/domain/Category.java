package proyectoEcommerce.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import proyectoEcommerce.utils.Auditable;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
    private String description;

    public Category(){}

    public Category(Long id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }
}
