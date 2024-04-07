package itcr.tarea3poo.Logica;

import java.util.ArrayList;

public class Productos {
    private static int consecutivo = 1;
    private int codigo;
    private String nombre;
    private float precio;
    private String unidad;
    private float existencias;

    public Productos(String nombre, float precio, String unidad, float existencias) {
        this.codigo = consecutivo++;
        this.nombre = nombre;
        this.precio = precio;
        this.unidad = unidad;
        this.existencias = existencias;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public String getUnidad() {
        return unidad;
    }

    public float getExistencias() {
        return existencias;
    }

    public void setExistencias(float existencias) {
        this.existencias = existencias;
    }

    
    public void retirarExistencias(float cantidad) throws Exception {
        if (cantidad > existencias){
            throw new Exception("no hay suficientes existencias");
        }
        existencias -= cantidad;
    }
    
    public void agregarExistencias(float cantidad) {
        existencias += cantidad;
    }
    
    @Override 
    public String toString() {
        String resultado = "Código: " + codigo + "\n";
        resultado += "Nombre: " + nombre + "\n";
        resultado += "Precio: " + precio + "\n";
        resultado += "Unidad: " + unidad + "\n";
        resultado += "Existencias: " + existencias + "\n";
        return resultado;
    }

}

