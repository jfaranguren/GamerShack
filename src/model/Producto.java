package model;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public abstract class Producto implements Taxable, Comparable<Producto> {

	// Atributos
	private String codigo;
	private String nombre;
	private double precio;
	private Calendar fechaRegistro;
	private int cantidadDisponible;

	// Constructor
	public Producto(String codigo, String nombre, double precio, Calendar fechaRegistro, int cantidadDisponible) {

		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.fechaRegistro = fechaRegistro;
		this.cantidadDisponible = cantidadDisponible;

	}

	// Analizador
	public String toString() {

		String msg = "";

		msg += "Codigo: " + codigo;
		msg += "\nNombre: " + nombre;
		msg += "\nPrecio: " + precio;
		msg += "\nFecha de registro: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaRegistro.getTime());

		return msg;
	}

	// Analizador
	public String getCodigo() {

		return this.codigo;

	}

	public String getNombre() {

		return this.nombre;

	}

	public double getPrecio() {

		return this.precio;

	}

	public boolean setPrecio(double precio) {

		this.precio = precio;
		return true;

	}

	public int getCantidadDisponible() {

		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {

		this.cantidadDisponible = cantidadDisponible;

	}

	public boolean hayProducto(int cantidadAVender) {

		if (cantidadDisponible > 0 && cantidadDisponible >= cantidadAVender) {

			return true;

		}

		return false;

	}

	@Override
	public int compareTo(Producto p2) {

		if (this.precio > p2.getPrecio()) {

			return -1;
		} else if (this.precio < p2.getPrecio()) {

			return 1;
		}
		return 0;

	}

}