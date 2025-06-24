package ec.edu.uce.cati.repository.model.properties;

import org.bson.types.ObjectId;
import java.util.List;

// No lleva @MongoEntity porque se embeberá dentro de IntellectualPropertyPublic
public class Brand extends IntellectualPropertyDetails {
    public String name;
    public ObjectId imageId; // ID que referencia a un documento en la colección 'images'
    public String slogan;
    public List<String> colors; // Lista de colores en formato hexadecimal, por ejemplo.
}