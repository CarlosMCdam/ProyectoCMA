package cma.proyectocma.ui.controller;

import cma.proyectocma.dao.repository.base.PkSimpleRepository;
import cma.proyectocma.domain.mapper.base.PkSimpleMapper;
import cma.proyectocma.domain.model.ArmarioCargaDto;
import cma.proyectocma.domain.service.base.PkSimpleService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public final class ArmarioCargaController {

    private final PkSimpleService<ArmarioCargaDto, ?> service;
    private final PkSimpleMapper<ArmarioCargaDto, ?> mapper;
    private final PkSimpleRepository<?> repository;

    public ArmarioCargaController(
            @Qualifier("ArmarioCargaService") PkSimpleService<ArmarioCargaDto, ?> service,
            @Qualifier("ArmarioCargaMapper") PkSimpleMapper<ArmarioCargaDto, ?> mapper,
            @Qualifier("ArmarioCargaRepository") PkSimpleRepository<?> repository
            ) {
        this.service = service;
        this.mapper = mapper;
        this.repository = repository;
    }

    @FXML
    private Label mainText;

    @FXML
    protected void getAllArmariosCarga() {

    }

    @FXML
    protected void initialize() {
        getAllArmariosCarga();
    }

}
