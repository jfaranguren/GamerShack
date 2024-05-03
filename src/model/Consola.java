package model;

import java.util.Calendar;

public class Consola extends Producto {

    private String marca;

    public Consola(String codigo, String nombre, double precio, Calendar fecha, int cantidadDisponible, String marca) {
        super(codigo, nombre, precio, fecha, cantidadDisponible);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    

    @Override
    public String toString() {
        return "Consola [marca=" + marca + "]";
    }

    @Override
    public double calcularImpuesto(double valor) {
       
        return valor*IVA;

    }

}
