package ec.edu.uce.cati.repository.model;

import org.bson.types.ObjectId;
import java.util.List;

public class Brand {
    public String name;
    public ObjectId image; // Referencia a Image
    public String slogan;
    public List<String> colors;
} 