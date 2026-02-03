module cma.proyectocma {

    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires mysql.connector.j;
    requires org.slf4j;
    requires javafx.graphics;

    opens cma.proyectocma to javafx.fxml;
    exports cma.proyectocma;

    exports cma.proyectocma.ui to javafx.graphics;

    opens cma.proyectocma.ui.controller.menu to javafx.fxml;
    opens cma.proyectocma.ui.controller.listado to javafx.fxml;
    opens cma.proyectocma.ui.controller.detalle to javafx.fxml;

    opens cma.proyectocma.data.model to org.hibernate.orm.core;
    opens cma.proyectocma.data.model.base to org.hibernate.orm.core;

}