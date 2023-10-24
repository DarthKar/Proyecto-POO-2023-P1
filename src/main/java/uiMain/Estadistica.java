package uiMain;

import java.util.Scanner;

import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Comprador;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.Membresia;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
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
				opcion = validarOpcionMenu(scanner.nextLine(), 1, 2);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}

			switch (opcion) {
			case 1:
				totalVentas();
				
				break estadisticas;
			case 2:
				productosVendidos();
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
				totalFactura();
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
		estadisticasVendedores:do {
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
				masVendedor();
				break estadisticasVendedores;
				
			case 2:
				masRecaudador();
				break estadisticasVendedores;
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
				opcion = validarOpcionMenu(scanner.nextLine(), 1, 3);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}

			switch (opcion) {
			case 1:
				productoMasCaro();
				break estadisticas;
			case 2:
				productoMasBarato();
				break estadisticas;
			case 3:
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
	private static void masRecaudador() {
		// TODO Auto-generated method stub
		String masRecaudo=Vendedor.mejorVendedorPorRecaudacion();
		System.out.println("El vendedor con la mayor recaudacion fue %s".formatted(masRecaudo));
	}
	private static void productoMasCaro() {
		// TODO Auto-generated method stub
		String caro=Producto.productoMasCaro();
		System.out.println("El producto mas caro de todo el e-commerce es %s".formatted(caro));	
		
	}
	private static void totalFactura() {
		String Factura=Comprador.masCompradorValor();
		System.out.println("El usuario que mas ha gastado de todo el e-commerce es %s".formatted(Factura));
		
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
	
	private static void masVendedor() {
		// TODO Auto-generated method stub
		String masVentas=Vendedor.mejorVendedor();
		System.out.println("El vendedor que mas vendio fue %s".formatted(masVentas));
	}
	
	private static void totalComprador() {
		//String compras=null;
		String compras=Comprador.masComprador();
		System.out.println("El Usuario con el mayor numero de productos comprados es %s".formatted(compras));
	}


	private static void productoMasVendido() {
		// TODO Auto-generated method stub
		Producto producto = Producto.productoMasVendido();
		System.out.println("El producto mas vendido es %s que pertenece a la categoria %s "
				.formatted(producto.getNombre(), producto.getCategoria()));
	}
	private static void productosVendidos() {
		// TODO Auto-generated method stub
		int producto = Producto.cantidadProductosVendidos();
		System.out.println("El total de productos que se vendieron fueron %s "
				.formatted(producto));
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
		"2. Total Productos vendidos";
	}
	private static String opcionesEstadisticaCompradores() {
		// TODO Auto-generated method stub
		return 
				
		"1. Usuario que mas productos ha comprado\n"+
		"2. Usuario que mas dinero ha gastado \n"+
		"3. Membresia mas comprada";
	}
	private static String opcionesEstadisticaVendedores() {
		// TODO Auto-generated method stub
		return 
				
		"1. Vendedor que mas productos vendió\n"+
		"2. Vendedor que mas dinero recaudó";
	}
	private static String opcionesEstadisticaProductos() {
		// TODO Auto-generated method stub
		return 
				
		"1. Producto mas caro\n"+
		"2. Producto mas barato\n"+
		"3. Producto mas vendido";
	}
}
