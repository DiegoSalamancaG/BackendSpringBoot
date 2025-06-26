package proyectoEcommerce.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoEcommerce.dto.CollectionDTO;
import proyectoEcommerce.service.CollectionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping
    public ResponseEntity<CollectionDTO> postCollection(@Valid @RequestBody CollectionDTO collectionDTO){
        CollectionDTO c = collectionService.createCollection(collectionDTO);
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public ResponseEntity<List<CollectionDTO>> getAllCollections(){
        return ResponseEntity.ok(collectionService.getAllCollections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionDTO> getCollectionById(@PathVariable Long id) {
        return ResponseEntity.ok(collectionService.getCollectionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionDTO> updateCollection(@PathVariable Long id, @Valid @RequestBody CollectionDTO collectionDTO) {
        return ResponseEntity.ok(collectionService.updateCollection(id, collectionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        collectionService.deleteCollection(id);
        return ResponseEntity.noContent().build();
    }



}
