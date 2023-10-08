package gestorAplicacion.casoDeUso;

import gestorAplicacion.entidad.producto.OpinionProducto;
import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.opinion.OpinionVendedor;

public class OpinionCDU {
    
    public OpinionCDU(){
       
    }
    
    public void crearOpinion(Comprador comprador, Producto producto, String comentario, int valoracion) { 
        if (producto != null && comprador != null) {
           for (Comprador comp : producto.getCompradores()) {
              if ( comp.equals(comprador)){  
                  if(producto.existeResena(comprador)==false){              
               OpinionProducto opinion = new OpinionProducto(comentario, valoracion,producto,comprador); // Metodo para crear una opinion de un producto
                opinion.setCreador(comprador);
                producto.addOpinionProducto(opinion);
                comprador.getResenasDeProductos().add(opinion);
            
            }
           }
          }
        }
      }
    
    public void editarOpinion(Comprador comprador, Producto producto,String comentario, int valoracion){
        if (producto !=null && comprador != null){
          if(producto.existeResena(comprador)==true){  
           for (OpinionProducto op : producto.getOpiniones()){          //edicion de opiniones 
                if (op.getCreador().equals(comprador)){
                   op.setOpinion(comentario);
                   op.setValoracion(valoracion);
                }
            
               
                   
               
            }     
          }
        }
    }
    
    public void borrarOpinion(Producto producto, Comprador comprador) {
        for (OpinionProducto op : producto.getOpiniones()){                 // Metodo para Borrar una opinion de un producto
            if ( op.getCreador().equals(comprador)){
                producto.getOpiniones().remove(op);
            }
        }
            
    }
    
    
    public void crearOpinion(Comprador comprador, Vendedor vendedor, String comentario, int valoracion) { 
        if (vendedor != null && comprador != null) {
           for (Comprador comp : vendedor.getCompradores()) {
              if ( comp.equals(comprador)){ 
                  if(vendedor.existeResena(comprador)==false){
                OpinionVendedor opinion = new OpinionVendedor(comentario, valoracion, comprador, vendedor); // Metodo para crear una opinion de un vendedor
                opinion.setCreador(comprador);
                vendedor.addOpinionVendedor(opinion);
                comprador.getResenasDeProductos().add(opinion);
                   }
                }   
            }    
        }
    }
    public void editarOpinion(Comprador comprador, Vendedor vendedor,String comentario, int valoracion){
        if (vendedor !=null && comprador != null ){
          if(vendedor.existeResena(comprador)==true){  
           for (OpinionVendedor op : vendedor.getOpinionVendedor()){        //edicion de opinones 
               if (op.getCreador().equals(comprador)){
                   op.setOpinion(comentario);
                   op.setValoracion(valoracion);
                   
               }    
            }     
          }
        }
    }
    public void borrarOpinion(Vendedor vendedor,Comprador comprador) {
        for (OpinionVendedor op : vendedor.getOpinionVendedor()){           // Metodo para Borrar una opinion de vendedor
            if ( op.getCreador().equals(comprador)){
                vendedor.getOpinionVendedor().remove(op);
            }
        }
            
    }
}

    
