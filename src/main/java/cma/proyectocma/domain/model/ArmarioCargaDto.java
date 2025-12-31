package cma.proyectocma.domain.model;

import cma.proyectocma.dao.model.ArmarioCarga;
import cma.proyectocma.domain.model.base.DtoPkSimple;

public record ArmarioCargaDto(
        Integer id,
        String ubicacion,
        Integer numPuertos,
        Boolean ventilado
) implements DtoPkSimple<ArmarioCargaDto, ArmarioCarga> {}
