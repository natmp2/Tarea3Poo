package itcr.tarea3poo.Logica;

import java.util.ArrayList;

public class Clientes {
    private static int consecutivo = 1;
    private int numero;
    private String nombre;
    private String email;
    private ArrayList<OrdenCompra> ordenes;

    public Clientes(String nombre, String email) {
        this.numero = consecutivo++;
        this.nombre = nombre;
        this.email = email;
        this.ordenes = new ArrayList();
    }
    
    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
    
    public Clientes getCliente() {
        return this;
    }
    
    public void agregarOrden(OrdenCompra orden){
        ordenes.add(orden);
    }
    
    public ArrayList<OrdenCompra> obtenerOrdenesTodas() { 
        return ordenes;
    }
 
    private ArrayList<OrdenCompra> obtenerOrdenesFiltradas(EstadoCompra estado) throws Exception{
        ArrayList<OrdenCompra> ordenesFiltradas = new ArrayList();
        for (OrdenCompra ordenF : ordenes){
            if (ordenF.getEstado() == estado){
                ordenesFiltradas.add(ordenF);
            }
            throw new Exception("obtenerOrdenesFiltradas"); //no se que fallaria aqui 
        }
        return ordenesFiltradas;
    }    
 
    public ArrayList<OrdenCompra> obtenerOrdenesClienteIniciadas() throws Exception{
        return obtenerOrdenesFiltradas(EstadoCompra.INICIADA);
    }

    public ArrayList<OrdenCompra> obtenerOrdenesClientePendientes() throws Exception {
        return obtenerOrdenesFiltradas(EstadoCompra.PENDIENTE);
    }

    public ArrayList<OrdenCompra> obtenerOrdenesCompletadas() throws Exception {
        return obtenerOrdenesFiltradas(EstadoCompra.COMPLETA);
    }

    @Override 
    public String toString() {
        String resultado = "Numero: " + numero + "\n";
        resultado += "Cliente: " + nombre + "\n";
        resultado += "Email: " + email + "\n";
        return resultado;
    }
}



