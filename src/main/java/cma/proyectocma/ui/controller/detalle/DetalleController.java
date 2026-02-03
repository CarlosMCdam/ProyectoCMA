package cma.proyectocma.ui.controller.detalle;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.service.base.Service;
import cma.proyectocma.ui.controller.base.EmbeddedController;
import cma.proyectocma.ui.controller.listado.ListadoTipo;
import cma.proyectocma.ui.controller.menu.MenuController;
import cma.proyectocma.ui.util.ServiceRegistry;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Setter;

import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class DetalleController extends EmbeddedController<MenuController> {

    @Setter
    private Record item;
    @Setter
    private ListadoTipo listadoTipo;

    @FXML
    private VBox contenedorAtributos;

    // Mapa para mantener los TextField reales
    private final Map<RecordComponent, TextField> campos = new HashMap<>();

    public DetalleController() {
        System.out.println(">>> NUEVO DetalleController: " + this);
    }

    @Override
    public void init(MenuController containerController, ListadoTipo listadoTipo) {
        setContainerController(containerController);
        this.listadoTipo = listadoTipo;
    }

    public void setData(Record objeto) {

        this.item = objeto;
        System.out.println("setData en controller: " + this);

        // Primera vez → crear campos
        if (campos.isEmpty()) {
            Arrays.stream(objeto.getClass().getRecordComponents()).forEach(component -> {
                try {
                    if (component.isAnnotationPresent(DtoId.class)) return;
                    Object valor = component.getAccessor().invoke(objeto);

                    HBox fila = new HBox(10);
                    fila.setPadding(new Insets(10));

                    Label label = new Label(component.getName() + ":");

                    TextField campo = new TextField(valor != null ? valor.toString() : "");
                    campo.setUserData(component);

                    campos.put(component, campo);

                    fila.getChildren().addAll(label, campo);
                    contenedorAtributos.getChildren().add(fila);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } else {
            // Ya existen → solo actualizar valores
            campos.forEach((component, campo) -> {
                try {
                    Object valor = component.getAccessor().invoke(objeto);
                    campo.setText(valor != null ? valor.toString() : "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }


    @FXML
    public void guardar() {
        System.out.println("GUARDAR ejecutado");
        System.out.println("guardar en controller: " + this);

        try {
            Record objeto = reconstruirRecord();

            // Cast seguro del servicio
            @SuppressWarnings("unchecked")
            Service<Record, ?, ?, ?> servicio =
                    (Service<Record, ?, ?, ?>) ServiceRegistry.getService(listadoTipo);

            // Actualizar
            servicio.update(objeto);

            // Cerrar detalle y refrescar listado
            getContainerController().ocultarDetalle();
            getContainerController().recargarListado();

            System.out.println("TIPO EN GUARDAR = " + listadoTipo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void cancelar() {
        getContainerController().ocultarDetalle();
    }


    private Record reconstruirRecord() throws Exception {
        Class<?> clazz = item.getClass();
        var components = clazz.getRecordComponents();

        Object[] valores = new Object[components.length];

        for (int i = 0; i < components.length; i++) {
            RecordComponent comp = components[i];

            TextField campo = campos.get(comp);

            if (campo == null) {
                valores[i] = comp.getAccessor().invoke(item);
                continue;
            }

            valores[i] = convertir(campo.getText(), comp.getType());
        }

        return (Record) clazz.getDeclaredConstructors()[0].newInstance(valores);
    }

    private Object convertir(String texto, Class<?> tipo) {
        if (tipo == Integer.class || tipo == int.class) return Integer.valueOf(texto);
        if (tipo == Long.class || tipo == long.class) return Long.valueOf(texto);
        if (tipo == Double.class || tipo == double.class) return Double.valueOf(texto);
        if (tipo == Boolean.class || tipo == boolean.class) return Boolean.valueOf(texto);
        return texto;
    }

}
