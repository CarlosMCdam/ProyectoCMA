package cma.proyectocma.dao.model.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class C {

    public static final String BBDD = "databaseCMA";

    public static final String ENTITY_SIMPLE_ID = "id";
    public static final String ENTITY_DOBLE_ID_1 = "id_1";
    public static final String ENTITY_DOBLE_ID_2 = "id_2";

    public static final String ARMARIOCARGA_NOMBRE = "armarios_carga";
    public static final String ARMARIOCARGA_PK = "id_dispositivo";
    public static final String AMRARIOCARGA_CAMPO_NUMPUERTOS = "num_puertos";
    public static final String AMRARIOCARGA_CAMPO_VENTILADO = "ventilado";

    public static final String AVERIA_NOMBRE = "averia";
    public static final String AVERIA_PK = "id_averia";
    public static final String AVERIA_CAMPO_DESCRIPCION = "descripcion";
    public static final String AVERIA_CAMPO_ESTADO = "estado";
    public static final String AVERIA_CAMPO_SOLUCION = "solucion";
    public static final String AVERIA_CAMPO_FECHAINICIAL = "fecha_inicial";
    public static final String AVERIA_CAMPO_FECHAFINAL = "fecha_final";
    public static final String AVERIA_CAMPO_IDDISPOSITIVO = "id_dispositivo";
    public static final String AVERIA_CAMPO_IDPERSONA = "id_persona";

    public static final String DISPOSITIVO_NOMBRE = "dispositivos";
    public static final String DISPOSITIVO_PK = "id_dispositivo";
    public static final String DISPOSITIVO_CAMPO_IDMODELO = "id_modelo";
    public static final String DISPOSITIVO_CAMPO_UBICACION = "ubicacion";

    public static final String IMPRESORA_NOMBRE = "impresoras";
    public static final String IMPRESORA_PK = "id_dispositivo";
    public static final String IMPRESORA_CAMPO_TIPOIMPRESION = "tipo_impresion";
    public static final String IMPRESORA_CAMPO_COLOR = "color";

    public static final String MODELO_NOMBRE = "modelo";
    public static final String MODELO_PK = "id_modelo";
    public static final String MODELO_CAMPO_NOMBRE = "nombre";
    public static final String MODELO_CAMPO_ESTADO = "estado";
    public static final String MODELO_CAMPO_TIPO = "tipo";
    public static final String MODELO_CAMPO_DESCRIPCION = "descripcion";

    public static final String PANTALLATACTIL_NOMBRE = "pantallas_tactiles";
    public static final String PANTALLATACTIL_PK = "id_dispositivo";
    public static final String PANTALLATACTIL_CAMPO_PULGADAS = "pulgadas";
    public static final String PANTALLATACTIL_CAMPO_RESOLUCION = "resolucion";

    public static final String PC_NOMBRE = "pc";
    public static final String PC_PK = "id_dispositivo";
    public static final String PC_CAMPO_TIPODISCO = "tipo_disco";
    public static final String PC_CAMPO_RAMGB = "ram_gb";

    public static final String PERSONA_NOMBRE = "persona";
    public static final String PERSONA_PK = "id_persona";
    public static final String PERSONA_CAMPO_GMAIL = "gmail";
    public static final String PERSONA_UNIQUE_GMAIL = "gmail";

    public static final String PERSONADISPOSITIVO_NOMBRE = "persona_dispositivos";
    public static final String PERSONADISPOSITIVO_PK_1 = "id1";
    public static final String PERSONADISPOSITIVO_PK_PERSONA = "id_persona";
    public static final String PERSONADISPOSITIVO_PK_2 = "id2";
    public static final String PERSONADISPOSITIVO_PK_DISPOSITIVO = "id_dispositivo";

    public static final String PORTATIL_NOMBRE = "portatiles";
    public static final String PORTATIL_PK = "id_pc";
    public static final String PORTATIL_CAMPO_PULGADAS = "pulgadas";

    public static final String TABLET_NOMBRE = "tablets";
    public static final String TABLET_PK = "id_dispositivo";
    public static final String TABLET_CAMPO_SISTEMAOPERATIVO = "sistema_operativo";
    public static final String TABLET_CAMPO_PULGADAS = "pulgadas";

}
