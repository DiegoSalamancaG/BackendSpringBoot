package proyectoEcommerce.mapper;

import org.mapstruct.Mapper;
import proyectoEcommerce.domain.Collection;
import proyectoEcommerce.dto.CollectionDTO;

@Mapper(componentModel = "spring")
public interface CollectionMapper {

    CollectionDTO toDto(Collection collection);
    Collection toEntity(CollectionDTO collectionDTO);



}
