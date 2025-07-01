package proyectoEcommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoEcommerce.dto.CategoryDTO;
import proyectoEcommerce.service.CategoryService;

import java.util.List;

@Tag(name = "Categorías", description = "Endpoints para crear, buscar, eliminar y modificar categorías")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Crear una nueva categoría",description = "Crea una nueva categoría y devuelve la información de la categoría creada")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Categoría creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "400",description = "Datos inválidos",content = @Content)
    })
    @PostMapping
    public ResponseEntity<CategoryDTO> postCategory(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la Categoria a crear",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))
            )
            @Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO c = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(201).body(c);
    }

    @Operation(summary = "Obtener todas las categorías",description = "Devuelve una lista con todas las categorías registradas")
    @ApiResponse(responseCode = "200",description = "Lista de categorías obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDTO.class)))
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(summary = "Obtener una categoría por ID", description = "Devuelve los datos de una categoría específica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404",description = "Categoría no encontrada",content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(
            @Parameter(description = "ID de la categoría", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @Operation(summary = "Actualizar una categoría",
            description = "Modifica una categoría existente por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @Parameter(description = "ID de la categoría", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría existente por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "ID de la categoría", example = "1")
            @PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
