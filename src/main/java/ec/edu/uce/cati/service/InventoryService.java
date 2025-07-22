package ec.edu.uce.cati.service;

import ec.edu.uce.cati.repository.model.IntellectualPropertyPublic;
import ec.edu.uce.cati.repository.InventoryRepository;
import ec.edu.uce.cati.repository.dto.IntellectualPropertyPublicDTO;
import ec.edu.uce.cati.service.mapper.InventoryMapper;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.time.Instant;
import java.util.Date;

@ApplicationScoped
public class InventoryService {

    @Inject
    InventoryRepository inventoryRepository;

    /**
     * Obtiene todas las propiedades como un stream de datos.
     *
     * @return un Multi que emitirá cada propiedad.
     */
    public Multi<IntellectualPropertyPublicDTO> getAll() {
        return inventoryRepository.streamAll().onItem().transform(InventoryMapper::entityToDto);
    }

    /**
     * Busca una propiedad por su ID de forma reactiva.
     *
     * @param id El ID del documento.
     * @return Un Uni que contendrá la entidad o un fallo si no se encuentra.
     */
    public Uni<IntellectualPropertyPublicDTO> getById(String id) {
        return inventoryRepository.findById(new ObjectId(id))
                .onItem().ifNull().failWith(() -> new NotFoundException("No se encontró la propiedad con ID: " + id))
                .onItem().transform(InventoryMapper::entityToDto);
    }

    /**
     * Crea una nueva propiedad de forma reactiva.
     *
     * @param ip La entidad a crear.
     * @return Un Uni que contendrá la entidad creada.
     */
    public Uni<IntellectualPropertyPublicDTO> create(IntellectualPropertyPublicDTO dto) {
        if (dto.type == null || dto.type.isBlank()) {
            return Uni.createFrom().failure(new IllegalArgumentException("El campo 'type' es obligatorio."));
        }
        if (dto.details == null) {
            return Uni.createFrom().failure(new IllegalArgumentException("El campo 'details' es obligatorio."));
        }
        IntellectualPropertyPublic entity = InventoryMapper.dtoToEntity(dto);
        entity.id = new ObjectId();
        entity.approvalDate = Date.from(Instant.now());
        return inventoryRepository.persist(entity)
                .onItem().transform(v -> InventoryMapper.entityToDto(entity));
    }

    /**
     * Elimina una propiedad de forma reactiva.
     *
     * @param id El ID del documento.
     * @return Un Uni<Boolean> que será 'true' si se borró, 'false' si no.
     */
    public Uni<Boolean> deleteById(String id) {
        return inventoryRepository.deleteById(new ObjectId(id));
    }

    /**
     * Busca propiedades por su tipo (e.g., "patent", "brand").
     * Delega la llamada al método personalizado del repositorio.
     *
     * @param type El tipo a buscar.
     * @return Un Multi que emitirá las propiedades que coincidan.
     */
    public Multi<IntellectualPropertyPublicDTO> findByType(String type) {
        return inventoryRepository.findByType(type).onItem().transform(InventoryMapper::entityToDto);
    }
}