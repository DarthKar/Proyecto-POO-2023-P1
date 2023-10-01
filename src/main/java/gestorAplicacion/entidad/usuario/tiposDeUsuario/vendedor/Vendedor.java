package gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor;

import gestorAplicacion.entidad.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vendedor extends Usuario {

    private List<ProductoVendedor> productosVendedor;
    private List<OpinionVendedor> opinionesVendedor;

    public Vendedor(long id, String nombre, String apellido, String correo) {
        super(id, nombre, apellido, correo);
        this.productosVendedor = new ArrayList<>();
        this.opinionesVendedor = new ArrayList<>();
    }

    public List<ProductoVendedor> getProductoVendedor() {
        return productosVendedor;
    }

    public void addProductoVendedor(ProductoVendedor productoVendedor) {
        if(Objects.isNull(productoVendedor))
            return;
        productosVendedor.add(productoVendedor);
    }

    public List<OpinionVendedor> getOpinionVendedor() {
        return opinionesVendedor;
    }

    public void addOpinionVendedor(OpinionVendedor opinionVendedor) {
        if(Objects.isNull(opinionVendedor))
            return;
        opinionesVendedor.add(opinionVendedor);
    }
}
