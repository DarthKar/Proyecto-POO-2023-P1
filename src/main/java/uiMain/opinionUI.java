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
            System.out.println(getOpciones());
            String opcion = scanner.nextLine().trim();
            
            switch (opcion) {
                case "1":
                    System.out.println(getCrearOpinionOpc());
                    
                    String opcion1 = scanner.nextLine().trim();
                    
                    if (opcion1.equals(1)) {
                        crearOpinionProducto(scanner);
                    }
                    if (opcion1.equals(2)) {
                        crearOpinionVendedor(scanner);
                    }
                    if (opcion1.equals(3)){
                        continue;
                    }

                    continue;

                case "2":
                    System.out.println(getEditarOpc());

                    String opcion2 = scanner.nextLine().trim();
                    
                    if (opcion2.equals(1)) {
                        editarOpinionProducto(scanner);
                    }
                    if (opcion2.equals(2)) {
                        editarOpinionVendedor(scanner);
                    }
                    if(opcion2.equals(3)){
                        continue;
                    }
                    continue;
                    
                case "3":
                    System.out.println(getBorrarOpc());
                    
                    String opcion3 = scanner.nextLine().trim();
                    
                    if (opcion3.equals(1)){
                        borrarOpinionProducto(scanner);  
                    }
                    if (opcion3.equals(2)){
                        borrarOpinionVendedor(scanner);
                    }  
                    continue;
                    
                case "4":
                    
                    break menuOpinionLoop;
                default:
                    
                    System.out.println("Has elegido una opcion invalida. Regresando al menu");
            }
        } while (true);
    }
    
    

    private static String getOpciones() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Crear opinion\n"
                + "2. Editar una resena\n"
                + "3. Borrar una resena\n"
                + "4. Regresar al menu principal";

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

    
    
    
    private static void crearOpinionProducto(Scanner scanner) {
        System.out.println("Ingresa el codigo del producto que quieres resenar");
        String codigo = scanner.nextLine();
        System.out.println("Ingresa cedula del autor");
        String autor = scanner.nextLine();
        List<Producto> bd = baseDatos.getProductos();
        List<Comprador> bdC = baseDatos.getCompradores();
        long codigoNuevo = 0;
        long idAutor = 0;
        boolean compradorExiste = false;
        boolean productoExiste = false;
        try {
            codigoNuevo = Long.parseLong(codigo);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo valido.");
        }
        try {
            idAutor = Long.parseLong(autor);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero de cedula valido.");
        }

        for (Comprador co : bdC) {
            if (co.getId() == idAutor) {
                compradorExiste = true;

                for (Producto producto : bd) {
                    if (codigoNuevo == producto.getId()) {
                        productoExiste = true;
                        System.out.println("Ingrese su comentario");
                        String com = scanner.nextLine();
                        int valoracion = 0;
                        while (true) {
                            System.out.println("Ingrese su valoracion del 1 al 5 (siendo 1 lo mas bajo)");

                            try {
                                valoracion = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");

                            }

                            if (valoracion >= 1 && valoracion <= 5) {

                                break;
                            } else {
                                System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                            }
                        }

                        Opinion op = new Opinion(com, valoracion);
                        System.out.println(op.crearOpinion(co, producto, com, valoracion));
                    }
                    if (!productoExiste) {
                        System.out.println("Error: El producto no existe en la base de datos");
                    }
                }
            }
        }
        if (!compradorExiste) {
            System.out.println("el usuario no ha comprado el producto, no se puede crear una resena");
        }
    }
    
    

    private static void editarOpinionProducto(Scanner scanner) {
        System.out.println("Ingresa el codigo del producto del cual quieres editar una resena");
        String codigo = scanner.nextLine();
        List<Producto> bdP = baseDatos.getProductos();
        System.out.println("Ingrese la cedula del usuario autor de la resena");
        String id = scanner.nextLine();
        boolean resenadorExiste = false;
        long codigoNuevo = 0;
        long id1 = 0;
        try {
            id1 = Long.parseLong(id);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero de cedula valido.");
        }
        try {
            codigoNuevo = Long.parseLong(codigo);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo producto valido.");
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
                                int valoracion = 0;
                                while (true) {
                                    System.out.println("Ingrese la nueva valoracion del 1 al 5 (siendo 1 lo mas bajo)");

                                    try {
                                        valoracion = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");

                                    }

                                    if (valoracion >= 1 && valoracion <= 5) {

                                        break;
                                    } else {
                                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                                    }
                                    System.out.println(op.editarOpinion(co, producto, com, valoracion));
                                }

                            }
                        }

                    }

                }

            }
        }
        if (!resenadorExiste) {
            System.out.println("El usuario no ha hecho una resena");
        }
    }
    
    

    private static void crearOpinionVendedor(Scanner scanner) {
        System.out.println("Ingresa el codigo del vendedor que quieres resenar");
        String codigo = scanner.nextLine();
        System.out.println("Ingresa cedula del autor");
        String autor = scanner.nextLine();
        List<Vendedor> bd = baseDatos.getVendedores();
        List<Comprador> bdC = baseDatos.getCompradores();
        long codigoNuevo = 0;
        long idAutor = 0;
        boolean compradorExiste = false;
        boolean vendedorExiste = false;
        try {
            codigoNuevo = Long.parseLong(codigo);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo de vendedor valido.");
        }
        try {
            idAutor = Long.parseLong(autor);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero de cedula valido.");
        }

        for (Comprador co : bdC) {
            if (co.getId() == idAutor) {
                compradorExiste = true;

                for (Vendedor vend : bd) {
                    if (codigoNuevo == vend.getId()) {
                        vendedorExiste = true;
                        System.out.println("Ingrese su comentario");
                        String com = scanner.nextLine();
                        int valoracion = 0;
                        while (true) {
                            System.out.println("Ingrese su valoracion del 1 al 5 (siendo 1 lo mas bajo)");

                            try {
                                valoracion = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");

                            }

                            if (valoracion >= 1 && valoracion <= 5) {

                                break;
                            } else {
                                System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                            }
                        }

                        Opinion op = new Opinion(com, valoracion);
                        System.out.println(op.crearOpinion(co, vend, com, valoracion));
                    }
                    if (!vendedorExiste) {
                        System.out.println("Error: El vendedor no existe en la base de datos");
                    }
                }
            }
        }
        if (!compradorExiste) {
            System.out.println("el usuario no ha comprado el producto, no se puede crear una resena");
        }
    }
    
    
    
     private static void editarOpinionVendedor(Scanner scanner) {
        System.out.println("Ingresa el codigo del producto del cual quieres editar una resena");
        String codigo = scanner.nextLine();
        List<Vendedor> bdV = baseDatos.getVendedores();
        System.out.println("Ingrese la cedula del usuario autor de la resena");
        String id = scanner.nextLine();
        boolean resenadorExiste = false;
        long codigoNuevo = 0;
        long id1 = 0;
        try {
            id1 = Long.parseLong(id);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero de cedula valido.");
        }
        try {
            codigoNuevo = Long.parseLong(codigo);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo producto valido.");
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
                                int valoracion = 0;
                                while (true) {
                                    System.out.println("Ingrese la nueva valoracion del 1 al 5 (siendo 1 lo mas bajo)");

                                    try {
                                        valoracion = Integer.parseInt(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");

                                    }

                                    if (valoracion >= 1 && valoracion <= 5) {

                                        break;
                                    } else {
                                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                                    }
                                    System.out.println(op.editarOpinion(co, vendedor, com, valoracion));
                                }

                            }
                        }

                    }

                }

            }
        }
        if (!resenadorExiste) {
            System.out.println("El usuario no ha hecho una resena");
        }
    }
     
     
     private static void borrarOpinionProducto(Scanner scanner){
         System.out.println("Ingresa el codigo del producto del cual quieres elliminar una resena");
         String codigo = scanner.nextLine();
         List<Producto> bdP = baseDatos.getProductos();
         List<Comprador> bdC = baseDatos.getCompradores();
         System.out.println("Ingrese la cedula del usuario autor de la resena");
         String id = scanner.nextLine();
         boolean productoEncontrado=false;
         boolean AutorEncontrado=false;
                 
         long codigoNuevo=0;
         long idAutor=0;
         
        try {
            codigoNuevo = Long.parseLong(codigo);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo de producto valido.");
        }
        try {
            idAutor = Long.parseLong(id);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero de cedula valido.");
        }
        for(Producto producto: bdP){
            if(producto.getId()==codigoNuevo){
                productoEncontrado=true;
                
                 for(Comprador comprador : bdC){
                     if(comprador.getId()==idAutor){
                        AutorEncontrado=true;
                        
                        for(OpinionProducto op : producto.getOpiniones()){
                            if(op.getCreador().getId()==idAutor){
                                System.out.println(op.borrarOpinionProducto(producto,comprador));
                               
                            }
                        }
                     }
                 }if(!AutorEncontrado){
                     System.out.println("Error: No se ha encontrado el autor que buscas");
                 }
            }if(!productoEncontrado){
                System.out.println("Error: no se ha encontrado el producto que buscas");
            }
                
        }
        
     }
     
     
     
     private static void borrarOpinionVendedor(Scanner scanner){
         System.out.println("Ingresa el codigo del producto del cual quieres elliminar una resena");
         String codigo = scanner.nextLine();
         List<Vendedor> bdV = baseDatos.getVendedores();
         List<Comprador> bdC = baseDatos.getCompradores();
         System.out.println("Ingrese la cedula del usuario autor de la resena");
         String id = scanner.nextLine();
         boolean vendedorEncontrado=false;
         boolean AutorEncontrado=false;
                 
         long codigoNuevo=0;
         long idAutor=0;
         
        try {
            codigoNuevo = Long.parseLong(codigo);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo de vendedor valido.");
        }
        try {
            idAutor = Long.parseLong(id);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un numero de cedula valido.");
        }
        for(Vendedor vendedor: bdV){
            if(vendedor.getId()==codigoNuevo){
                vendedorEncontrado=true;
                
                 for(Comprador comprador : bdC){
                     if(comprador.getId()==idAutor){
                        AutorEncontrado=true;
                        
                        for(OpinionVendedor op : vendedor.getOpinion()){
                            if(op.getCreador().getId()==idAutor){
                                System.out.println(op.borrarOpinionVendedor(vendedor,comprador));
                            }
                        }
                     }
                 }if(!AutorEncontrado){
                     System.out.println("Error: No se ha encontrado el autor que buscas");
                 }
            }if(!vendedorEncontrado){
                System.out.println("Error: no se ha encontrado el producto que buscas");
            }
                
        }
        
     }
}