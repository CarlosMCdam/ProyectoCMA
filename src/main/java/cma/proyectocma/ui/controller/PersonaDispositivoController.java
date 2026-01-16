package cma.proyectocma.ui.controller;

import cma.proyectocma.dao.model.PersonaDispositivo;
import cma.proyectocma.domain.model.PersonaDispositivoDto;
import cma.proyectocma.domain.service.base.PkDobleService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

public class PersonaDispositivoController {

    private final PkDobleService<PersonaDispositivoDto, ?> service;

    public PersonaDispositivoController(PkDobleService<PersonaDispositivoDto, ?> service) {
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
