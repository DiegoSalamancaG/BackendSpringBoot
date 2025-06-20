package proyectoEcommerce.service;

import proyectoEcommerce.dto.UserDTO;
import proyectoEcommerce.dto.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponseDTO> getUsers();
    Optional<UserResponseDTO> getUserById(Long id);
    Optional<UserResponseDTO> postUser(UserDTO dto);
    Optional<UserResponseDTO> updateUser(Long id, UserDTO dto);
    Boolean deleteUser(Long id);

}
