package proyectoEcommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoEcommerce.dto.CategoryDTO;
import proyectoEcommerce.service.CategoryService;

import java.util.List;

@Tag(name = "Categorias",description = "Endpoints para el crear, buscar, eliminar y modificar Categorias")
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
    @Operation(
            summary = "Crear Categoria",
            description = "Crea una Categoria y devuelve la Categoria creada",
            requestBody = @RequestBody(
                    description = "Categoria",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "",
                            content = @Content(schema = @Schema(implementation = CategoryDTO.class))
                    )
            }
    )*/
    @PostMapping
    public ResponseEntity<CategoryDTO> postCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO c = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(c);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
