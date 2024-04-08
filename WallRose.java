package itcr.tarea3poo;
// diamante relleno composicion, diamante hueco agregacion no se muy bien que hace cada uno pero diay
import itcr.tarea3poo.Clientes;
import itcr.tarea3poo.OrdenCompra;
import itcr.tarea3poo.Productos;
import itcr.tarea3poo.EstadoCompra;
import itcr.tarea3poo.LineaOrdenCompra;
import java.util.Scanner;
import java.util.ArrayList;

public class WallRose {
    private ArrayList<Clientes> listaClientes;
    private ArrayList<OrdenCompra> listaOrdenes;
    private ArrayList<Productos> listaProductos; 

    public WallRose(){
        this.listaClientes = new ArrayList<>(); //netbeans me recomendó usar new ArrayList<>() en vez de new ArrayList<Clientes>() no se por que
        this.listaOrdenes = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
    }

    private Clientes obtenerCliente(int numero) throws Exception {
        for (Clientes cliente : listaClientes){
            if (cliente.getNumero() == numero) {
                return cliente;
            }
        }
        throw new Exception("Numero de cliente no encontrado.");
    }

    private Productos obtenerProductos(int codigo) throws Exception{
        for (Productos producto : listaProductos){
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        throw new Exception("Codigo de producto no encontrado.");
    }

    private OrdenCompra obtenerOrdenCompra(int numero) throws Exception{ // ya genera la orden con el num de cliente
        for (OrdenCompra orden : listaOrdenes){
            if (orden.getNumero() == numero){
                return orden;
            }
        }
        throw new Exception("Numero de orden no encontrado.");
    }

    public void verListaClientes() {
        for (Clientes cliente : listaClientes) { // para que imprima todo
            System.out.println(cliente);
        }
    }

    public void verListaProductos() {
        for (Productos producto : listaProductos) { // para que imprima todo
            System.out.println(producto);
        }
    }

    public void verListaOrdenes() {
        for (OrdenCompra orden : listaOrdenes) { // para que imprima todo
            System.out.println(orden);
        }
    }
    
    public String verProducto(int codigoProducto) throws Exception{
        for (Productos producto : listaProductos){
            if (producto.getCodigo() == codigoProducto){
                return producto.getNombre();
            }
        }
        throw new Exception("Codigo de producto no encontrado.");
    }

    public int CrearProducto(String nombre, float precio, String unidad, float existencias) {
            Productos nuevoProducto = new Productos(nombre, precio, unidad, existencias);
            listaProductos.add(nuevoProducto);
            return nuevoProducto.getCodigo();
        }
    
    
    public int agregarCliente(String nombre, String email) { 
        Clientes nuevoCliente = new Clientes(nombre, email);
        listaClientes.add(nuevoCliente);
        return nuevoCliente.getNumero();
    }

    public ArrayList<OrdenCompra> obtenerOrdenesClienteTodas(int numeroCliente) throws Exception{ //terminar
        Clientes cliente = obtenerCliente(numeroCliente);
        if (cliente.getNumero() == numeroCliente){
            return cliente.obtenerOrdenesTodas();
        }
        throw new Exception("Numero de cliente no encontrado");
    }
            
    public ArrayList<OrdenCompra> obtenerOrdenesClienteIniciadas(int numeroCliente) throws Exception{ //terminar
        Clientes cliente = obtenerCliente(numeroCliente);
        if (cliente.getNumero() == numeroCliente){
            return cliente.obtenerOrdenesClienteIniciadas();
        }
        throw new Exception("Numero de cliente no encontrado");
    }
    
    public ArrayList<OrdenCompra> obtenerOrdenesClientePendientes(int numeroCliente) throws Exception{ //terminar
        Clientes cliente = obtenerCliente(numeroCliente);
        if (cliente.getNumero() == numeroCliente){
            return cliente.obtenerOrdenesClientePendientes();
        }
        throw new Exception("Numero de cliente no encontrado");
    }
    
    public ArrayList<OrdenCompra> obtenerOrdenesClienteCompletadas(int numeroCliente) throws Exception{ //terminar
        Clientes cliente = obtenerCliente(numeroCliente);
        if (cliente.getNumero() == numeroCliente){
            return cliente.obtenerOrdenesCompletadas();
        }
        throw new Exception("Numero de cliente no encontrado");
    }


    public int crearOrden(int numeroCliente) throws Exception {
        Clientes cliente = obtenerCliente(numeroCliente);
        OrdenCompra nuevaOrden = new OrdenCompra(cliente);
        listaOrdenes.add(nuevaOrden);
        return nuevaOrden.getNumero();
    }


    public void agregarLineaOrden(int numeroOrden, int codigoProducto, float cantidad) throws Exception {
        OrdenCompra orden = obtenerOrdenCompra(numeroOrden);
        Productos producto = null;
        for (Productos p : listaProductos) {
            if (p.getCodigo() == codigoProducto) {
                producto = p;
                break;
            }
        }

        if (producto == null) {
            throw new Exception("No existe un producto con ese código.");
        }

        if (producto.getExistencias() < cantidad) {
            throw new Exception("No hay suficientes existencias del producto.");
        }

        orden.agregarLinea(cantidad, producto);
        orden.setEstado(EstadoCompra.PENDIENTE);
        producto.setExistencias(producto.getExistencias() - cantidad);
    }


    public void EliminarLineaOrden(int numeroOrden, int indiceLinea) throws Exception {
        boolean ordenEncontrada = false;
        for (OrdenCompra orden : listaOrdenes) {
            if (orden.getNumero() == numeroOrden) {
                ordenEncontrada = true;
                
                System.out.println("Orden registrada en el sistema:");
                System.out.println(orden.toString());

                if (indiceLinea >= 0 && indiceLinea < orden.getLineas().size()) { // un toque confuso pero mientras el numero de indice este en el rango de la orden... 
                    LineaOrdenCompra linea = orden.getLineas().get(indiceLinea); //se encuentra la linea por borrar
                    Productos producto = linea.getProducto();
                    producto.agregarExistencias(linea.getCantidad()); //como se borra la linea la cantidad de productos en la orden, los productos disponibles aumentan
                    orden.getLineas().remove(indiceLinea); //finalmente se borra la linea
                    System.out.println("la linea " + indiceLinea + " se ha eliminado del numero de orden: " + numeroOrden);
                }
                else {
                    throw new Exception("El índice debe estar dentro de las líneas disponibles de la orden.");
                }   
            }
        }

        if (!ordenEncontrada) {
            throw new Exception("No se encontró el número de orden.");
        }
    }
    
    public void completarOrden(int numeroOrden) throws Exception {
        OrdenCompra orden = obtenerOrdenCompra(numeroOrden);
        if (orden != null) {
            orden.setEstado(EstadoCompra.COMPLETA);
            System.out.println("La orden número " + numeroOrden + " ha sido completada.");
        } else {
            throw new Exception("No se encontró el número de orden.");
        }
    }
       
    public void agregarExistenciasProducto(int codigoProducto, float nuevasExistencias) throws Exception {
        for (Productos producto : listaProductos){
            if (producto.getCodigo() == codigoProducto){
                producto.agregarExistencias(nuevasExistencias);
            }
        }
        throw new Exception("codigo de produco no encontrado.");
    }

} 
