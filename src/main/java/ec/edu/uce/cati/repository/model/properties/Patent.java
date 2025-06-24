package ec.edu.uce.cati.repository.model.properties;

import org.bson.types.ObjectId;

import java.util.Date;

public class Patent extends IntellectualPropertyDetails {
    public String invention_title;
    public Date publication_date;
    public ObjectId pdfId; // ID que referencia a un documento en la colección 'attachments'
}