package gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor;

import gestorAplicacion.entidad.usuario.Usuario;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.opinion.OpinionVendedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vendedor extends Usuario {

    private List<ProductoVendedor> productosVendedor;
    private List<OpinionVendedor> opinionesVendedor;
    private List<Comprador> compradores;
    private List<Comprador> resenadores;


    public Vendedor(long id, String nombre, String apellido, String correo) {
        super(id, nombre, apellido, correo);
        this.productosVendedor = new ArrayList<>();
        this.opinionesVendedor = new ArrayList<>();
        this.compradores = new ArrayList<>(); // Nuevo array para verificar si el comprador que va a crear una opinion si ha comprado en esa tienda
        
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
    
      public boolean existeResena(Comprador comprador){
        return resenadores.contains(comprador);                  // Creacion del metodo ExisteResena que comprueba si ya hay una reseña
   
    }
    
    public List<Comprador> getCompradores(){
        return compradores;                                 // Getter y setter para esas listas
    }                                                                      

    public void setProductosVendedor(List<ProductoVendedor> productosVendedor) {
        this.productosVendedor = productosVendedor;
    }

    public void setOpinionesVendedor(List<OpinionVendedor> opinionesVendedor) {
        this.opinionesVendedor = opinionesVendedor;
    }

    public void setCompradores(List<Comprador> compradores) {
        this.compradores = compradores;
    }
    
    public void setResenadores(List<Comprador> resenadores) {
        this.resenadores = resenadores;
    }

    public List<Comprador> getResenadores() {
        return resenadores;
    }
    
    
}
