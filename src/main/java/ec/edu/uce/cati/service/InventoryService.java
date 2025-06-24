package ec.edu.uce.cati.service;

import ec.edu.uce.cati.repository.model.IntellectualPropertyPublic;
import ec.edu.uce.cati.repository.InventoryRepository;
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
    public Multi<IntellectualPropertyPublic> getAll() {
        return inventoryRepository.streamAll();
    }

    /**
     * Busca una propiedad por su ID de forma reactiva.
     *
     * @param id El ID del documento.
     * @return Un Uni que contendrá la entidad o un fallo si no se encuentra.
     */
    public Uni<IntellectualPropertyPublic> getById(String id) {
        return inventoryRepository.findById(new ObjectId(id))
                // Si el item es null (no encontrado), cambiamos el Uni a un fallo.
                .onItem().ifNull().failWith(() -> new NotFoundException("No se encontró la propiedad con ID: " + id));
    }

    /**
     * Crea una nueva propiedad de forma reactiva.
     *
     * @param ip La entidad a crear.
     * @return Un Uni que contendrá la entidad creada.
     */
    public Uni<IntellectualPropertyPublic> create(IntellectualPropertyPublic ip) {
        // Lógica de validación (sigue siendo síncrona y rápida)
        if (ip.type == null || ip.type.isBlank()) {
            return Uni.createFrom().failure(new IllegalArgumentException("El campo 'type' es obligatorio."));
        }
        if (ip.details == null) {
            return Uni.createFrom().failure(new IllegalArgumentException("El campo 'details' es obligatorio."));
        }

        // Lógica de enriquecimiento
        ip.id = new ObjectId();
        ip.approval_date = Date.from(Instant.now());

        // La operación de persistencia devuelve un Uni<Void> (un Uni que no contiene valor, solo indica que terminó).
        // Lo transformamos para que devuelva la entidad creada.
        return inventoryRepository.persist(ip)
                .onItem().transform(v -> ip); // Cuando persist() termine, devuelve el objeto 'ip'.
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
    public Multi<IntellectualPropertyPublic> findByType(String type) {
        return inventoryRepository.findByType(type);
    }
}