package uiMain;

import java.util.Scanner;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Membresia;
import uiMain.utilidades.Validaciones;

public class Estadistica extends Validaciones {
	private static Scanner scanner;

	public static void interfaz(Scanner scanner) {
		Estadistica.scanner = scanner;

		generales:do {
			System.out.println(getOpciones());
			int opcion;
			try {
				opcion = validarOpcionMenu(scanner.nextLine(), 1, 4);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}

			switch (opcion) {
			case 1:
				menuEstadisticasGenerales();
				break generales;
			case 2:
				menuEstadisticasCompradores();
				break generales;
			case 3:
				menuEstadisticasVendedores();
				break generales;	
			case 4:
				menuEstadisticasProductos();
			default:
				System.out.println("Ha elegido una opción invalida.");
			}
		} while (true);

	}

	private static void menuEstadisticasGenerales() {
		estadisticas:do {
			System.out.println("Bienvenido a las estadisticas generales del E-commerce, ingrese el numero de la estadistica que desea visualizar");
			System.out.println(opcionesEstadistica());
			int opcion;
			try {
				opcion = validarOpcionMenu(scanner.nextLine(), 1, 5);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}

			switch (opcion) {
			case 1:
				totalVentas();
				
				break estadisticas;
			case 2:
				System.out.println("estadisticas");
			case 5:
				break estadisticas;
			default:
				System.out.println("Ha elegido una opción invalida.");
			}
		} while (true);

	}

	
	private static void menuEstadisticasCompradores() {
		estadisticasCompradores:do {
			System.out.println("Bienvenido a las estadisticas de los compradores, ingrese el numero de la estadistica que desea visualizar");
			System.out.println(opcionesEstadisticaCompradores());
			int opcion;
			try {
				opcion = validarOpcionMenu(scanner.nextLine(), 1, 3);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}

			switch (opcion) {
			case 1:
				totalComprador();
				break estadisticasCompradores;
			case 2:
				break estadisticasCompradores;
			case 3:
				totalMembresias();
				break estadisticasCompradores;
			default:
				System.out.println("Ha elegido una opción invalida.");
			}
		} while (true);

	}
	private static void menuEstadisticasVendedores() {
		// TODO Auto-generated method stub
		estadisticas:do {
			System.out.println("Bienvenido a las estadisticas generales de los vendedores, ingrese el numero de la estadistica que desea visualizar");
			System.out.println(opcionesEstadisticaVendedores());
			int opcion;
			try {
				opcion = validarOpcionMenu(scanner.nextLine(), 1,3);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}

			switch (opcion) {
			case 1:
				totalVentas();
				
				break estadisticas;
			case 2:
				System.out.println("estadisticas");
			case 5:
				break estadisticas;
			default:
				System.out.println("Ha elegido una opción invalida.");
			}
		} while (true);

	}
		
	
	private static void menuEstadisticasProductos() {
		estadisticas:do {
			System.out.println("Bienvenido a las estadisticas generales de los productos, ingrese el numero de la estadistica que desea visualizar");
			System.out.println(opcionesEstadisticaProductos());
			int opcion;
			try {
				opcion = validarOpcionMenu(scanner.nextLine(), 1, 6);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}

			switch (opcion) {
			case 1:
				totalVentas();
				
				break estadisticas;
			case 2:
				System.out.println("estadisticas");
				break estadisticas;
			case 3:
				productoMasCaro();
				break estadisticas;
			case 4:
				productoMasBarato();
				break estadisticas;
			case 5:
				productoMasVendido();
				break estadisticas;
			default:
				System.out.println("Ha elegido una opción invalida.");
			}
		} while (true);

	}
	
	private static void productoMasBarato() {
		// TODO Auto-generated method stub
		String barato=Producto.productoMasBarato();
		System.out.println("El producto mas barato de todo el e-commerce es %s".formatted(barato));		
	}

	private static void productoMasCaro() {
		// TODO Auto-generated method stub
		String caro=Producto.productoMasCaro();
		System.out.println("El producto mas caro de todo el e-commerce es %s".formatted(caro));	
		
	}

	private static void totalMembresias() {
		// TODO Auto-generated method stub
		
		Membresia member=Comprador.MembresiaMasComprada();
		System.out.println("La membresia mas comprada es %s".formatted(member));
		
	}

	private static void totalVentas() {
		float ventas=Producto.ventasTotales();
		System.out.println("El e-commerce genero %s".formatted(ventas));
			
	}
	
	private static void totalComprador() {
		//String compras=null;
		String compras=Comprador.masComprador();
		System.out.println("El Usuario con la factura mas alta es %s".formatted(compras));
	}


	private static void productoMasVendido() {
		// TODO Auto-generated method stub
		Producto producto = Producto.productoMasVendido();
		System.out.println("El producto mas vendido es %s que pertenece a la categoria %s "
				.formatted(producto.getNombre(), producto.getCategoria()));
	}

	private static String getOpciones() {
		// TODO Auto-generated method stub
		return "1. Estadisticas Generales\n"+
		"2. Estadisticas Compradores\n"+
	    "3. Estadisticas Vendedores\n"+
		"4. Estadisticas Productos";
		
	}
	private static String opcionesEstadistica() {
		// TODO Auto-generated method stub
		return 
		"1. Total ventas e-commerce\n"+
		"2. Total devoluciones";
	}
	private static String opcionesEstadisticaCompradores() {
		// TODO Auto-generated method stub
		return 
				
		"1. Usuario que mas productos ha compado\n"+
		"2. Usuario mas comprador por valor\n"+
		"3. Membresia mas comprada";
	}
	private static String opcionesEstadisticaVendedores() {
		// TODO Auto-generated method stub
		return 
				
		"1. Vendedor con mejor valoracion\n"+
		"2. Vendedor que mas productos vendio\n"+
		"3. Vendedor con mayor cantidad de productos vendidos";
	}
	private static String opcionesEstadisticaProductos() {
		// TODO Auto-generated method stub
		return 
				
		"1. Productos mejor valoracion\n"+
		"2. Total productos sin vender\n"+
		"3. Producto mas caro\n"+
		"4. Producto mas barato\n"+
		"5. Producto mas vendido";
	}
}
