package cma.proyectocma.ui.controller.listado;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ListadoTipo {

    ARMARIOCARGA("Armario de Carga", "armarioCarga"),
    AVERIA("Avería", "averia"),
    DISPOSITIVO("Dispositivo", "dispositivo"),
    IMPRESORA("Impresora", "impresora"),
    MODELO("Modelo", "modelo"),
    PANTALLATACTIL("Pantalla Táctil", "pantallaTactil"),
    PC("PC", "pc"),
    PERSONADISPOSITIVO("Persona <-> Dispositivo", "personaDispositivo"),
    PERSONA("Persona", "persona"),
    PORTATIL("Portatil", "portatil"),
    TABLET("Tablet", "tablet");

    private final String nombre;
    private final String id;

}
