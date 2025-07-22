package ec.edu.uce.cati.repository.model;

import org.bson.types.ObjectId;
import java.util.Date;

public class Patent {
    public String inventionTitle;
    public Date publicationDate;
    public ObjectId pdf; // Referencia a Attachment
} 