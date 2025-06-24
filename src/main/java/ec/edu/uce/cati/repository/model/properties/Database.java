package ec.edu.uce.cati.repository.model.properties;

import org.bson.types.ObjectId;
import java.util.List;

public class Database extends IntellectualPropertyDetails {
    public String name;
    public String description;
    // ID que referencia a un documento en la colección 'attachments'
    public ObjectId sample_data;
    public String access_type; // e.g., "Público", "Privado", "Restringido"
    public List<String> technologies_used; // e.g., "MongoDB", "MySQL"
    public List<String> data_categories;
}