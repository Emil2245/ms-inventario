package ec.edu.uce.cati.repository;

import ec.edu.uce.cati.repository.model.IntellectualPropertyPublic;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Multi;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class InventoryRepository implements ReactivePanacheMongoRepository<IntellectualPropertyPublic> {

    /**
     * Busca todas las propiedades de un tipo específico.
     * El método `find()` devuelve un ReactivePanacheQuery.
     * Usamos `.list()` que devuelve un Uni<List<T>> y lo transformamos en un Multi<T>.
     *
     * @param type El tipo a buscar (e.g., "marca", "patente").
     * @return Un Multi que emitirá las propiedades que coincidan.
     */
    public Multi<IntellectualPropertyPublic> findByType(String type) {
        // Usamos find().list() y transformamos el Uni<List> a un Multi
        return find("type", type)
                .list()
                .onItem().transformToMulti(list -> Multi.createFrom().iterable(list));
    }

    /**
     * Busca por propietario y ordena por fecha.
     *
     * @param owner El nombre del propietario.
     * @return Un Multi que emitirá las propiedades ordenadas.
     */
    public Multi<IntellectualPropertyPublic> findByOwnerSortedByDate(String owner) {
        Sort sort = Sort.by("approval_date", Sort.Direction.Descending);
        return find("owner", sort, owner)
                .list()
                .onItem().transformToMulti(list -> Multi.createFrom().iterable(list));
    }
}
