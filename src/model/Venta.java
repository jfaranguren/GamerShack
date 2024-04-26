package model;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Venta{
	
	private double valorTotal;
	private double valorIVA;
	private int cantidadVendida;
	private Producto productoVendido;
	private Calendar fechaDeVenta;
	
	public Venta(int cantidadVendida, Producto productoVendido){
		
		double subTotal = cantidadVendida*productoVendido.getPrecio();
		valorIVA = subTotal*0.19;	
		valorTotal=subTotal+valorIVA;
		this.cantidadVendida = cantidadVendida;
		this.productoVendido = productoVendido;
		fechaDeVenta = Calendar.getInstance();
		
	}
	
	public String toString(){
		
		String msg= "Venta: \n";
		
		msg += productoVendido.getNombre()+" - "+ cantidadVendida+" - "+ productoVendido.getPrecio();
		msg += "\nTotal: "+valorTotal;

		return msg;
		
	}
	
	
	
}