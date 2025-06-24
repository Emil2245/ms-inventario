package ec.edu.uce.cati.repository.model.properties;

import java.util.Date;
import java.util.List;

public class PlantVariety extends IntellectualPropertyDetails {
    public String variety_name;
    public String botanical_name;
    public String breeder_name;
    public List<String> distinct_characteristics;
    public String growing_conditions;
    public List<String> resistance_properties;
    public String yield_estimate;
    public Date sample_submission_date;
    // Distinctness, Uniformity, Stability Index
    public String dusi_number;
}