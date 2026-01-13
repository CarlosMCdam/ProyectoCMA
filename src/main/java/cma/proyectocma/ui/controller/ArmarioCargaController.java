package cma.proyectocma.ui.controller;

import cma.proyectocma.domain.service.ServiceArmarioCarga;
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
        getAllArmariosCarga();
    }
}
