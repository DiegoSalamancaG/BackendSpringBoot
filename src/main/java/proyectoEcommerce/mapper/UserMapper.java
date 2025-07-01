package proyectoEcommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import proyectoEcommerce.domain.User;
import proyectoEcommerce.dto.UserDTO;
import proyectoEcommerce.dto.UserResponseDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Convertir entidad a respuesta DTO (GET /usuarios)
    UserResponseDTO toUserResponseDTO(User user);

    // DTO de entrada a entidad (para métodos POST o PUT)
    @Mapping(target = "id", ignore = true)
    User toEntity(UserDTO dto);

    // Actualizar un usuario existente con datos del DTO (ej: PUT /usuarios/{id})
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true) // evitar sobrescribir el password
    void updatefromDto(UserDTO dto, @MappingTarget User user);

}
