package ec.edu.uce.cati.service.mapper;

import ec.edu.uce.cati.repository.dto.IntellectualPropertyPublicDTO;
import ec.edu.uce.cati.repository.model.IntellectualPropertyPublic;

public class InventoryMapper {
    public static IntellectualPropertyPublicDTO entityToDto(IntellectualPropertyPublic entity) {
        if (entity == null) return null;
        IntellectualPropertyPublicDTO dto = new IntellectualPropertyPublicDTO();
        dto.id = entity.id != null ? entity.id.toHexString() : null;
        dto.image = entity.image != null ? entity.image.toHexString() : null;
        dto.type = entity.type;
        dto.internationalClass = entity.internationalClass;
        dto.owner = entity.owner;
        dto.authors = entity.authors != null ? entity.authors.stream().map(a -> a.toHexString()).toList() : null;
        dto.tags = entity.tags;
        dto.approvalDate = entity.approvalDate;
        dto.details = entity.details; // Si necesitas mapear detalles, crea mappers adicionales
        return dto;
    }

    public static IntellectualPropertyPublic dtoToEntity(IntellectualPropertyPublicDTO dto) {
        if (dto == null) return null;
        IntellectualPropertyPublic entity = new IntellectualPropertyPublic();
        // El id puede ser null para nuevos registros
        entity.id = null;
        entity.image = dto.image != null ? new org.bson.types.ObjectId(dto.image) : null;
        entity.type = dto.type;
        entity.internationalClass = dto.internationalClass;
        entity.owner = dto.owner;
        entity.authors = dto.authors != null ? dto.authors.stream().map(org.bson.types.ObjectId::new).toList() : null;
        entity.tags = dto.tags;
        entity.approvalDate = dto.approvalDate;
        entity.details = dto.details; // Si necesitas mapear detalles, crea mappers adicionales
        return entity;
    }
} 