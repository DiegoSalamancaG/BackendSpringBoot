package proyectoEcommerce.service;

import org.springframework.stereotype.Service;
import proyectoEcommerce.domain.Product;
import proyectoEcommerce.dto.ProductDTO;
import proyectoEcommerce.mapper.ProductMapper;
import proyectoEcommerce.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO postProduct(ProductDTO productDTO){
        Product p = productMapper.toEntity(productDTO);
        return productMapper.toDto(productRepository.save(p));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
        return  productMapper.toDto(p);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product p = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado"));

        p.setName(productDTO.getName());
        p.setDescription(productDTO.getDescription());
        p.setImage(productDTO.getImage());
        p.setStock(productDTO.getStock());
        p.setPrice(productDTO.getPrice());
        p.setActive(productDTO.getActive());

        return productMapper.toDto(productRepository.save(p));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)){
            throw new RuntimeException("Producto no encontrado");
        }
        productRepository.deleteById(id);
    }

    /*
    @Override
    public List<ProductDTO> getFilteredProducts(Long categoryId, Long collectionId) {
        List<Product> products;
        if (categoryId != null && collectionId != null) {
            new Product = productRepository.findByCategoryIdAndCollectionIdAndIsActiveTrue(categoryId, collectionId);
        } else if (categoryId != null) {
            products = productRepository.findByCategoryIdAndIsActiveTrue(categoryId);
        } else if (collectionId != null) {
            products = productRepository.findByCollectionIdAndIsActiveTrue(collectionId);
        } else {
            products = productRepository.findByIsActiveTrue(); // Todos activos
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    } */
}
