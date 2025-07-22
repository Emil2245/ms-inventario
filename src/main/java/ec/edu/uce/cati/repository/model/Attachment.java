package ec.edu.uce.cati.repository.model;

import org.bson.types.ObjectId;

public class Attachment {
    public ObjectId id;
    public String fileType;
    public String fileUrl;
    public String fileName;
    public Long fileSize;
} 