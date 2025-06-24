package ec.edu.uce.cati.repository.model.properties;

import org.bson.types.ObjectId;
import java.util.List;

public class Software extends IntellectualPropertyDetails {
    public String name;
    public String version;
    public List<String> programming_languages;
    public String web_page_url;
    public List<ObjectId> image_gallery; // Lista de IDs de la colección 'images'
    public String license_type;
    public List<String> supported_platforms;
}