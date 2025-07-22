package ec.edu.uce.cati.repository.model;

import org.bson.types.ObjectId;
import java.util.List;

public class Database {
    public String name;
    public String description;
    public ObjectId sampleData; // Referencia a Attachment
    public String accessType;
    public List<String> technologiesUsed;
    public List<String> dataCategories;
} 