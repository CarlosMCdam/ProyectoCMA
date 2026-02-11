package cma.proyectocma.ui.util;

import cma.proyectocma.data.model.*;
import cma.proyectocma.data.repository.Repository;
import cma.proyectocma.domain.mapper.PkDobleMapper;
import cma.proyectocma.domain.mapper.PkSimpleMapper;
import cma.proyectocma.domain.model.*;
import cma.proyectocma.domain.service.PkDobleService;
import cma.proyectocma.domain.service.PkSimpleService;
import cma.proyectocma.domain.service.base.Service;
import cma.proyectocma.ui.screen.listado.ListadoTipo;

import java.util.Arrays;

public enum ServiceRegistry {

    ARMARIO_CARGA(
            ListadoTipo.ARMARIOCARGA,
            new PkSimpleService<>(
                    new Repository<>(ArmarioCarga.class),
                    new PkSimpleMapper<>(ArmarioCargaDto.class, ArmarioCarga.class)
            ),
            ArmarioCargaDto.class
    ),

    AVERIA(
            ListadoTipo.AVERIA,
            new PkSimpleService<>(
                    new Repository<>(Averia.class),
                    new PkSimpleMapper<>(AveriaDto.class, Averia.class)
            ),
            AveriaDto.class
    ),

    DISPOSITIVO(
            ListadoTipo.DISPOSITIVO,
            new PkSimpleService<>(
                    new Repository<>(Dispositivo.class),
                    new PkSimpleMapper<>(DispositivoDto.class, Dispositivo.class)
            ),
            DispositivoDto.class
    ),

    IMPRESORA(
            ListadoTipo.IMPRESORA,
            new PkSimpleService<>(
                    new Repository<>(Impresora.class),
                    new PkSimpleMapper<>(ImpresoraDto.class, Impresora.class)
            ),
            ImpresoraDto.class
    ),

    MODELO(
            ListadoTipo.MODELO,
            new PkSimpleService<>(
                    new Repository<>(Modelo.class),
                    new PkSimpleMapper<>(ModeloDto.class, Modelo.class)
            ),
            ModeloDto.class
    ),

    PANTALLA_TACTIL(
            ListadoTipo.PANTALLATACTIL,
            new PkSimpleService<>(
                    new Repository<>(PantallaTactil.class),
                    new PkSimpleMapper<>(PantallaTactilDto.class, PantallaTactil.class)
            ),
            PantallaTactilDto.class
    ),

    PC(
            ListadoTipo.PC,
            new PkSimpleService<>(
                    new Repository<>(Pc.class),
                    new PkSimpleMapper<>(PcDto.class, Pc.class)
            ),
            PcDto.class
    ),

    PERSONA(
            ListadoTipo.PERSONA,
            new PkSimpleService<>(
                    new Repository<>(Persona.class),
                    new PkSimpleMapper<>(PersonaDto.class, Persona.class)
            ),
            PersonaDto.class
    ),

    PORTATIL(
            ListadoTipo.PORTATIL,
            new PkSimpleService<>(
                    new Repository<>(Portatil.class),
                    new PkSimpleMapper<>(PortatilDto.class, Portatil.class)
            ),
            PortatilDto.class
    ),

    TABLET(
            ListadoTipo.TABLET,
            new PkSimpleService<>(
                    new Repository<>(Tablet.class),
                    new PkSimpleMapper<>(TabletDto.class, Tablet.class)
            ),
            TabletDto.class
    ),

    PERSONADISPOSITIVO(
            ListadoTipo.PERSONADISPOSITIVO,
            new PkDobleService<>(
                    new Repository<>(PersonaDispositivo.class),
                    new PkDobleMapper<>(PersonaDispositivoDto.class, PersonaDispositivo.class)
            ),
            PersonaDispositivoDto.class
    );

    public final ListadoTipo tipo;
    public final Service<?, ?, ?, ?> service;
    public final Class<? extends Record> dtoClass;

    ServiceRegistry(ListadoTipo tipo, Service<?, ?, ?, ?> service, Class<? extends Record> dtoClass) {
        this.tipo = tipo;
        this.service = service;
        this.dtoClass = dtoClass;
    }

    public static Service<?, ?, ?, ?> getService(ListadoTipo tipo) {
        return Arrays.stream(values())
                .filter(e -> e.tipo == tipo)
                .findFirst()
                .orElseThrow()
                .service;
    }

    public static Class<? extends Record> getDtoClass(ListadoTipo tipo) {
        return Arrays.stream(values())
                .filter(e -> e.tipo == tipo)
                .findFirst()
                .orElseThrow()
                .dtoClass;
    }
}
