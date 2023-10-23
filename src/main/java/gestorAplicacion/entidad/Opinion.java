package gestorAplicacion.entidad;
import gestorAplicacion.entidad.producto.OpinionProducto;
import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.opinion.OpinionVendedor;

public class Opinion {
    protected String opinion;
    protected int valoracion;
    protected Comprador creador;        // Creacion de atributo creador para poder diferenciar entre una opinion y otra, filtrar por edicion de opiniones
    static {
        
    }
   
    public Opinion(String opinion, int valoracion) {
        this.opinion = opinion;
        this.valoracion = valoracion;
    }

    public String getOpinion() {
        return opinion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
     public void setCreador(Comprador creador) {
        this.creador = creador;
    }                                                             //setter y getter de creador
     
    public Comprador getCreador() {
        return creador;
    }
         
    public String crearOpinion(Comprador comprador, Producto producto, String comentario, int valoracion) { 
        boolean existe=false;
            for (Comprador comp : producto.getCompradores()) {
              if ( comp.equals(comprador)){  
                  existe=true;
                  if(producto.existeResena(comprador)==false){              
               OpinionProducto opinion = new OpinionProducto(comentario, valoracion,producto,comprador); // Metodo para crear una opinion de un producto
                opinion.setCreador(comprador);
                producto.addOpinionProducto(opinion);
                comprador.getResenasDeProductos().add(opinion);
                producto.agregarResenador(comprador);
                return "Se ha creado la resena con exito";
            }
                return "Este usuario ya ha resenado este producto";
           }
          }
        if(!existe){
            return "el usuario no ha comprado el producto, no se puede crear una resena";
        }
        return "No se pudo realizar la accion";
    }
    
    
    public String editarOpinion(Comprador comprador, Producto producto,String comentario, int valoracion){
           if(producto.existeResena(comprador)){
            for (OpinionProducto op : producto.getOpiniones()){       //edicion de opiniones 
                if (op.getCreador().equals(comprador)){
                
                   op.setOpinion(comentario);
                   op.setValoracion(valoracion);
                   return "Opinion editada con exito";
                }  
               
            }     
        }
        return "Error: Este autor no tiene ninguna resena de este producto";
        }
    
    public OpinionProducto borrarOpinionProducto(Producto producto, Comprador comprador) {
        boolean opinionEncontrada=false;
        OpinionProducto opinionEliminar=null;
        for (OpinionProducto op : producto.getOpiniones()){                 // Metodo para Borrar una opinion de un producto
            if ( op.getCreador().equals(comprador)){
                opinionEliminar=op;
                opinionEncontrada=true;
                }

        }if(opinionEncontrada&&opinionEliminar!=null){
            return (opinionEliminar);
                
                }
            return null;
    }
    
    
    public  String crearOpinion(Comprador comprador, Vendedor vendedor, String comentario, int valoracion) { 
        boolean existe=false;
           for (Comprador comp : vendedor.getCompradores()) {
              if ( comp.equals(comprador)){ 
                  existe=true;
                  if(vendedor.existeResena(comprador)==false){
                OpinionVendedor opinion = new OpinionVendedor(comentario, valoracion, comprador, vendedor); // Metodo para crear una opinion de un vendedor
                opinion.setCreador(comprador);
                vendedor.addOpinionVendedor(opinion);
                comprador.getResenasDeProductos().add(opinion);
                vendedor.agregarResenador(comprador);
                   return "Se ha creado la resena con exito";
                   }
                   return "Este usuario ya ha resenado este vendedor";
                }   
            }    
        if(!existe){
            return "el usuario no es cliente del vendedor, no se puede crear una resena";
        }
        return "No se pudo realizar la accion";
    }
    
    public String editarOpinion(Comprador comprador, Vendedor vendedor,String comentario, int valoracion){
          if(vendedor.existeResena(comprador)==true){  
           for (OpinionVendedor op : vendedor.getOpinion()){        //edicion de opinones 
               if (op.getCreador().equals(comprador)){

                   op.setOpinion(comentario);
                   op.setValoracion(valoracion);
                   return "Opinion editada con exito";
                   
               }    
            }     
          }
        return "Error: Este autor no tiene ninguna resena de este vendedor";
    }
    public OpinionVendedor borrarOpinionVendedor(Vendedor vendedor,Comprador comprador) {
        boolean opinionEncontrada=false;
        OpinionVendedor opinionEliminar=null;
        for (OpinionVendedor op : vendedor.getOpinion()){           // Metodo para Borrar una opinion de vendedor
            if ( op.getCreador().equals(comprador)){
                if ( op.getCreador().equals(comprador)){
                    opinionEliminar=op;
                    opinionEncontrada=true;
                }

        }if(opinionEncontrada&&opinionEliminar!=null){
            return (opinionEliminar);
            }
            
        }return null;
            
    }
    @Override
    public String toString(){
        return "Autor: "+this.getCreador().getNombreCompleto()+"\n"
              +"Miembro: "+this.getCreador().getMembresia()+"\n\n"
                
                +"Comentario: "+this.getOpinion()+"\n"
                
                +"Valoracion: "+this.getValoracion()+"\n"
                +"----------------------------------------------------------------"
                +"";

                
        
    }
}

    



