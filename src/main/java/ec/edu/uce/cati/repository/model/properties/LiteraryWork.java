package ec.edu.uce.cati.repository.model.properties;

import org.bson.types.ObjectId;
import java.util.Date;

public class LiteraryWork extends IntellectualPropertyDetails {
    public String title;
    // ID que referencia a un documento en la colección 'images'
    public ObjectId cover_image;
    // ID que referencia a un documento en la colección 'attachments'
    public ObjectId preview_pdf;
    public String author_name;
    public String genre; // e.g., "Ensayo", "Poesía", "Novela"
    public String language;
    public Date publication_date;
    public String publisher_name;
    public Integer number_of_pages;
    public String digital_version_url;
    public String summary;
    public String isbn;
}