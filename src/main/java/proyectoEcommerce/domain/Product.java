package proyectoEcommerce.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import proyectoEcommerce.utils.Auditable;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 1000, message = "La descripción debe tener entre 10 y 1000 caracteres")
    private String description;

    private String image;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad no puede ser menor que 1")
    private Integer stock;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "1.0", message = "El precio no puede ser menor que 1")
    private Double price;

    @NotNull(message = "El estado activo es obligatorio")
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive = true;

    //Category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    //Collection
    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    public Product() {}

    public Product(Boolean isActive, String name, String description, String image, Integer stock, Double price, Category category, Collection collection) {
        this.isActive = isActive;
        this.name = name;
        this.description = description;
        this.image = image;
        this.stock = stock;
        this.price = price;
        this.category = category;
        this.collection = collection;
    }
}
