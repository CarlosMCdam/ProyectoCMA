package cma.proyectocma.ui.controller;

import cma.proyectocma.domain.mapper.base.PkSimpleMapper;
import cma.proyectocma.domain.model.ArmarioCargaDto;
import cma.proyectocma.domain.service.base.PkSimpleService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public final class ArmarioCargaController {

    private final PkSimpleMapper<ArmarioCargaDto, ?> service;

    public ArmarioCargaController(@Qualifier("ArmarioCargaMapper") PkSimpleMapper<ArmarioCargaDto, ?> service) {
        this.service = service;
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
