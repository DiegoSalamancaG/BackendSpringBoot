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
import proyectoEcommerce.dto.CollectionDTO;
import proyectoEcommerce.service.CollectionService;

import java.util.List;

@Tag(name = "Colecciones", description = "Endpoints para crear, buscar, eliminar y modificar colecciones")
@RestController
@RequestMapping("api/v1/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Operation(summary = "Crear nueva colección", description = "Crea una nueva colección y devuelve su información")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Colección creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CollectionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CollectionDTO> postCollection(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la colección a crear",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CollectionDTO.class))
            )
            @Valid @RequestBody CollectionDTO collectionDTO) {
        CollectionDTO c = collectionService.createCollection(collectionDTO);
        return ResponseEntity.status(201).body(c);
    }

    @Operation(summary = "Obtener todas las colecciones", description = "Devuelve una lista de todas las colecciones registradas")
    @ApiResponse(responseCode = "200", description = "Listado de colecciones obtenido correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CollectionDTO.class)))
    @GetMapping
    public ResponseEntity<List<CollectionDTO>> getAllCollections() {
        return ResponseEntity.ok(collectionService.getAllCollections());
    }

    @Operation(summary = "Obtener colección por ID", description = "Devuelve los datos de una colección específica por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Colección encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CollectionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Colección no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CollectionDTO> getCollectionById(
            @Parameter(description = "ID de la colección", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(collectionService.getCollectionById(id));
    }

    @Operation(summary = "Actualizar colección", description = "Modifica una colección existente por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Colección actualizada correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CollectionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Colección no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CollectionDTO> updateCollection(
            @Parameter(description = "ID de la colección", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody CollectionDTO collectionDTO) {
        return ResponseEntity.ok(collectionService.updateCollection(id, collectionDTO));
    }

    @Operation(summary = "Eliminar colección", description = "Elimina una colección por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Colección eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Colección no encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(
            @Parameter(description = "ID de la colección", example = "1")
            @PathVariable Long id) {
        collectionService.deleteCollection(id);
        return ResponseEntity.noContent().build();
    }
}
