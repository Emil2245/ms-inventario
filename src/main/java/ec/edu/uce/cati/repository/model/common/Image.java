package ec.edu.uce.cati.repository.model.common;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

/**
 * Entidad que representa una imagen en la base de datos.
 * Tendrá su propia colección llamada 'images'.
 * Se usa para almacenar metadatos de las imágenes subidas a MinIO.
 */
@MongoEntity(collection = "images")
public class Image extends PanacheMongoEntity {
    // PanacheMongoEntity ya nos proporciona un campo 'id' de tipo ObjectId.

    /**
     * Formato del archivo de imagen (e.g., "jpeg", "png", "webp").
     */
    public String format;

    /**
     * Objeto embebido que contiene las URLs a las diferentes versiones de la imagen.
     */
    public Urls urls;

    /**
     * Texto alternativo para accesibilidad (buena práctica).
     */
    public String altText;
}