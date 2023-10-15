package uiMain;

import baseDatos.impl.Repositorio;
import gestorAplicacion.entidad.Opinion;
import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
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
                    if (opcion1.equals(2)){
                        crearOpinionVendedor(scanner);
                    }
                    }

                    continue;

                case "2":
                    buscarVendedor(scanner);
                    continue;
                case "3":
                    guardarVendedor(scanner);
                    continue;
                case "4":
                    break menuOpinionLoop;
                default:
                    System.out.println("Has elegido una opciÃ³n invalida. Regresando al menÃº");
            }
        } while (true);
    }

    private static String getOpciones() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Crear opinion\n"
                + "2. Editar una reseña\n"
                + "3. Borrar una reseña\n";

    }

    private static String getCrearOpinionOpc() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Reseñar producto\n"
                + "2. Reseñar vendedor\n";

    }

    private static void crearOpinionProducto(Scanner scanner) {
        System.out.println("Ingresa el codigo del producto que quieres reseñar");
        String codigo = scanner.nextLine();
        List<Producto> bd = baseDatos.getProductos();
        long codigoNuevo = 0;

        try {
            codigoNuevo = Long.parseLong(codigo);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo válido.");
        }
    
            for (Producto producto : bd) {
                if (codigoNuevo == producto.getId()) {
                    System.out.println("Ingrese su comentario");
                    String com = scanner.nextLine();
                    int valoracion = 0;
            while(true){
                    System.out.println("Ingrese su valoracion del 1 al 5 (siendo 1 lo mas bajo)");
                    
                    
                    try {
                        valoracion = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                        
                    }

                    if (valoracion >= 1 && valoracion <= 5) {
                        
                        break;
                    }else{
                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                    }
            }

                Opinion op = new Opinion(com,valoracion);
                op.crearOpinion(comprador, producto, com, valoracion);
            }
                
    private static void crearOpinionVendedor(Scanner scanner) {
        System.out.println("Ingresa el codigo del vendedor que quieres reseñar");
        String codigo = scanner.nextLine();
        List<Vendedor> bd = baseDatos.getVendedores();
        long codigoNuevo = 0;

        try {
            codigoNuevo = Long.parseLong(codigo);

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un codigo válido.");
        }
    
            for (Vendedor vendedor : bd) {
                if (codigoNuevo == vendedor.getId()) {
                    System.out.println("Ingrese su comentario");
                    String com = scanner.nextLine();
                    int valoracion = 0;
            while(true){
                    System.out.println("Ingrese su valoracion del 1 al 5 (siendo 1 lo mas bajo)");
                    
                    
                    try {
                        valoracion = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                        
                    }

                    if (valoracion >= 1 && valoracion <= 5) {
                        
                        break;
                    }else{
                        System.out.println("La valoracion se mide del 1 al 5, digite un numero valido");
                    }
            }

                Opinion op = new Opinion(com,valoracion);
                op.crearOpinion(comprador, producto, com, valoracion);
            }
        }

    }
}
