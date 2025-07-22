package ec.edu.uce.cati.repository.model;

import org.bson.types.ObjectId;
import java.util.Date;

public class LiteraryWork {
    public String title;
    public ObjectId coverImage; // Referencia a Image
    public ObjectId previewPdf; // Referencia a Attachment
    public String authorName;
    public String genre;
    public String language;
    public Date publicationDate;
    public String publisherName;
    public Integer numberOfPages;
    public String digitalVersionUrl;
    public String summary;
    public String isbn;
} 