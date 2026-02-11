package cma.proyectocma.ui.screen.base;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

public abstract class Painter<R extends Node> {

    protected abstract R rootNode();

    protected Insets customInsets(double verticalPadding, double horizontalPadding) {
        return new Insets(verticalPadding, horizontalPadding, verticalPadding, horizontalPadding);
    }

    protected Background customBackground(Color color) {
        return new Background(new BackgroundFill(color, new CornerRadii(5), Insets.EMPTY));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    protected static final class NodeStyler<N extends Node> {

        private N node;

        public static <T extends Node> NodeStyler<T> of(T node) {
            return new NodeStyler<>(node);
        }

        public <E extends Effect> NodeStyler<N> effect(E effect) {
            node.setEffect(effect);
            return this;
        }

        public NodeStyler<N> cursor(Cursor cursor) {
            node.setCursor(cursor);
            return this;
        }

        public NodeStyler<N> min(double size) {
            if (node instanceof Region region) region.setMinSize(size, size);
            return this;
        }

        public NodeStyler<N> pref(double size) {
            if (node instanceof Region region) region.setPrefSize(size, size);
            return this;
        }

        public NodeStyler<N> max(double size) {
            if (node instanceof Region region) region.setMaxSize(size, size);
            return this;
        }

        public NodeStyler<N> min(double height, double width) {
            if (node instanceof Region region) region.setMinSize(width, height);
            return this;
        }

        public NodeStyler<N> pref(double height, double width) {
            if (node instanceof Region region) region.setPrefSize(width, height);
            return this;
        }

        public NodeStyler<N> max(double height, double width) {
            if (node instanceof Region region) region.setMaxSize(width, height);
            return this;
        }

        public NodeStyler<N> background(Background background) {
            if (node instanceof Region region) region.setBackground(background);
            return this;
        }

        public NodeStyler<N> padding(Insets insets) {
            if (node instanceof Region region) region.setPadding(insets);
            return this;
        }

        public NodeStyler<N> font(Font font) {
            if (node instanceof Text text) text.setFont(font);
            if (node instanceof Labeled labeled) labeled.setFont(font);
            return this;
        }

        public NodeStyler<N> textFill(Color color) {
            if (node instanceof Labeled labeled) labeled.setTextFill(color);
            return this;
        }

        public NodeStyler<N> also(Consumer<N> custom) {
            custom.accept(node);
            return this;
        }

        public NodeStyler<N> hover(boolean doResize) {
            if (node instanceof Region region) {
                BackgroundFill unselectedBackgroundFill = region.getBackground().getFills().getFirst();
                region.setOnMouseEntered(ignored -> {
                    if (doResize) {
                        region.setScaleX(1.05);
                        region.setScaleY(1.05);
                    }
                    region.setBackground(new Background(new BackgroundFill(
                            ((Color) unselectedBackgroundFill.getFill()).interpolate(Color.WHITE, 0.1),
                            unselectedBackgroundFill.getRadii(),
                            unselectedBackgroundFill.getInsets()
                    )));
                });
                region.setOnMouseExited(ignored -> {
                    if (doResize) {
                        region.setScaleX(1);
                        region.setScaleY(1);
                    }
                    region.setBackground(new Background(unselectedBackgroundFill));
                });
            }
            return this;
        }

    }

}
