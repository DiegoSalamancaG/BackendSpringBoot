package proyectoEcommerce.service;

import proyectoEcommerce.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO postProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    //List<ProductDTO> getFilteredProducts(Long categoryId, Long collectionId);
}
