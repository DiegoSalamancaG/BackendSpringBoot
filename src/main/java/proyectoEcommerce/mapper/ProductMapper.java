package proyectoEcommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import proyectoEcommerce.domain.Product;
import proyectoEcommerce.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
}
