package cma.proyectocma.ui.screen.listado;

import cma.proyectocma.ui.common.C;
import cma.proyectocma.ui.screen.base.Painter;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public final class ListadoPainter extends Painter<ScrollPane> {

    @Override
    protected ScrollPane rootNode() {
        return NodeStyler.of(new ScrollPane())
                .pref(Region.USE_COMPUTED_SIZE)
                .max(Region.USE_PREF_SIZE)
                .background(Background.EMPTY)
                .padding(new Insets(10))
                .also(rootNode -> {
                    rootNode.setFitToHeight(true);
                    rootNode.setFitToWidth(true);
                    rootNode.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    rootNode.skinProperty().addListener((obs, oldSkin, newSkin) -> {
                        Node viewport = rootNode.lookup(".viewport");
                        if (viewport instanceof Region region) {
                            region.setBackground(Background.EMPTY);
                            region.setPadding(Insets.EMPTY);
                        }
                    });

                })
                .getNode();
    }

    VBox listado() {
        return NodeStyler.of(new VBox(C.NODE_SPACING_DEFAULT))
                .pref(Region.USE_COMPUTED_SIZE)
                .max(Double.MAX_VALUE, Region.USE_PREF_SIZE)
                .background(Background.EMPTY)
                .also(listado -> listado.setFillWidth(true))
                .getNode();
    }

}
