package cma.proyectocma.ui.controller.base;

import cma.proyectocma.ui.controller.detalle.DetalleController;
import cma.proyectocma.ui.controller.listado.ListadoController;
import cma.proyectocma.ui.controller.listado.ListadoItemController;
import cma.proyectocma.ui.controller.listado.ListadoTipo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("java:S119")

@Getter
@Setter
@NoArgsConstructor
public abstract sealed class EmbeddedController<C extends ContainerController>
        permits DetalleController, ListadoController, ListadoItemController {

    private C containerController;

    public abstract void init(C containerController, ListadoTipo listadoTipo);

}
