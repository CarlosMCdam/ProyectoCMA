package cma.proyectocma.ui.controllers;

import cma.proyectocma.domain.service.ServiceArmarioCarga;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public final class ArmarioCargaController {

    private final ServiceArmarioCarga service;

    public ArmarioCargaController(ServiceArmarioCarga service) {
        this.service = service;
    }

    @FXML
    private Label mainText;

    @FXML
    protected void getAllArmariosCarga() {
        mainText.setText(service.findAll().toString());
    }

    @FXML
    protected void initialize() {
        mainText.setText(service.findById(1).getId().toString());
    }
}
