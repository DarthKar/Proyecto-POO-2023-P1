package uiMain;

import de.vandermeer.asciitable.AsciiTable;
import gestorAplicacion.casoDeUso.UsuarioCDU;
import gestorAplicacion.entidad.producto.Producto;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Vendedor;
import baseDatos.impl.Repositorio;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.vendedor.Publicacion;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.orden.Carrito;
import gestorAplicacion.entidad.usuario.tiposDeUsuario.comprador.ProductoTransaccion; 

import java.util.Scanner;
import java.util.ArrayList;

public class productosIU extends Repositorio {
    static Carrito carrito = new Carrito(Repositorio.obtenerCompradores().get(23));
    static ArrayList<Publicacion> puaux = new ArrayList<>();
    protected static void IU(Scanner scanner) {
        menuProductoLoop:
        do {
            System.out.println(pro());
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1":
                    for (int i = 0; i< Producto.getProductos().size() ; i++){
                        System.out.println((i+1)+". "+ Producto.getProductos().get(i).getNombre());
                    }
                    break;
                case "2":
                    System.out.println("Elija el producto que desea comprar");
                    int select = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println("Cuantas unidades desea comprar");
                    int cantidadDeseada = Integer.parseInt(scanner.nextLine().trim());
                    int contador = 1;
                    for (Vendedor ven : Repositorio.obtenerVendedores()){
                        for(Publicacion pu : ven.getPublicaciones()){
                            if (pu.getProducto() == Producto.getProductos().get(select) && pu.getInventario()>cantidadDeseada){
                            System.out.println(contador + ". " +pu.mostrarPublicacion()); 
                            puaux.add(pu);
                            contador++;
                            }else{System.out.println("El vendedor no tiene mas unidades de este producto");
                            }
                        }
                    }
                    System.out.println("Elija la publicacion que le llame la atencion");
                    int select1 = Integer.parseInt(scanner.nextLine().trim());
                    ProductoTransaccion compra = new ProductoTransaccion(puaux.get(select1), cantidadDeseada);
                    carrito.agregarProducto(compra);
                    break;
                case "3":
                    System.out.println("Elija el producto que desea eliminar del carrito");
                    int select2 = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println("Cuantas unidades desea eliminar");
                    int cantidadEl = Integer.parseInt(scanner.nextLine().trim());
                    if (cantidadEl == carrito.getProductosTransaccion().get(select2).getCantidad()){
                    carrito.removerProducto(carrito.getProductosTransaccion().get(select2));
                    }else{carrito.getProductosTransaccion().get(select2).setCantidad(carrito.getProductosTransaccion().get(select2).getCantidad()-cantidadEl);}
                    break;
                case "4":
                    int contador1 = 1;
                    for (ProductoTransaccion productosTransaccion : carrito.getProductosTransaccion()) {
                        System.out.println(contador1+". "+productosTransaccion.mostrarEspProducto());
                        contador1++;
                }
                    break;
                case "5":
                    System.out.println(opciones_5());
                    String opcion2 = scanner.nextLine().trim();
                    switch(opcion2){
                        case "1":
                            System.out.println("Seleccione que producto desea modificar");
                            int select3 = Integer.parseInt(scanner.nextLine().trim());
                            int Ncantidad = Integer.parseInt(scanner.nextLine().trim());
                            carrito.getProductosTransaccion().get(select3).setCantidad(Ncantidad);
                            break;
                        case "2":
                            carrito.getProductosTransaccion().clear();
                            System.out.println("Carrito correctamente baseado");
                            break;
                        case "3":
                            break menuProductoLoop;
                        default:
                            System.out.println("Opción inválida. Regresando al menú de carrito");
                            break; // Regresa automáticamente al menú principal
                    }
                    
                    continue;
                case "9":
                    break menuProductoLoop;
                case "10":
                    return;
                default:
                    System.out.println("Has elegido una opción invalida. Regresando al menú");
                    break;
            }
        }while(true);}
   
     
    
    private static String pro() {
        return "Seleccione una opcion\n"
                + "1. Mostrar lista de productos\n"
                + "2. Agregar productos al carrito\n"
                + "3. Eliminar productos del carrito\n"
                + "4. Mostrar carrito\n"
                 + "5. Modificar carrito\n"
                 + "9. Regresar a menu realizar compra\n"
                + "10. Volver al menu principal\n";
    }
    
    private static String opciones_5() {
        return "Que desea modificar?\n"
                + "1. Modificar cantidad de productos\n"
                + "2. Vaciar carrito completamente\n"
                + "10. Volver al menu principal\n";
    }
    
}
