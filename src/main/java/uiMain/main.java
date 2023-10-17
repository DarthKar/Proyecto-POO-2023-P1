package uiMain;

import baseDatos.impl.Repositorio;


import java.util.Scanner;

public class main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Bienvenido a e-commerce Choope");
        menuLoop : do {
            System.out.println(getOpciones());
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1", "2":
                    continue;
                case "3": CompradoresUI.IU(scanner);
                    continue;
                case "4": opinionUI.IU(scanner);
                    continue;
                case "9": vendedoresIU.IU(scanner);
                    continue;
                case "10":
                    break menuLoop;
            }
        } while (true);
        System.out.println("Hasta luego que le vaya bien");
    }

    private static String getOpciones() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Realizar compra\n"
                + "2....\n"
                + "3 Menú compradores\n"
                + "4. Opinar \n"
                + "5. Estadisticas\n"
                + "9 Gestión vendedores\n"
                + "10. Para cerrar la aplicación";
    }


}
