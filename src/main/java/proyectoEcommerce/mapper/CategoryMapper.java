package proyectoEcommerce.mapper;


import org.mapstruct.Mapper;
import proyectoEcommerce.domain.Category;
import proyectoEcommerce.dto.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);
    Category toEntity(CategoryDTO categoryDTO);
}
