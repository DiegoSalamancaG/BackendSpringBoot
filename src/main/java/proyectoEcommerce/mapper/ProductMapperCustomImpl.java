package proyectoEcommerce.mapper;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import proyectoEcommerce.domain.Category;
import proyectoEcommerce.domain.Collection;
import proyectoEcommerce.domain.Product;
import proyectoEcommerce.dto.ProductDTO;
import proyectoEcommerce.repository.CategoryRepository;
import proyectoEcommerce.repository.CollectionRepository;

@Component
@Primary
public class ProductMapperCustomImpl implements  ProductMapper{

    private final CategoryRepository categoryRepository;
    private final CollectionRepository collectionRepository;

    public ProductMapperCustomImpl(CategoryRepository categoryRepository,
                                   CollectionRepository collectionRepository) {
        this.categoryRepository = categoryRepository;
        this.collectionRepository = collectionRepository;
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

        //Asignamos el nombre de la categoria
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());

        //Asignamos el nombre de la colección
        dto.setCollectionId(product.getCollection().getId());
        dto.setCollectionName(product.getCollection().getName());

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
                .orElseThrow(()->new RuntimeException("Categoría no encontrada con ID: " + dto.getCategoryId()));
        product.setCategory(cat);
        //Collection
        Collection col = collectionRepository.findById(dto.getCollectionId())
                .orElseThrow(()->new RuntimeException("Colección no encontrada con ID: " + dto.getCollectionId()));
        product.setCollection(col);

        return product;
    }
}
