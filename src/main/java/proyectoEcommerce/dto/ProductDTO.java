package proyectoEcommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.*;

public class ProductDTO {

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
    @NotNull(message = "La categoría es obligatoria")
    private Long categoryId;

    public ProductDTO(){}

    public ProductDTO(String name, String description, String image, Integer stock, Double price, Boolean isActive, Long categoryId) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.stock = stock;
        this.price = price;
        this.isActive = isActive;
        this.categoryId = categoryId;
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
}
