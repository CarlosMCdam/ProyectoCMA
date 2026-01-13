package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;

import java.time.LocalDate;

public record AveriaDto(
        @DtoId
        Integer id,
        String descripcion,
        String estado,
        String solucion,
        LocalDate fechaInicial,
        LocalDate fechaFinal,
        @IdReference(IdReference.Entity.DISPOSITIVO)
        Integer idDispositivo,
        @IdReference(IdReference.Entity.PERSONA)
        Integer idPersona
) {
}
