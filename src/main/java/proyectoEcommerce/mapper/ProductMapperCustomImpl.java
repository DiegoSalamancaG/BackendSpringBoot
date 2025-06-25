package proyectoEcommerce.mapper;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import proyectoEcommerce.domain.Category;
import proyectoEcommerce.domain.Product;
import proyectoEcommerce.dto.ProductDTO;
import proyectoEcommerce.repository.CategoryRepository;

@Component
@Primary
public class ProductMapperCustomImpl implements  ProductMapper{

    private final CategoryRepository categoryRepository;

    public ProductMapperCustomImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO toDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());
        dto.setActive(product.getActive());
        dto.setCategoryId(product.getCategory().getId());

        return dto;
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());
        product.setActive(dto.getActive());

        //Category
        Category cat = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()->new RuntimeException("Categoria no encontrada"));

        product.setCategory(cat);

        return product;
    }
}
