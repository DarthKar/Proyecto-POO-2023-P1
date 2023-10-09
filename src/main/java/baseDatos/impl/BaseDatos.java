package baseDatos.impl;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import gestorAplicacion.entidad.producto.Categoria;
import java.util.Collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos implements Serializable {

    private List<Vendedor> vendedores;
    private List<Comprador> compradores;
    private List<Producto> productos;

    public BaseDatos() {
        vendedores = new ArrayList<>();
        compradores = new ArrayList<>();
        productos = new ArrayList<>();
        this.crearDatosporDefecto();
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public List<Comprador> getCompradores() {
        return compradores;
    }

    public  List<Producto> getProductos(){
        return productos;
    }
    
    private void crearDatosporDefecto(){
        
        Producto p1 = new Producto(123L,"portatil", Categoria.ELECTRONICA);
        Producto p2 = new Producto(143L,"pc", Categoria.ELECTRONICA);
        Producto p3 = new Producto(234L,"arroz", Categoria.ALIMENTOS);
        Producto p4 = new Producto(355L,"tomate", Categoria.ALIMENTOS);
        Producto p5 = new Producto(478L,"maxstell", Categoria.JUGUETES);
        Producto p6 = new Producto(362L,"barbie", Categoria.JUGUETES);
        Producto p7 = new Producto(453L,"sueter", Categoria.ROPA);
        Producto p8 = new Producto(212L,"vaqueros", Categoria.ROPA);
        Producto p9 = new Producto(876L,"sofa", Categoria.HOGAR);
        Producto p10 = new Producto(232L,"silla", Categoria.HOGAR);
        Producto p11 = new Producto(345L,"labial", Categoria.COSMETICOS);
        Producto p12 = new Producto(124L,"delineador", Categoria.COSMETICOS);
        Producto p13 = new Producto(831L,"llanta", Categoria.OTROS);
        Producto p14 = new Producto(832L,"metronomo", Categoria.OTROS);
        
        Collections.addAll(productos,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14);
        
        Vendedor v1 = new Vendedor(123L,"Santiago","Palacios","SP123@");
        Vendedor v2 = new Vendedor(456L,"Jhon","Cano","JC456@");
        Vendedor v3 = new Vendedor(789L,"Jefersson","Lerma","JL789@");
        Vendedor v4 = new Vendedor(234L,"Lina","Monsalve","LM234@");
        Vendedor v5 = new Vendedor(567L,"Simon","Cadavid","SC567@");
        Vendedor v6 = new Vendedor(890L,"Ramon","Alegri","RA890@");
        Vendedor v7 = new Vendedor(321L,"Nikol","Miranda","NM321@");   
        
        Collections.addAll(vendedores,v1,v2,v3,v4,v5,v6,v7);
    }

}
