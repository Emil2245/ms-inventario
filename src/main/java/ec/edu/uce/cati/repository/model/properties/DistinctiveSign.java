package ec.edu.uce.cati.repository.model.properties;

public class DistinctiveSign extends IntellectualPropertyDetails {
    public String sign_type; // e.g., "Nombre comercial", "Lema", "Logotipo"
    public String sign_text;
    // URL a imagen o gráfico que representa el signo (posiblemente un ObjectId a la colección 'images')
    public String graphic_representation_url;
    public String meaning_or_connotation;
}