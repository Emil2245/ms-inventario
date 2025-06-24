package ec.edu.uce.cati.repository.model.properties;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Clase base abstracta para los detalles específicos de cada tipo de propiedad intelectual.
 *
 * Las anotaciones de Jackson (@JsonTypeInfo y @JsonSubTypes) son la clave para que
 * Quarkus/RESTEasy Reactive sepa cómo convertir el JSON a la clase Java correcta y viceversa.
 *
 * @JsonTypeInfo:
 *  - use = Id.NAME: Usa un nombre lógico (e.g., "brand") para identificar el tipo.
 *  - include = As.PROPERTY: El identificador de tipo será una propiedad más en el objeto JSON.
 *  - property = "type": El nombre de esa propiedad en el JSON será "type".
 *
 * @JsonSubTypes:
 *  - Mapea cada nombre lógico a su clase Java correspondiente.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Brand.class, name = "brand"),
        @JsonSubTypes.Type(value = Patent.class, name = "patent"),
        @JsonSubTypes.Type(value = Software.class, name = "software"),
        @JsonSubTypes.Type(value = Database.class, name = "database"),
        // Esta es la línea que estaba causando el error. Ahora está corregida.
        @JsonSubTypes.Type(value = LiteraryWork.class, name = "literary_work"),
        @JsonSubTypes.Type(value = PlantVariety.class, name = "plant_variety"),
        @JsonSubTypes.Type(value = DistinctiveSign.class, name = "distinctive_sign")
})
public abstract class IntellectualPropertyDetails {
    // Esta clase base no necesita tener campos. Actúa como un marcador y
    // como portadora de la configuración de polimorfismo para sus hijas.
}
