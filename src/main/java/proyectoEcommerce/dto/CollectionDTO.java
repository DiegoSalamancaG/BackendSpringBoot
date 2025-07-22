package proyectoEcommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CollectionDTO {

    // Getters y setters
    private Long id;

    @Schema(description = "Nombre de la Colección")
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @Schema(description = "Descripción de la Colección")
    @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
    private String description;

    public CollectionDTO() {
    }

    public CollectionDTO(String description, String name, Long id) {
        this.description = description;
        this.name = name;
        this.id = id;
    }

}
