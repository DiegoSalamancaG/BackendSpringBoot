package proyectoEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoEcommerce.domain.Product;
import proyectoEcommerce.dto.ProductDTO;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<ProductDTO> findByIsActiveTrue();
    List<ProductDTO> findByCategoryIdAndIsActiveTrue(Long categoryId);
    List<ProductDTO> findByCollectionIdAndIsActiveTrue(Long collectionId);
    List<ProductDTO> findByCategoryIdAndCollectionIdAndIsActiveTrue(Long categoryId, Long collectionId);
}
