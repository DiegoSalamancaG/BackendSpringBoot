package proyectoEcommerce.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import proyectoEcommerce.utils.Auditable;

@Setter
@Getter
@Entity
@Table(name = "collections")
public class Collection extends Auditable {

    //Setters
    //Getters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la colección es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
    private String description;

    public Collection() {
    }

    public Collection(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
