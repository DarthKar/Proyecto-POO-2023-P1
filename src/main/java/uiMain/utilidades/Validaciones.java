package uiMain.utilidades;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Validaciones {

    private static final String MENSAJE_ID_NO_ES_NUMERO = "Id debe ser un numero";
    private static final String MENSAJE_EMAIL_NO_VALIDO = "El email ingresado no es valido.";
    private static final String MENSAJE_ID_YA_EXISTE_EN_BD = "El id ingresado ya existe en la base de datos. Para modificar usa la función de modificar vendedor";
    private static final String MENSAJE_TODOS_LOS_CAMPOS_SON_OBLIGATORIOS = "Todos los campos son obligatorios";
    private static final String MENSAJE_NO_SE_HA_ENCONTRADO_VENDEDOR = "No se ha encontrado el vendedor con id %s";
    private static Pattern patternCorreo = Pattern.compile("^(.+)@(\\S+)$");
    private static Pattern patternNumeros = Pattern.compile("[0-9]+");

    protected static void validarUsuario(String idStr, String nombre, String apellido, String correo) {

        validarCamposNoVacios(idStr, nombre, apellido, correo);
        validarId(idStr);
        validarCorreo(correo);
    }

    protected static void validarCorreo(String correo) {
        if (!patternCorreo.matcher(correo).matches())
            throw new IllegalArgumentException(MENSAJE_EMAIL_NO_VALIDO);
    }

    protected static void validarId(String idStr) {
        if (!patternNumeros.matcher(idStr).matches())
            throw new IllegalArgumentException(MENSAJE_ID_NO_ES_NUMERO);
    }

    protected static void validarCamposNoVacios(String idStr, String nombre, String apellido, String correo) {
        if (StringUtils.isBlank(idStr) || StringUtils.isBlank(nombre)
                || StringUtils.isBlank(apellido) || StringUtils.isBlank(correo))
            throw new IllegalArgumentException(MENSAJE_TODOS_LOS_CAMPOS_SON_OBLIGATORIOS);
    }

}
