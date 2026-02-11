package cma.proyectocma.ui.screen.base;

import cma.proyectocma.ui.exception.UiException;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public abstract class Controller<R extends Region, P extends Painter<R>> {

    private R rootNode;

    private P painter;

    protected abstract void initInner();

    protected <C extends Controller<? extends Region, ? extends Painter<? extends Node>>> C loadController(
            Class<C> embeddedControllerClass
    ) {
        try {
            return embeddedControllerClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new UiException(e.getMessage());
        }
    }

    protected static String textFormatter(String text) {
        return text.chars()
                .mapToObj(unicode -> (char) unicode)
                .map(character -> Character.isUpperCase(character) ? " " + character : "" + character)
                .collect(Collectors.joining())
                .trim();
    }

}
