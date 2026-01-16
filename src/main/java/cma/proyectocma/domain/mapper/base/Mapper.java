package cma.proyectocma.domain.mapper.base;

import cma.proyectocma.domain.common.C;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

abstract sealed class Mapper permits PkSimpleMapper, PkDobleMapper {

    @SuppressWarnings(C.SONARQUBEFALSEPOSITIVE_FIELDACCESSIBILITYBYPASS)
    protected Field getField(Class<?> objectClass, Object objectReference) throws NoSuchFieldException {
        Field field = null;
        if (objectReference instanceof Field objectField) field = objectField;
        if (objectReference instanceof String objectFieldName)
            field = objectClass.getDeclaredField(objectFieldName);
        if (field != null) field.setAccessible(true);
        return field;
    }

    protected Object getFieldValue(@NotNull Object object, Object objectReference)
            throws NoSuchFieldException, IllegalAccessException {
        return getField(object.getClass(), objectReference).get(object);
    }

    protected void setField(@NotNull Object target, Object targetReference, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        getField(target.getClass(), targetReference).set(target, value);
    }

}
