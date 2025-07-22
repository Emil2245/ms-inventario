package ec.edu.uce.cati.repository.model;

import org.bson.types.ObjectId;
import java.util.List;

public class Software {
    public String name;
    public List<String> programmingLanguages;
    public String webPageUrl;
    public List<ObjectId> imageGallery; // Referencia a Image
    public String licenseType;
    public List<String> supportedPlatforms;
} 