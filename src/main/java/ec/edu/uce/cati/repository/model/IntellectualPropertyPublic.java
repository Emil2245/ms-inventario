package ec.edu.uce.cati.repository.model;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import java.util.Date;
import java.util.List;

@MongoEntity(collection="intellectual_property_public")
public class IntellectualPropertyPublic {
    @BsonId
    public ObjectId id;
    public ObjectId image; // Referencia a Image
    public String type;
    @BsonProperty("international_class")
    public String internationalClass;
    public String owner;
    public List<ObjectId> authors; // Referencia a autores (puede ser otra colección)
    public List<String> tags;
    @BsonProperty("approval_date")
    public Date approvalDate;
    public Object details; // Puede ser Brand, Patent, etc. (polimórfico)
}