package cma.proyectocma;

import cma.proyectocma.ui.ApplicationCMA;
import javafx.application.Application;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Launcher {
    static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/databaseCMA",
                "cma",
                "cma"
        );
        System.out.println("OK");
        Application.launch(ApplicationCMA.class, args);
    }
}
