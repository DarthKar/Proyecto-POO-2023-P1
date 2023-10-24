package uiMain;

import baseDatos.impl.Repositorio;
import gestorAplicacion.entidad.Opinion;
import gestorAplicacion.entidad.producto.OpinionProducto;
import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.opinion.OpinionVendedor;
import java.util.List;
import java.util.Scanner;

public class opinionUI extends Repositorio {

    protected static void IU(Scanner scanner) {
        
        menuOpinionLoop:
        do {
            casoPrueba();
            System.out.println(getOpciones());
            String opcion = scanner.nextLine().trim();
            
            switch (opcion) {
                case "1":
                    System.out.println(getCrearOpinionOpc());
                    
                    String opcion1 = scanner.nextLine().trim();
                    
                    switch(opcion1){ 

                    case "1":
                        crearOpinionProducto(scanner);
                        break;
                    

                    case "2":
                        crearOpinionVendedor(scanner);
                        break;
                    
                    
                    case"3":
                        break;
                    

                }
                    continue menuOpinionLoop;

                case "2":
                    System.out.println(getEditarOpc());

                    String opcion2 = scanner.nextLine().trim();
                    
                    switch(opcion2){
                        
                        case"1":
                        editarOpinionProducto(scanner);
                            break;
                    
                    
                    
                        case"2":
                            editarOpinionVendedor(scanner);
                            break;
                    
                        case"3":
                            break;


                    }
                    
                        continue menuOpinionLoop;
                    
                case "3":
                    System.out.println(getBorrarOpc());
                    
                    String opcion3 = scanner.nextLine().trim();
                    
                    switch (opcion3){
                        case "1":

                            borrarOpinionProducto(scanner);  
                            break;
                    
                        case "2":

                            borrarOpinionVendedor(scanner);
                            break;
                    }  
                        continue menuOpinionLoop;
                    
                case "4":
                    System.out.println(getVerOpinionOpc());
                    
                    String opcion4 = scanner.nextLine().trim();
                    
                    switch(opcion4) {
                        case "1":

                            verOpinionProducto(scanner);
                            break;
                    
                    
                        case "2":
                        
                            verOpinionVendedor(scanner);
                            break;
                    
                    
                        case "3":
                        
                          break;
                    }
                    continue menuOpinionLoop;
                    
                case "5":
                    
                    break menuOpinionLoop;
                default:
                    
                    System.out.println("Has elegido una opcion invalida. Regresando al menu");

                    continue menuOpinionLoop;
            }
        } while (true);
    }
    
    static void casoPrueba(){
   Producto productoPrueba = baseDatos.getProductos().get(0);
   Comprador compradorPrueba = baseDatos.getCompradores().get(35);
   productoPrueba.agregarComprador(compradorPrueba);
   
   Vendedor vendedorPrueba = baseDatos.getVendedores().get(0);
   vendedorPrueba.agregarComprador(compradorPrueba);


 }    

