package uiMain;


import java.util.Scanner;

public class main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Bienvenido a e-commerce Chopee");

        menuLoop : do {
            System.out.println(getOpciones());
            String opcion = scanner.nextLine().trim();
            switch (opcion) { 
                case "1": productosIU.IU(scanner);
                    continue;
                case "2": DevolucionesUI.IU(scanner);
                    continue;
                case "3": opinionUI.IU(scanner);
                    continue;
                case "4":Estadistica.interfaz(scanner);
                    continue;
                case "5":
                    break menuLoop;
            }
        } while (true);
        System.out.println("Hasta luego que le vaya bien");
    }

    private static String getOpciones() {
        return "Selecciona una de las siguientes opciones\n"
                + "1. Realizar compra\n" 
                + "2. Realizar devolución\n"
                + "3. Opinar \n"
                + "4. Estadisticas\n"
                + "5. Para cerrar la aplicación";
    }


}
