package cma.proyectocma.ui.screen.detalle;

import cma.proyectocma.Launcher;
import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.service.base.Service;
import cma.proyectocma.ui.exception.UiException;
import cma.proyectocma.ui.screen.base.EmbeddedController;
import cma.proyectocma.ui.screen.listado.ListadoTipo;
import cma.proyectocma.ui.screen.menu.MenuController;
import cma.proyectocma.ui.util.ServiceRegistry;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.IntStream;

@Setter
@NoArgsConstructor
public final class DetalleController extends EmbeddedController<BorderPane, DetallePainter, MenuController> {

    private HBox attributesContainer;

    private Record item;
    private ListadoTipo listadoTipo;

    @Override
    protected void initInner() {
        setPainter(new DetallePainter());
        setRootNode(getPainter().rootNode());
        attributesContainer = getPainter().attributesContainer();
    }

    public DetalleController init(MenuController containerController, Record item, ListadoTipo listadoTipo) {
        initInner();
        setContainerController(containerController);
        this.item = item;
        this.listadoTipo = listadoTipo;
        configurarCampos();
        return this;
    }

    private void configurarCampos() {
        Arrays.stream(item.getClass().getRecordComponents()).forEach(recordComponent -> {
            VBox attributeContainer = getPainter().attributeContainer();
            try {
                Object value = recordComponent.getAccessor().invoke(item);
                if (recordComponent.isAnnotationPresent(DtoId.class)) {
                    attributesContainer.getChildren().add(getPainter().idValue(value.toString()));
                    return;
                }
                attributeContainer.getChildren().addAll(
                        getPainter().attributeName(Launcher.capitalize(textFormatter(recordComponent.getName()))),
                        getPainter().attributeValue(value != null ? value.toString() : "---")
                );
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new UiException(e);
            }
            attributesContainer.getChildren().add(attributeContainer);
        });
        getRootNode().setLeft(attributesContainer);
        Button guardar = getPainter().guardar();
        guardar.setOnMouseClicked(ignored -> guardar());
        Button cancelar = getPainter().cancelar();
        cancelar.setOnMouseClicked(ignored -> cancelar());
        HBox optionsContainer = getPainter().optionsContainer();
        optionsContainer.getChildren().setAll(guardar, cancelar);
        getRootNode().setRight(optionsContainer);
    }

    public void guardar() {
        try {
            @SuppressWarnings("unchecked")
            Service<Record, ?, ?, ?> servicio = (Service<Record, ?, ?, ?>) ServiceRegistry.getService(listadoTipo);
            HBox valuesContainer = (HBox) getRootNode().getLeft();
            RecordComponent[] components = item.getClass().getRecordComponents();
            servicio.update(
                    item.getClass().getDeclaredConstructor(
                            Arrays.stream(components).map(RecordComponent::getType).toArray(Class[]::new)
                    ).newInstance(IntStream.range(0, components.length)
                            .mapToObj(i -> {
                                if (i == 0) try {
                                    return item.getClass().getRecordComponents()[0].getAccessor().invoke(item);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new UiException(e);
                                }
                                VBox vbox = (VBox) valuesContainer.getChildren().get(i);
                                String textFieldText = ((TextField) vbox.getChildren().get(1)).getText();
                                Class<?> tipo = components[i].getType();
                                return !textFieldText.isEmpty() && !textFieldText.equals("---") ? convertir(textFieldText, tipo) : null;
                            })
                            .toArray()
                    )
            );
            getContainerController().loadList(listadoTipo);
        } catch (Exception e) {
            throw new UiException(e);
        }
    }

    private void cancelar() {
        getContainerController().getRootNode().setBottom(null);
        getContainerController().loadList(listadoTipo);
    }

    private Object convertir(String texto, Class<?> tipo) {
        System.out.println(texto);
        System.out.println(tipo);
        if (tipo == Integer.class || tipo == int.class) return Integer.valueOf(texto);
        if (tipo == Double.class || tipo == double.class) return Double.valueOf(texto);
        if (tipo == Boolean.class || tipo == boolean.class) return Boolean.valueOf(texto);
        if (tipo == BigDecimal.class) return new BigDecimal(texto);
        if (tipo == LocalDate.class) return LocalDate.parse(texto);
        return texto;
    }

}
