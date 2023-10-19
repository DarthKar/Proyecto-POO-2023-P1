package uiMain;

import java.util.Scanner;

import gestorAplicacion.entidad.producto.Producto;
import uiMain.utilidades.Validaciones;

public class Estadistica extends Validaciones {
	 private static Scanner scanner;
public static void interfaz(Scanner scanner) {
	Estadistica.scanner=scanner;
	 
	do {
         System.out.println(getOpciones());
         int opcion;
         int opcion2;
         try{
             opcion2 = validarOpcionMenu(scanner.nextLine(), 1, 4);
         } catch (IllegalArgumentException e){
             System.out.println(e.getMessage());
             continue;
         }
         try{
             opcion = validarOpcionMenu(scanner.nextLine(), 1, 4);
         } catch (IllegalArgumentException e){
             System.out.println(e.getMessage());
             continue;
         }

         switch (opcion) {
             case 1:
            	 System.out.println("Bienvenido a las estadisticas generales del E-commerce, ingrese el numero de la estadistica que desea visualizar");             //  productoMasVendido();
                 switch(opcion2) {
                 case 1:
                	 System.out.println("1. Ventas totales en el e-commerce");
                	 break;
                 case 2:
                	 System.out.println("Cantidad total de devoluciones");
                	 continue;
                 }
            	 continue;
             default:
                 System.out.println("Ha elegido una opción invalida.");
         }
     } while (true);
	
}
private static void productoMasVendido() {
	// TODO Auto-generated method stub
	Producto producto=Producto.productoMasVendido();
	System.out.println("El producto mas vendido es %s que pertenece a la categoria %s ".formatted(producto.getNombre(),producto.getCategoria()));
}
private static String getOpciones() {
	// TODO Auto-generated method stub
	return "1. Estadisticas generales" ;
}
}
