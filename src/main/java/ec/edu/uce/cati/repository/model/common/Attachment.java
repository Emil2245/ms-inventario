package ec.edu.uce.cati.repository.model.common;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

/**
 * Entidad que representa un archivo adjunto (e.g., un PDF, un documento).
 * Tendrá su propia colección llamada 'attachments'.
 * Se usa para almacenar metadatos de los archivos subidos a MinIO.
 */
@MongoEntity(collection = "attachments")
public class Attachment extends PanacheMongoEntity {
    // PanacheMongoEntity ya nos proporciona un campo 'id' de tipo ObjectId.

    /**
     * URL directa para acceder o descargar el archivo (probablemente desde MinIO).
     */
    public String fileUrl;

    /**
     * Nombre original del archivo (e.g., "documento_tecnico.pdf").
     */
    public String fileName;

    /**
     * Tipo MIME del archivo (e.g., "application/pdf", "image/jpeg").
     */
    public String mimeType;

    /**
     * Tamaño del archivo en bytes.
     */
    public Long fileSize;
}