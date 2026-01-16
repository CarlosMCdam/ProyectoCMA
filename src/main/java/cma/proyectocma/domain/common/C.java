package cma.proyectocma.domain.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class C {

    public static final String ID = "id";
    public static final String ID_DOBLE_1 = "id1";
    public static final String ID_DOBLE_2 = "id2";

    public static final String REFERENCE_DISPOSITIVO = "dispositivo";
    public static final String REFERENCE_MODELO = "modelo";
    public static final String REFERENCE_PERSONA = "persona";

    public static final String EXCEPTIONMESSAGE_MAPPER_MISSINGEMBEDDEDANNOTATION = "El id de la entidad no tiene @EmbeddedId";
    public static final String EXCEPTIONMESSAGE_MAPPER_NULLREFERENCEDENTITY = "La entidad referenciada es null";
    public static final String EXCEPTIONMESSAGE_IDRESOLVER_UNKNOWNREPOSITORYCLASS = "No se pudo determinar la clase de entidad para: ";
    public static final String EXCEPTIONMESSAGE_IDRESOLVER_MISSINGREPOSITORY = "No existe repositorio para: ";

    public static final String SONARQUBEFALSEPOSITIVE_UNUSED = "java:S1068";
    public static final String SONARQUBEFALSEPOSITIVE_FIELDACCESSIBILITYBYPASS = "java:S3011";

}
