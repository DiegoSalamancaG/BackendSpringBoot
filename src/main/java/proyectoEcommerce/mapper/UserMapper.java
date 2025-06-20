package proyectoEcommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import proyectoEcommerce.domain.User;
import proyectoEcommerce.dto.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
    User UserDTOToUser(UserDTO userDTO);

    List<UserDTO> userToUserDTOs(List<User> user);
    List<User> userDTOToUsers(List<UserDTO> userDTO);

}
