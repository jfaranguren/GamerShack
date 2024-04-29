package model;

import java.util.Arrays;

public class Controladora {

	// Relacion
	private Producto[] almacenamiento;
	private Venta[] ventas;

	public Controladora() {

		almacenamiento = new Producto[1000];
		ventas = new Venta[10];
		crearCasosDePrueba();
		ordenarAlamacenamiento();

	}

	public String listarProductos() {

		Producto[] almacenamientoSinNull = ordenarAlamacenamiento();

		String lista = "";

		for (int i = 0; i < almacenamientoSinNull.length; i++) {

			if (almacenamientoSinNull[i] != null) {
				lista += "\n" + almacenamientoSinNull[i].getCodigo() + "-" + almacenamientoSinNull[i].getNombre();
			}
		}

		return lista;

	}

	public String listaGenero() {

		Genero[] arregloGenero = Genero.values();

		String lista = "";

		for (int i = 0; i < arregloGenero.length; i++) {

			lista += "\n" + (i + 1) + "- " + arregloGenero[i];

		}

		return lista;

	}

	public boolean almacenarConsola(String codigo, String nombre, double precio, int cantidad, String marca) {

		Consola nuevoProducto = new Consola(codigo, nombre, precio, cantidad, marca);

		for (int i = 0; i < almacenamiento.length; i++) {

			if (almacenamiento[i] == null) {

				almacenamiento[i] = nuevoProducto;
				return true;

			} else if (almacenamiento[i].getCodigo().equals(codigo)) {

				return false;
			}

		}

		return false;

	}

	public boolean almacenarJuego(String codigo, String nombre, double precio, int cantidad, int genero) {

		// Juego nuevoProducto =

		for (int i = 0; i < almacenamiento.length; i++) {

			if (almacenamiento[i] == null) {

				almacenamiento[i] = new Juego(codigo, nombre, precio, cantidad, Genero.values()[genero - 1]);
				return true;

			} else if (almacenamiento[i].getCodigo().equals(codigo)) {

				return false;
			}

		}

		return false;

	}

	/**
	 * Descripcion: Este metodo busca un Producto a traves de
	 * su codigo, comparando el String ingresado con el codigo
	 * del Producto almacenado en el arreglo.
	 * pre: El arreglo almacenamiento esta inicializado
	 * 
	 * @param codigo String El codigo del Producto a buscarProducto
	 * @return Producto El Producto buscado
	 */
	public Producto buscarProducto(String codigo) {

		for (int i = 0; i < almacenamiento.length; i++) {

			Producto temporal = almacenamiento[i];

			if (temporal != null) {

				if (codigo.equals(temporal.getCodigo())) {

					return temporal;

				}
			}

		}

		return null;

	}

	public String mostrarProducto(String codigo) {

		Producto temporal = buscarProducto(codigo);

		if (temporal == null) {

			return "El Producto no se encuentra";
		}

		return temporal.toString();

	}

	public void crearCasosDePrueba() {

		almacenarConsola("1", "PlayStation 6", 7000000, 12, "Sony");
		almacenarJuego("3", "GTA 6", 400000, 20, 3);
		almacenarConsola("2", "Nintendo Switch 2", 3000000, 6, "Nintendo");

	}

	public boolean modificarPrecioProducto(String codigo, double precio) {

		return buscarProducto(codigo).setPrecio(precio);

	}

	public boolean eliminarProducto(String codigo) {

		int indice = buscarIndiceProducto(codigo);

		if (indice != -1) {

			almacenamiento[indice] = null;
			return true;

		}

		return false;

	}

	public int buscarIndiceProducto(String codigo) {

		for (int i = 0; i < almacenamiento.length; i++) {

			Producto temporal = almacenamiento[i];

			if (temporal != null) {

				if (codigo.equals(temporal.getCodigo())) {

					return i;

				}
			}

		}

		return -1;

	}

	public String realizarVenta(String codigo, int cantidadProducto) {

		Producto temporal = buscarProducto(codigo);

		if (temporal != null) {

			if (temporal.hayProducto(cantidadProducto)) {

				Venta nuevaVenta = new Venta(cantidadProducto, temporal);

				for (int z = 0; z < ventas.length; z++) {

					if (ventas[z] == null) {

						ventas[z] = nuevaVenta;
						temporal.setCantidadDisponible(temporal.getCantidadDisponible() - cantidadProducto);
						return nuevaVenta.toString();

					}

				}

			}

		}

		return "La venta no se puede registrar";

	}

	public String contarTipoProducto() {

		String msg = "";
		int contadorJuego = 0;
		int contadorConsola = 0;

		for (int i = 0; i < almacenamiento.length; i++) {

			if (almacenamiento[i] != null) {

				if (almacenamiento[i] instanceof Juego) {
					contadorJuego++;
				} else if (almacenamiento[i] instanceof Consola) {
					contadorConsola++;
				}
			}
		}

		msg += "El inventario esta compuesto de la siguiente manera:" +
				"\nJuegos: " + contadorJuego +
				"\nConsolas: " + contadorConsola;

		return msg;

	}

	public String consultarProductoConMasUnidades() {

		String msg = "";
		int maximo = 0;
		int indice = 0;

		for (int i = 0; i < almacenamiento.length; i++) {

			if (almacenamiento[i] != null) {

				if (maximo < almacenamiento[i].getCantidadDisponible()) {

					maximo = almacenamiento[i].getCantidadDisponible();
					indice = i;

				}

			}

		}

		msg += "El Producto con mas unidades es: " + almacenamiento[indice].getNombre() + " con " + maximo
				+ " unidades.";

		return msg;
	}

	public String consultarMarcaDeConsolaConMasUnidades() {

		String msg = "";
		int maximo = 0;
		int indice = 0;
		String marca = "";

		for (int i = 0; i < almacenamiento.length; i++) {

			if (almacenamiento[i] != null) {

				if (almacenamiento[i] instanceof Consola) {

					if (maximo < almacenamiento[i].getCantidadDisponible()) {

						maximo = almacenamiento[i].getCantidadDisponible();
						indice = i;

					}

					marca = ((Consola) almacenamiento[indice]).getMarca();

				}

			}

		}

		msg += "La Marca con mas unidades es: " + marca + " con " + maximo + " unidades.";

		return msg;
	}

	public Producto[] ordenarAlamacenamiento() {

		// Ubica los null al final del arreglo
		int i = 0;
		for (int j = 0; j < almacenamiento.length; j++) {
			if (almacenamiento[j] != null) {
				almacenamiento[i] = almacenamiento[j];
				i++;
			}

		}

		Producto[] almacenamientoSinNull = Arrays.copyOf(almacenamiento, i); // Crea una copia hasta el último indice no null
		Arrays.sort(almacenamientoSinNull); // Ordena el arreglo resultante

		return almacenamientoSinNull;

	}

}