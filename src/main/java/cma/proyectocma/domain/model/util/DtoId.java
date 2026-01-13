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
@Target(ElementType.RECORD_COMPONENT)
public @interface DtoId {

    IdIndex value() default IdIndex.SIMPLE;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    enum IdIndex {

        SIMPLE(C.ID_SIMPLE), ID1(C.ID_1), ID2(C.ID_2);

        private final String idName;

    }
}