    private static String getOpciones() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Crear opinion\n"
                + "2. Editar una resena\n"
                + "3. Borrar una resena\n"
                + "4. Ver opiniones\n"
                + "5. Regresar al menu principal";

    }
    
    

    private static String getCrearOpinionOpc() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Resenar producto\n"
                + "2. Resenar vendedor\n"
                + "3. Regresar";

    }
    
    

    private static String getEditarOpc() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Editar resena de un producto\n"
                + "2. Editar resenar de un vendedor\n"
                + "3. Regresar";

    }
    
    
    
    private static String getBorrarOpc(){
        return "Selecciona una de las siguientes opciones\n"
                + "1. Borrar resena de un producto\n"
                + "2. Borrar resenar de un vendedor\n"
                + "3. Regresar";
    }
    
    private static String getVerOpinionOpc(){
        return "Selecciona una de las siguientes opciones\n"
                + "1. Ver resenas de un producto\n"
                + "2. Ver resenas de un vendedor\n"
                + "3. Regresar";
    }
    
    
    
    private static void crearOpinionProducto(Scanner scanner) {
    System.out.println("Ingresa el codigo del producto que quieres resenar");
    String codigo = scanner.nextLine();
    System.out.println("---------------------------------------------------------------");
    System.out.println("Ingresa id del autor");
    String autor = scanner.nextLine();
    System.out.println("---------------------------------------------------------------");

    long codigoNuevo;
    long idAutor;

    try {
        codigoNuevo = Long.parseLong(codigo);
        idAutor = Long.parseLong(autor);
    } catch (NumberFormatException e) {
        System.out.println("Error: Ingresa un codigo y una id válidos.");
        System.out.println("---------------------------------------------------------------");
        return;
    }

    List<Producto> bd = baseDatos.getProductos();
    List<Comprador> bdC = baseDatos.getCompradores();
    boolean compradorExiste = false;
    boolean productoExiste = false;


    for (Comprador co : bdC) {
       
        if (co.getId() == idAutor) {
            compradorExiste = true;

            for (Producto producto : bd) {
              
                if (codigoNuevo == producto.getId()) {
                    productoExiste = true;
                    System.out.println("Ingrese su comentario");
                    String com = scanner.nextLine();
                    System.out.println("---------------------------------------------------------------");
                    int valoracion = 0;
                
                    while (true) {
                        System.out.println("Ingrese su valoracion del 1 al 5 (siendo 1 lo mas bajo)");
                        

                        try {
                            valoracion = Integer.parseInt(scanner.nextLine());
                            System.out.println("---------------------------------------------------------------");
                        } catch (NumberFormatException ex) {
                            System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                            System.out.println("---------------------------------------------------------------");
                            continue;
                        }

                        if (valoracion >= 1 && valoracion <= 5) {
                            break;
                        } else {
                            System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                            System.out.println("---------------------------------------------------------------");
                        }
                    }

                    Opinion op = new Opinion(com, valoracion);
                    System.out.println(op.crearOpinion(co, producto, com, valoracion));
                    System.out.println("---------------------------------------------------------------");
                    
                }
            }
        }
    }

    if (!compradorExiste) {
        System.out.println("Error: El usuario no existe en la base de datos.");
        System.out.println("---------------------------------------------------------------");
    }

    if (!productoExiste) {
        System.out.println("Error: El producto no existe en la base de datos.");
        System.out.println("---------------------------------------------------------------");
    }
}

    

    private static void editarOpinionProducto(Scanner scanner) {
        System.out.println("Ingresa el codigo del producto del cual quieres editar una resena");
        String codigo = scanner.nextLine();
        System.out.println("---------------------------------------------------------------");
        List<Producto> bdP = baseDatos.getProductos();
        System.out.println("Ingrese la id del usuario autor de la resena");
        String id = scanner.nextLine();
        System.out.println("---------------------------------------------------------------");
        boolean resenadorExiste = false;
        long codigoNuevo = 0;
        long id1 = 0;
        try {
            id1 = Long.parseLong(id);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero de id valido.");
            System.out.println("---------------------------------------------------------------");
        }
        try {
            codigoNuevo = Long.parseLong(codigo);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo producto valido.");
            System.out.println("---------------------------------------------------------------");
        }

        for (Producto producto : bdP) {
            if (codigoNuevo == producto.getId()) {

                for (Comprador co : producto.getResenadores()) {
                    if (co.getId() == id1) {
                        resenadorExiste = true;

                        for (OpinionProducto op : producto.getOpiniones()) {
                            if (co.getId() == op.getCreador().getId()) {
                                System.out.println("Ingrese el comentario nuevo");
                                String com = scanner.nextLine();
                                System.out.println("---------------------------------------------------------------");
                                int valoracion = 0;
                                while (true) {
                                    System.out.println("Ingrese la nueva valoracion del 1 al 5 (siendo 1 lo mas bajo)");
                                    

                                    try {
                                        valoracion = Integer.parseInt(scanner.nextLine());
                                        System.out.println("---------------------------------------------------------------");
                                    } catch (NumberFormatException e) {
                                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                                        System.out.println("---------------------------------------------------------------");

                                    }

                                    if (valoracion >= 1 && valoracion <= 5) {

                                        break;
                                    } else {
                                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                                        System.out.println("---------------------------------------------------------------");
                                    }
                                    
                                    
                                }   System.out.println(op.editarOpinion(co, producto, com, valoracion));
                                    System.out.println("---------------------------------------------------------------");

                            }
                        }

                    }

                }

            }
        }
        if (!resenadorExiste) {
            System.out.println("El usuario no ha hecho una resena");
            System.out.println("---------------------------------------------------------------");
        }
    }
    
    
    private static void crearOpinionVendedor(Scanner scanner) {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Ingresa el código del vendedor que quieres reseñar");
        String codigo = scanner.nextLine();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Ingresa cédula del autor");
        String autor = scanner.nextLine();
        System.out.println("---------------------------------------------------------------");
    
        long codigoNuevo;
        long idAutor;
    
        try {
            codigoNuevo = Long.parseLong(codigo);
            idAutor = Long.parseLong(autor);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un código y una cédula válidos.");
            System.out.println("---------------------------------------------------------------");
            return;
        }
    
        List<Vendedor> bd = baseDatos.getVendedores();
        List<Comprador> bdC = baseDatos.getCompradores();
        boolean compradorExiste = false;
        boolean vendedorExiste = false;
    
        for (Comprador co : bdC) {
            if (co.getId() == idAutor) {
                compradorExiste = true;
    
                for (Vendedor vend : bd) {
                    if (codigoNuevo == vend.getId()) {
                        vendedorExiste = true;
                        System.out.println("Ingrese su comentario");
                        String com = scanner.nextLine();
                        System.out.println("---------------------------------------------------------------");
                        

                        int valoracion = 0;
    
                        while (true) {
                            
                            System.out.println("Ingrese su valoración del 1 al 5 (siendo 1 lo más bajo)");
    
                            try {
                                valoracion = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException ex) {
                                System.out.println("La valoración se mide del 1 al 5, digite un número válido");
                                System.out.println("---------------------------------------------------------------");
                                continue;
                            }
    
                            if (valoracion >= 1 && valoracion <= 5) {
                                break;
                            } else {
                                System.out.println("La valoración se mide del 1 al 5, digite un número válido");
                                System.out.println("---------------------------------------------------------------");
                            }
                        }
    
                        Opinion op = new Opinion(com, valoracion);
                        System.out.println(op.crearOpinion(co, vend, com, valoracion));
                        System.out.println("---------------------------------------------------------------");
                    }
                }
    
                if (!vendedorExiste) {
                    System.out.println("Error: El vendedor no existe en la base de datos");
                    System.out.println("---------------------------------------------------------------");
                }
            }
        }
    
        if (!compradorExiste) {
            System.out.println("El usuario no es cliente del vendedor, no se puede crear una reseña.");
            System.out.println("---------------------------------------------------------------");
        }
    }
    
    
    
     private static void editarOpinionVendedor(Scanner scanner) {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Ingresa el codigo del vendedor del cual quieres editar una resena");
        String codigo = scanner.nextLine();
        List<Vendedor> bdV = baseDatos.getVendedores();
        System.out.println("---------------------------------------------------------------");
        System.out.println("Ingrese la id del usuario autor de la resena");
        String id = scanner.nextLine(); System.out.println("---------------------------------------------------------------");
        boolean resenadorExiste = false;
        long codigoNuevo = 0;
        long id1 = 0;
        try {
            id1 = Long.parseLong(id);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero de id valido.");
            System.out.println("---------------------------------------------------------------");
        }
        try {
            codigoNuevo = Long.parseLong(codigo);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo producto valido.");
            System.out.println("---------------------------------------------------------------");
        }

        for (Vendedor vendedor : bdV) {
            if (codigoNuevo == vendedor.getId()) {

                for (Comprador co : vendedor.getResenadores()) {
                    if (co.getId() == id1) {
                        resenadorExiste = true;

                        for (OpinionVendedor op : vendedor.getOpinion()) {
                            if (co.getId() == op.getCreador().getId()) {
                                
                                System.out.println("Ingrese el comentario nuevo");
                                String com = scanner.nextLine();
                                System.out.println("---------------------------------------------------------------");
                                int valoracion = 0;
                                while (true) {
                                    System.out.println("Ingrese la nueva valoracion del 1 al 5 (siendo 1 lo mas bajo)");
                                    

                                    try {
                                        valoracion = Integer.parseInt(scanner.nextLine());
                                        System.out.println("---------------------------------------------------------------");
                                    } catch (NumberFormatException e) {
                                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                                        System.out.println("---------------------------------------------------------------");

                                    }

                                    if (valoracion >= 1 && valoracion <= 5) {

                                        break;
                                    } else {
                                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                                        System.out.println("---------------------------------------------------------------");
                                    }
                                 
                                    
                                }
                                System.out.println(op.editarOpinion(co, vendedor, com, valoracion));
                                    System.out.println("---------------------------------------------------------------");
                            
                            }
                        }

                    }

                }

            }
        }
        if (!resenadorExiste) {
            System.out.println("El usuario no ha hecho una resena");
            System.out.println("---------------------------------------------------------------");
        }
    }
     
     
     private static void borrarOpinionProducto(Scanner scanner){
         System.out.println("Ingresa el codigo del producto del cual quieres elliminar una resena");
         String codigo = scanner.nextLine();
         System.out.println("---------------------------------------------------------------");
         List<Producto> bdP = baseDatos.getProductos();
         List<Comprador> bdC = baseDatos.getCompradores();
         System.out.println("Ingrese la id del usuario autor de la resena");
         String id = scanner.nextLine();
         System.out.println("---------------------------------------------------------------");
         boolean productoEncontrado=false;
         boolean AutorEncontrado=false;
                 
         long codigoNuevo=0;
         long idAutor=0;
         
        try {
            codigoNuevo = Long.parseLong(codigo);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo de producto valido.");
            System.out.println("---------------------------------------------------------------");
        }
        try {
            idAutor = Long.parseLong(id);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero de id valido.");
            System.out.println("---------------------------------------------------------------");
        }
        for(Producto producto: bdP){
            if(producto.getId()==codigoNuevo){
                productoEncontrado=true;
                
                 for(Comprador comprador : bdC){
                     if(comprador.getId()==idAutor){
                        AutorEncontrado=true;
                        
                        for(OpinionProducto op : producto.getOpiniones()){
                            if(op.getCreador().getId()==idAutor){
                                
                              OpinionProducto opinionBorrar = op.borrarOpinionProducto(producto,comprador);
                               
                               if(opinionBorrar!=null){
                                producto.getOpiniones().remove(opinionBorrar);
                                producto.getResenadores().remove(comprador);
                                System.out.println("Resena borrada con exito, volviendo al menu principal");
                                System.out.println("---------------------------------------------------------------");
                                break;
                               }
                               
                            }
                        }
                     }
                 }if(!AutorEncontrado){
                     System.out.println("Error: No se ha encontrado el autor que buscas");
                     System.out.println("---------------------------------------------------------------");
                 }
            }
            }if(!productoEncontrado){
                System.out.println("Error: no se ha encontrado el producto que buscas");
                System.out.println("---------------------------------------------------------------");
                
        }
        
     }
     
     
     
     private static void borrarOpinionVendedor(Scanner scanner){
         System.out.println("Ingresa el codigo del vendedor del cual quieres elliminar una resena");
         String codigo = scanner.nextLine();
         System.out.println("---------------------------------------------------------------");
         List<Vendedor> bdV = baseDatos.getVendedores();
         List<Comprador> bdC = baseDatos.getCompradores();
         System.out.println("Ingrese la id del usuario autor de la resena");
         String id = scanner.nextLine();
         System.out.println("---------------------------------------------------------------");
         boolean vendedorEncontrado=false;
         boolean AutorEncontrado=false;
                 
         long codigoNuevo=0;
         long idAutor=0;
         
        try {
            codigoNuevo = Long.parseLong(codigo);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo de vendedor valido.");
            System.out.println("---------------------------------------------------------------");
        }
        try {
            idAutor = Long.parseLong(id);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero de id valido.");
            System.out.println("---------------------------------------------------------------");
        }
        for(Vendedor vendedor: bdV){
            if(vendedor.getId()==codigoNuevo){
                vendedorEncontrado=true;
                
                 for(Comprador comprador : bdC){
                     if(comprador.getId()==idAutor){
                        AutorEncontrado=true;
                        
                        for(OpinionVendedor op : vendedor.getOpinion()){
                            if(op.getCreador().getId()==idAutor){
                                OpinionVendedor opinionBorrar = op.borrarOpinionVendedor(vendedor,comprador);
                               
                               if(opinionBorrar!=null){
                                vendedor.getOpinion().remove(opinionBorrar);
                                vendedor.getResenadores().remove(comprador);
                                System.out.println("Resena borrada con exito, volviendo al menu principal");
                                System.out.println("---------------------------------------------------------------");
                                break;
                            }
                        }
                     }
                 }
            }
             if(!AutorEncontrado){
                     System.out.println("Error: No se ha encontrado el autor que buscas");
                     System.out.println("---------------------------------------------------------------");
                 }
     }if(!vendedorEncontrado){
                System.out.println("Error: no se ha encontrado el producto que buscas"); 
                System.out.println("---------------------------------------------------------------"); 
    }
}
     }
     
     private static void verOpinionProducto(Scanner scanner){
         System.out.println("Ingresa el codigo del producto del cual quieres ver opiniones");
         String codigo = scanner.nextLine();
         System.out.println("---------------------------------------------------------------");
         List<Producto> bdP = baseDatos.getProductos();
         boolean productoEncontrado= false;
         
         long codigoNuevo=0;
         
         try{
             
             codigoNuevo = Long.parseLong(codigo);
             
         } catch(NumberFormatException e ) {
             System.out.println("Error Ingresa un codigo de producto valido");
             System.out.println("---------------------------------------------------------------");
             
         }
         
         for (Producto producto : bdP){
             if(producto.getId()==codigoNuevo){
                  productoEncontrado=true;
                if(producto.getOpiniones().isEmpty()==false){ 
                    
                for(OpinionProducto op: producto.getOpiniones()){ 
                    
                    System.out.println(op.toString());
                    
                }
                }
                if(producto.getOpiniones().isEmpty()){
                    
                    System.out.println("El producto aun no tiene resenas"); 
                    System.out.println("---------------------------------------------------------------");  
             
             }
         }
         }
        if(!productoEncontrado){

                 System.out.println("Error: No se ha encontrado el producto que buscas");
                 System.out.println("---------------------------------------------------------------");
        }
                 
     }
     private static void verOpinionVendedor(Scanner scanner){
         System.out.println("Ingresa el codigo del vendedor del cual quieres ver opiniones");
         String codigo = scanner.nextLine();
         System.out.println("---------------------------------------------------------------");
         List<Vendedor> bdV = baseDatos.getVendedores();
         boolean vendedorEncontrado= false;
         
         long codigoNuevo=0;
         
         try{
             
             codigoNuevo = Long.parseLong(codigo);
             
         } catch(NumberFormatException e ) {
             System.out.println("Error Ingresa un codigo de producto valido");
             System.out.println("---------------------------------------------------------------");
             
         }
         
         for (Vendedor vendedor : bdV){
             if(vendedor.getId()==codigoNuevo){
                  vendedorEncontrado=true;
               if(vendedor.getOpinion().isEmpty()==(false)){
                   
                for(OpinionVendedor op: vendedor.getOpinion()){ 
                   
                    System.out.println(op.toString());
                    
                }   
             }if(vendedor.getOpinion().isEmpty()){
                 System.out.println("El vendedor aun no tiene resenas  ");
                 System.out.println("---------------------------------------------------------------");
             }
         }
            }if(!vendedorEncontrado){
             System.out.println("Error: No se ha encontrado el vendedor que buscas");
             System.out.println("---------------------------------------------------------------");
         
        }
    }
}
