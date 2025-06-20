package proyectoEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoEcommerce.domain.User;

import java.util.List;
import java.util.Optional;

//USerRepository para realizar consultas personalizadas
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findByActiveTrue();

}
