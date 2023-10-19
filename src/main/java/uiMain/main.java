package uiMain;

import baseDatos.impl.Repositorio;
import gestorAplicacion.casoDeUso.*;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Carrito;

import java.util.Scanner;

public class main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("Bienvenido a e-commerce XYZ");
        menuLoop : do {
            System.out.println(getOpciones());
            String opcion = scanner.nextLine().trim();
            switch (opcion) { 
                case "1": productosIU.IU(scanner);
                    continue;
                case "3": CompradoresUI.IU(scanner);
                    continue;
                case "4":
                	continue;
                case "9": vendedoresIU.IU(scanner);
                    continue;
                case "10":
                    break menuLoop;
            }
        } while (true);
        System.out.println("Hasta luego 👋");
    }

    private static String getOpciones() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Realizar compra\n"
                + "2....\n"
                + "3 Menú compradores\n"
                +"4 Estadisticas\n"
                + "9 Gestión vendedores\n"
                + "10. Para cerrar la aplicación";
    }


}
