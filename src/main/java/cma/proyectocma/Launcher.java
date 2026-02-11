package cma.proyectocma;

import cma.proyectocma.ui.ApplicationCMA;
import javafx.application.Application;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Launcher {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/databaseCMA",
                "cma",
                "cma"
        );
        Application.launch(ApplicationCMA.class, args);
    }

    /**
     * @param string String.
     * @return String con la primera letra mayúscula.
     */
    public static String capitalize(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

}
