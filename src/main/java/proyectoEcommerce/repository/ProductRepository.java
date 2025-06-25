package proyectoEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoEcommerce.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
