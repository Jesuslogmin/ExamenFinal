package com.barturen.converter;

import org.springframework.stereotype.Component;

import com.barturen.dto.InfraccionDto;
import com.barturen.entity.Infraccion;

@Component
public class InfraccionConverter extends AbstractConverter<Infraccion, InfraccionDto> {

    @Override
    public InfraccionDto fromEntity(Infraccion entity) {
        if (entity == null) return null;
        return InfraccionDto.builder()
                .id(entity.getId())
                .dni(entity.getDni())
                .fecha(entity.getFecha() != null ? entity.getFecha().toString() : null)
                .placa(entity.getPlaca())
                .infraccion(entity.getInfraccion())
                .descripcion(entity.getDescripcion())
                .build();
    }

    @Override
    public Infraccion fromDTO(InfraccionDto dto) {
        if (dto == null) return null;
        return Infraccion.builder()
                .id(dto.getId())
                .dni(dto.getDni())
                .fecha(dto.getFecha() != null ? java.sql.Date.valueOf(dto.getFecha()) : null)
                .placa(dto.getPlaca())
                .infraccion(dto.getInfraccion())
                .descripcion(dto.getDescripcion())
                .build();
    }
}
