package cma.proyectocma.domain.model.util;

import cma.proyectocma.domain.common.C;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IdReference {

    Entity value();

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    enum Entity {

        DISPOSITIVO(C.REF_DISPOSITIVO), MODELO(C.REF_MODELO), PERSONA(C.REF_PERSONA);

        private final String entityName;

    }
}
