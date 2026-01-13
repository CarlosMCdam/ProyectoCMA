module cma.proyectocma {
    // --- JavaFX ---
    requires javafx.controls;
    requires javafx.fxml;

    // --- UI extra ---
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    // --- Persistencia / Spring ---
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires spring.data.jpa;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.boot;
    requires static lombok;
    requires spring.data.commons;
    requires org.slf4j;
    requires spring.core;
    requires annotations;

    // --- Paquetes abiertos/exports ---
    opens cma.proyectocma to javafx.fxml;
    exports cma.proyectocma;

    exports cma.proyectocma.ui;
    opens cma.proyectocma.ui to javafx.fxml;

    exports cma.proyectocma.ui.controller;
    opens cma.proyectocma.ui.controller to javafx.fxml;

    opens cma.proyectocma.domain.service;

    // --- Entidades y DAO (Hibernate/Spring Data necesitan reflexión) ---
    opens cma.proyectocma.dao.model;
    opens cma.proyectocma.dao.model.base;
    opens cma.proyectocma.domain.mapper;
    opens cma.proyectocma.domain.mapper.util;
    opens cma.proyectocma.domain.service.base;
    opens cma.proyectocma.dao.repository.base;
}
