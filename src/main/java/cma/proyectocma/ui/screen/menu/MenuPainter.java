package cma.proyectocma.ui.screen.menu;

import cma.proyectocma.ui.common.C;
import cma.proyectocma.ui.screen.base.Painter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public final class MenuPainter extends Painter<BorderPane> {

    @Override
    protected BorderPane rootNode() {
        return NodeStyler.of(new BorderPane()).background(new Background(new BackgroundFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop(1, Color.rgb(40, 40, 40)), new Stop(0, Color.rgb(20, 20, 20))), CornerRadii.EMPTY, Insets.EMPTY))).font(Font.font(C.FONT_SEGOEUI, FontWeight.BOLD, 12)).getNode();
    }

    HBox tabs() {
        return NodeStyler.of(new HBox(C.NODE_SPACING_DEFAULT)).min(Region.USE_COMPUTED_SIZE).background(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))).padding(new Insets(8)).getNode();
    }

    Button tab(String entityName) {
        return NodeStyler.of(new Button(entityName)).min(Region.USE_PREF_SIZE).pref(Region.USE_COMPUTED_SIZE).background(customBackground(Color.rgb(255, 255, 255, 0.1))).padding(new Insets(C.NODE_PADDING_DEFAULT)).font(Font.font(C.FONT_SEGOEUI, FontWeight.BOLD, 15)).textFill(Color.WHITE).hover(true).getNode();
    }

}