package proyectoEcommerce.service;

import org.springframework.stereotype.Service;
import proyectoEcommerce.domain.Collection;
import proyectoEcommerce.dto.CollectionDTO;
import proyectoEcommerce.mapper.CollectionMapper;
import proyectoEcommerce.repository.CollectionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final CollectionMapper collectionMapper;

    public CollectionServiceImpl(CollectionRepository collectionRepository, CollectionMapper collectionMapper) {
        this.collectionRepository = collectionRepository;
        this.collectionMapper = collectionMapper;
    }

    @Override
    public CollectionDTO createCollection(CollectionDTO collectionDTO) {
        Collection collection = collectionMapper.toEntity(collectionDTO);
        return collectionMapper.toDto(collectionRepository.save(collection));
    }

    @Override
    public List<CollectionDTO> getAllCollections() {
        return collectionRepository.findAll()
                .stream()
                .map(collectionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CollectionDTO getCollectionById(Long id) {
        Collection collection = collectionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Colección no encontrada"));
        return collectionMapper.toDto(collection);
    }

    @Override
    public CollectionDTO updateCollection(Long id, CollectionDTO collectionDTO) {
        Collection existCol = collectionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Colección no encontrada"));

        existCol.setName(collectionDTO.getName());
        existCol.setDescription(collectionDTO.getDescription());

        return collectionMapper.toDto(collectionRepository.save(existCol));
    }

    @Override
    public void deleteCollection(Long id) {
        if(!collectionRepository.existsById(id)){
            throw new RuntimeException("Colección no encontrada");
        }
        collectionRepository.deleteById(id);
    }
}
