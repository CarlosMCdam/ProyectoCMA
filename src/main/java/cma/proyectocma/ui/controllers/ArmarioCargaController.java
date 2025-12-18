package cma.proyectocma.ui.controllers;

import cma.proyectocma.dao.model.ArmarioCarga;
import cma.proyectocma.domain.service.ServicePkSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class ArmarioCargaController {

    private final ServicePkSimple<ArmarioCarga> service;

    public ArmarioCargaController(ServicePkSimple<ArmarioCarga> service) {
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
        mainText.setText(service.findById(1).getDispositivos().toString());
    }
}
