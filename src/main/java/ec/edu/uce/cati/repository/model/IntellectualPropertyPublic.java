package ec.edu.uce.cati.repository.model;

import ec.edu.uce.cati.repository.model.properties.IntellectualPropertyDetails;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 * Entidad principal que representa una propiedad intelectual en el catálogo público.
 * NOTA: Esta clase ahora extiende PanacheMongoEntityBase, lo que significa
 * que no tiene los métodos de acceso a datos (como .persist()) directamente.
 * Esos métodos estarán en el Repository.
 */
@MongoEntity(collection = "intellectual_property_public")
public class IntellectualPropertyPublic extends PanacheMongoEntityBase { // <-- ¡CAMBIO IMPORTANTE! Se cambia la clase base.

    /**
     * El identificador único de MongoDB para este documento.
     * Al usar el patrón Repository, es una buena práctica declarar explícitamente el campo 'id'.
     * La anotación @BsonId le indica a MongoDB que este es el campo clave (_id).
     */
    @BsonId
    public ObjectId id;

    /**
     * ID de la imagen de vista previa, que se relaciona con un documento en la colección 'images'.
     */
    public ObjectId imageId;

    /**
     * Tipo de propiedad intelectual (e.g., "marca", "patente", "software").
     * Ayuda a filtrar y a entender qué tipo de objeto se encuentra en 'details'.
     */
    public String type;

    /**
     * Clasificación internacional (e.g., Clasificación de Niza para marcas).
     */
    public String international_class;

    /**
     * Nombre del propietario o titular de la propiedad intelectual.
     */
    public String owner;

    /**
     * Lista de IDs de los autores, que se relacionan con una colección de usuarios/autores.
     */
    public List<ObjectId> authors;

    /**
     * Etiquetas o palabras clave para facilitar la búsqueda.
     */
    public List<String> tags;

    /**
     * Fecha en que la propiedad fue aprobada o registrada.
     */
    public Date approval_date;

    /**
     * Campo polimórfico que contiene los detalles específicos de cada tipo de propiedad.
     * Gracias a las anotaciones @JsonTypeInfo y @JsonSubTypes en la clase base
     * 'IntellectualPropertyDetails', Jackson y Panache sabrán cómo manejarlo.
     */
    public IntellectualPropertyDetails details;

    // Los constructores, getters y setters son opcionales con los campos públicos de Panache,
    // pero puedes añadirlos si tu equipo sigue esa convención o si necesitas lógica en ellos.
}