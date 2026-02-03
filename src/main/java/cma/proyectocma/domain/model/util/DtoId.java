package cma.proyectocma.domain.model.util;

import cma.proyectocma.domain.common.C;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Representa un atributo del DTO que equivale a la clave primaria o parte de ella de la entidad JPA correspondiente.
 * <p>
 * Si la entidad es simple, los atributos son iguales (Integer -> Integer)
 * <p>
 * Si la entidad es compuesta, se separa en varios atributos (PkDoble -> Integer(Id1), Integer(Id2))
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.RECORD_COMPONENT)
public @interface DtoId {

    IdIndex value() default IdIndex.SIMPLE;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    enum IdIndex {

        SIMPLE(C.ID), ID1(C.ID_DOBLE_1), ID2(C.ID_DOBLE_2);

        private final String idName;

    }

}
