package cma.proyectocma.ui.controller;

import cma.proyectocma.domain.service.PersonaDispositivoService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class PersonaDispositivoController {

    private final PersonaDispositivoService service;

    public PersonaDispositivoController(PersonaDispositivoService service) {
        this.service = service;
    }

    @FXML
    private Label mainText;

    @FXML
    protected void getAllPersonasDispositivos() {
        mainText.setText(service.findAll().toString());
    }

    @FXML
    protected void initialize() {
        getAllPersonasDispositivos();
    }

}
