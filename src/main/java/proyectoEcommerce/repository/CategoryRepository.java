package proyectoEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoEcommerce.domain.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
