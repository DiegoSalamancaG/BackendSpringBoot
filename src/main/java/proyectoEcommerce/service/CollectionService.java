package proyectoEcommerce.service;

import proyectoEcommerce.dto.CollectionDTO;

import java.util.List;

public interface CollectionService {

    CollectionDTO createCollection(CollectionDTO collectionDTO);
    List<CollectionDTO> getAllCollections();
    CollectionDTO getCollectionById(Long id);
    CollectionDTO updateCollection(Long id, CollectionDTO collectionDTO);
    void deleteCollection(Long id);
}
