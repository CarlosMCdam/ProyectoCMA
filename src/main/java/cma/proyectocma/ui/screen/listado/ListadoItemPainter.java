package cma.proyectocma.ui.screen.listado;

import cma.proyectocma.ui.common.C;
import cma.proyectocma.ui.screen.base.Painter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public final class ListadoItemPainter extends Painter<HBox> {

    @Override
    protected HBox rootNode() {
        return NodeStyler.of(new HBox(12))
                .pref(Region.USE_COMPUTED_SIZE)
                .max(Region.USE_PREF_SIZE)
                .effect(new DropShadow(6, 0, 0, Color.rgb(11, 84, 160, 0.04)))
                .cursor(Cursor.HAND)
                .background(customBackground(Color.BLACK))
                .padding(new Insets(C.NODE_PADDING_DEFAULT))
                .hover(false)
                .getNode();
    }

    VBox idContainer() {
        return NodeStyler.of(new VBox(C.NODE_SPACING_DEFAULT))
                .background(customBackground(Color.WHITE))
                .padding(new Insets(C.NODE_PADDING_DEFAULT))
                .getNode();
    }

    Label idName(String text) {
        return NodeStyler.of(new Label(text))
                .background(customBackground(Color.TRANSPARENT))
                .font(Font.font(C.FONT_SEGOEUI, FontWeight.BOLD, 15))
                .textFill(Color.BLACK)
                .also(label -> label.setAlignment(Pos.CENTER))
                .getNode();
    }

    Label idValue(String text) {
        Label idValue = NodeStyler.of(new Label(text))
                .max(Region.USE_COMPUTED_SIZE, Double.MAX_VALUE)
                .background(customBackground(Color.BLACK))
                .padding(customInsets(0, C.NODE_PADDING_DEFAULT * 2d))
                .font(Font.font(C.FONT_SEGOEUI, FontWeight.EXTRA_BOLD, 20))
                .textFill(Color.WHITE)
                .also(label -> label.setAlignment(Pos.CENTER))
                .getNode();
        VBox.setVgrow(idValue, Priority.ALWAYS);
        return idValue;
    }

    VBox attributeContainer() {
        return NodeStyler.of(new VBox(C.NODE_SPACING_DEFAULT))
                .background(customBackground(Color.rgb(255, 255, 255, 0.1)))
                .padding(new Insets(C.NODE_PADDING_DEFAULT))
                .getNode();
    }

    Label attributeName(String text) {
        Label attributeName = NodeStyler.of(new Label(text))
                .max(Region.USE_COMPUTED_SIZE, Double.MAX_VALUE)
                .font(Font.font(C.FONT_SEGOEUI, FontWeight.SEMI_BOLD, 15))
                .textFill(Color.WHITE)
                .getNode();
        VBox.setVgrow(attributeName, Priority.ALWAYS);
        return attributeName;
    }

    Label attributeValue(String text) {
        Label attributeValue = NodeStyler.of(new Label(text))
                .max(Region.USE_COMPUTED_SIZE, Double.MAX_VALUE)
                .background(customBackground(Color.rgb(255, 255, 255, 0.05)))
                .padding(customInsets(2, C.NODE_PADDING_DEFAULT))
                .font(Font.font(C.FONT_SEGOEUI, FontWeight.SEMI_BOLD, 15))
                .textFill(Color.WHITE)
                .also(label -> label.setAlignment(Pos.CENTER))
                .getNode();
        VBox.setVgrow(attributeValue, Priority.ALWAYS);
        return attributeValue;
    }

}
