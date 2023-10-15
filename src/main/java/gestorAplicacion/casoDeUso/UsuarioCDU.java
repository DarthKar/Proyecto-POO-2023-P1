package gestorAplicacion.casoDeUso;


import baseDatos.impl.UsuarioRepositorio;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

public class UsuarioCDU {


    public void eliminarVendedor(long id) {
        UsuarioRepositorio.eliminar(id);
    }

    public void guardarVendedor(String idStr, String nombre, String apellido, String correo) {

        //validarCampos(idStr, nombre, apellido, correo);
        final long id = Long.parseLong(idStr);

        if (UsuarioRepositorio.obtenerPorId(id).isPresent())
            throw new IllegalArgumentException("-" /*+ MENSAJE_ID_YA_EXISTE_EN_BD*/);

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
        //String mensaje = validarId(idStr);
        /*if (StringUtils.isNotEmpty(mensaje))
            throw new IllegalArgumentException(mensaje);

        */return UsuarioRepositorio.obtenerPorId(Long.parseLong(idStr)).orElseThrow(() ->
                new IllegalArgumentException(/*MENSAJE_NO_SE_HA_ENCONTRADO_VENDEDOR.formatted(idStr)*/)
        );
    }





}
