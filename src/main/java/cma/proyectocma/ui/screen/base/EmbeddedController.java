package cma.proyectocma.ui.screen.base;

import cma.proyectocma.ui.screen.detalle.DetalleController;
import cma.proyectocma.ui.screen.listado.ListadoController;
import cma.proyectocma.ui.screen.listado.ListadoItemController;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract sealed class EmbeddedController<
        R extends Region,
        P extends Painter<R>,
        C extends Controller<? extends Region, ? extends Painter<?>>
        > extends Controller<R, P> permits DetalleController, ListadoController, ListadoItemController {

    private C containerController;

}
