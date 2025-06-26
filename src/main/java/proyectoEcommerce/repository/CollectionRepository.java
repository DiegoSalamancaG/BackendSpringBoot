package proyectoEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoEcommerce.domain.Collection;

public interface CollectionRepository extends JpaRepository<Collection,Long> {

}
