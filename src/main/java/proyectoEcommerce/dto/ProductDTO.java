package proyectoEcommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "DTO para crear o actualizar productos en el sistema")
public class ProductDTO {

    @Schema(description = "ID del producto (solo en respuestas)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre del producto", example = "Katana Hattori Hanzo", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @Schema(description = "Descripción del producto", example = "Katana forjada a mano por Hattori Hanzo con hoja de acero de alta calidad", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 1000, message = "La descripción debe tener entre 10 y 1000 caracteres")
    private String description;

    @Schema(description = "URL de la imagen del producto", example = "https://misitio.com/imagenes/katana.jpg")
    private String image;

    @Schema(description = "Stock disponible del producto", example = "20", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad no puede ser menor que 1")
    private Integer stock;

    @Schema(description = "Precio del producto", example = "150000", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "1.0", message = "El precio no puede ser menor que 1")
    private Double price;

    @Schema(description = "Indica si el producto está activo", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El estado activo es obligatorio")
    private Boolean isActive = true;

    @Schema(description = "ID de la categoría asociada", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La categoría es obligatoria")
    private Long categoryId;

    @Schema(description = "ID de la colección asociada (opcional)", example = "3")
    private Long collectionId;

    @Schema(description = "Nombre de la categoría (solo lectura)", example = "Armas", accessMode = Schema.AccessMode.READ_ONLY)
    private String categoryName;

    @Schema(description = "Nombre de la colección (solo lectura)", example = "Edición Clásica", accessMode = Schema.AccessMode.READ_ONLY)
    private String collectionName;

    public ProductDTO() {}

    public ProductDTO(String name, String description, String image, Integer stock, Double price, Boolean isActive, Long categoryId, Long collectionId) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.stock = stock;
        this.price = price;
        this.isActive = isActive;
        this.categoryId = categoryId;
        this.collectionId = collectionId;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Integer getStock() {
        return stock;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Double getPrice() {
        return price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }
}
