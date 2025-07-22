package ec.edu.uce.cati.repository.dto;

import java.util.Date;
import java.util.List;

public class IntellectualPropertyPublicDTO {
    public String id;
    public String image;
    public String type;
    public String internationalClass;
    public String owner;
    public List<String> authors;
    public List<String> tags;
    public Date approvalDate;
    public Object details; // Puede ser otro DTO si se requiere
} 