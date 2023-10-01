package gestorAplicacion.casoDeUso;


import baseDatos.impl.UsuarioRepositorio;
import gestorAplicacion.entidad.usuario.Usuario;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

public class UsuarioCDU {

    private static final String MENSAJE_ID_NO_ES_NUMERO = "Id debe ser un numero";
    private static final String MENSAJE_EMAIL_NO_VALIDO = "El email ingresado no es valido.";
    private static final String MENSAJE_ID_YA_EXISTE_EN_BD = "El id ingresado ya existe en la base de datos. Para modificar usa la función de modificar vendedor";
    private static final String MENSAJE_TODOS_LOS_CAMPOS_SON_OBLIGATORIOS = "Todos los campos son obligatorios";
    private static final String MENSAJE_NO_SE_HA_ENCONTRADO_VENDEDOR = "No se ha encontrado el vendedor con id %s";
    private static Pattern patternCorreo = Pattern.compile("^(.+)@(\\S+)$");
    private static Pattern patternNumeros = Pattern.compile("[0-9]+");


    public void eliminarVendedor(long id) {
        UsuarioRepositorio.eliminar(id);
    }

    public void guardarVendedor(String idStr, String nombre, String apellido, String correo) {

        validarCampos(idStr, nombre, apellido, correo);
        final long id = Long.parseLong(idStr);

        if (UsuarioRepositorio.obtenerPorId(id).isPresent())
            throw new IllegalArgumentException("-" + MENSAJE_ID_YA_EXISTE_EN_BD);

        UsuarioRepositorio.guardar(new Vendedor(id, nombre, apellido, correo));
    }

    public List<Vendedor> obtenerVendedores() {
        return UsuarioRepositorio.obtener();
    }

    public void modificarVendedor(String id, String nombreMod, String apellidoMod, String correoMod) {
        Vendedor vendedor = obtenerVendedorPorId(id);
        if(StringUtils.isNotBlank(nombreMod)){
            vendedor.setNombre(nombreMod);
        }
        if(StringUtils.isNotBlank(apellidoMod)){
            vendedor.setApellido(apellidoMod);
        }
        if(StringUtils.isNotBlank(correoMod)){
            vendedor.setCorreo(correoMod);
        }
        UsuarioRepositorio.guardar(vendedor);
    }

    public Vendedor obtenerVendedorPorId(String idStr) {
        String mensaje = validarId(idStr);
        if (StringUtils.isNotEmpty(mensaje))
            throw new IllegalArgumentException(mensaje);

        return UsuarioRepositorio.obtenerPorId(Long.parseLong(idStr)).orElseThrow(() ->
                new IllegalArgumentException(MENSAJE_NO_SE_HA_ENCONTRADO_VENDEDOR.formatted(idStr))
        );
    }

    private static void validarCampos(String idStr, String nombre, String apellido, String correo) {
        String mensaje = StringUtils.EMPTY;

        mensaje += validarCamposNoVacios(idStr, nombre, apellido, correo);
        mensaje += validarId(idStr);
        mensaje += validarCorreo(correo);

        if (StringUtils.isNotEmpty(mensaje))
            throw new IllegalArgumentException(mensaje);
    }

    private static String validarCorreo(String correo) {
        if (!patternCorreo.matcher(correo).matches())
            return "- " + MENSAJE_EMAIL_NO_VALIDO;
        return StringUtils.EMPTY;
    }

    private static String validarId(String idStr) {
        if (!patternNumeros.matcher(idStr).matches())
            return "- " + MENSAJE_ID_NO_ES_NUMERO;
        return StringUtils.EMPTY;
    }

    private static String validarCamposNoVacios(String idStr, String nombre, String apellido, String correo) {
        if (StringUtils.isBlank(idStr) || StringUtils.isBlank(nombre)
                || StringUtils.isBlank(apellido) || StringUtils.isBlank(correo))
            return "- " + MENSAJE_TODOS_LOS_CAMPOS_SON_OBLIGATORIOS;
        return StringUtils.EMPTY;
    }



}
