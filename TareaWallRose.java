// Tarea 3 POO
// Desarrollada por Felipe Benavides y Nathaniel Motykiewicz
package itcr.tarea3poo.Interfaz;

import itcr.tarea3poo.Control.WallRose;
import java.util.Scanner;

public class TareaWallRose {
    private static WallRose wallRose = new WallRose();
    private static Scanner scanner = new Scanner(System.in);
    
    private static void menuPrincipal() {
        
    }
    
    private static void menuClientes() {
        
    }
    
    private static void menuOrdenes() {
       
    }
    
    private static void menuProductos() {
        
    }
    
    private static void imprimirListaClientes() {
        System.out.println("Lista de clientes: ");
        wallRose.verListaClientes();
    }
    
    private static void imprimirListaOrdenes() {
        System.out.println("Lista de clientes: ");
        wallRose.verListaOrdenes();
    }
    
    private static void imprimirListaProductos() {
        System.out.println("Lista de clientes: ");
        wallRose.verListaProductos();
    }
    
    private static float leerFloat() {
        return scanner.nextFloat();
    }
    
    private static int leerInt() {
        return scanner.nextInt();
    }
    
    
    public static void main(String[] args) throws Exception {
        
        System.out.println("Lista de clientes:");
        imprimirListaClientes();
        
        System.out.println("Lista de productos:");
        imprimirListaProductos();
        
        System.out.println("Lista de ordenes:");
        imprimirListaOrdenes();
        
        
        // se crean los clientes
        int cliente1 = wallRose.agregarCliente("Felipe", "felipe@gmail.com");
        int cliente2 = wallRose.agregarCliente("Nathaniel", "nathaniel@gmail.com");
        int cliente3 = wallRose.agregarCliente("Mauricio", "mauricio@gmail.com");

        // se crean los productos
        int producto1 = wallRose.CrearProducto("Arroz", (float) 3.50, "Bolsa 1 kg", 150);
        int producto2 = wallRose.CrearProducto("Camisa de algodón", 20, "Unidad", 80);
        int producto3 = wallRose.CrearProducto("Juego de cuchillos", 35, "Set 5 piezas", 40);
        int producto4 = wallRose.CrearProducto("Taladro inalámbrico", 80, "unidad", 25);
        int producto5 = wallRose.CrearProducto("Smart TV de 55 pulgadas", 600, "Unidad", 15);

        // se crean las ordenes para cada cliente 
        int ordenCliente1 = wallRose.crearOrden(cliente1);
        wallRose.agregarLineaOrden(cliente1, producto1, 1); // cliente, producto, cantidad
        wallRose.agregarLineaOrden(cliente1, producto2, 2);
        wallRose.agregarLineaOrden(cliente1, producto3, 1);

        int ordenCliente2 = wallRose.crearOrden(cliente2);
        wallRose.agregarLineaOrden(cliente2, producto3, 2);
        wallRose.agregarLineaOrden(cliente2, producto4, 1);
        wallRose.agregarLineaOrden(cliente2, producto5, 2);
        
        int ordenCliente3 = wallRose.crearOrden(cliente3);
        wallRose.agregarLineaOrden(cliente3, producto1, 2);
        wallRose.agregarLineaOrden(cliente3, producto3, 3);
        wallRose.agregarLineaOrden(cliente3, producto5, 1);
        
        System.out.println("Lista de clientes:");
        imprimirListaClientes();
        
        System.out.println("Lista de productos:");
        imprimirListaProductos();
        
        System.out.println("Lista de ordenes:");
        imprimirListaOrdenes();
       
        wallRose.EliminarLineaOrden(ordenCliente1, 1);
        wallRose.obtenerOrdenesClienteCompletadas(cliente2);
        wallRose.obtenerOrdenesClienteIniciadas(cliente2);
        wallRose.obtenerOrdenesClientePendientes(cliente2);
        wallRose.obtenerOrdenesClienteTodas(cliente1);
        
        System.out.println("Lista de clientes:");
        imprimirListaClientes();
        
        System.out.println("Lista de productos:");
        imprimirListaProductos();
        
        System.out.println("Lista de ordenes:");
        imprimirListaOrdenes();
                
    }
    
}
