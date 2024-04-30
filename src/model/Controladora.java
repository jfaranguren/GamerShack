package model;

import java.util.Arrays;

public class Controladora {

	// Relaciones
	private Producto[] almacenamiento;
	private Venta[] ventas;

	// Constructor
	public Controladora() {

		almacenamiento = new Producto[1000];
		ventas = new Venta[10];
		crearCasosDePrueba();
		ordenarAlamacenamiento();

	}

	/**
	 * Descripcion: Este metodo permite listar los productos registrados en el
	 * almacenamiento
	 * pre: El arreglo almacenamiento debe estar inicializado
	 * 
	 * @return String en formato lista con la informacion de codigo,
	 *         nombre y precio de los productos registrado
	 */
	public String listarProductos() {

		Producto[] almacenamientoSinNull = ordenarAlamacenamiento();

		String lista = "";

		for (int i = 0; i < almacenamientoSinNull.length; i++) {

			if (almacenamientoSinNull[i] != null) {
				lista += "\n" + almacenamientoSinNull[i].getCodigo() + "-" + almacenamientoSinNull[i].getNombre() + "-"
						+ almacenamientoSinNull[i].getPrecio();
			}
		}

		return lista;

	}

	/**
	 * Descripcion: Este metodo permite listar los generos de los juegos a partir de
	 * la
	 * enumeracion Genero
	 * 
	 * @return String en formato lista con la informacion de los
	 *         valores de la enumeracion Genero
	 */

	public String listaGenero() {

		Genero[] arregloGenero = Genero.values();

		String lista = "";

		for (int i = 0; i < arregloGenero.length; i++) {

			lista += "\n" + (i + 1) + "- " + arregloGenero[i];

		}

		return lista;

	}

	/**
	 * Descripcion: Este metodo permite registrar una Consola en el arreglo
	 * almacenamiento
	 * pre: El arreglo almacenamiento debe estar inicializado
	 * pos: El arreglo almacenamiento queda modificado con el nuevo objeto Consola
	 * 
	 * @param codigo   String, el codigo del Producto
	 * @param nombre   String, el nombre del Producto
	 * @param precio   double, el precio del Producto
	 * @param cantidad int, la cantidad disponible del Producto
	 * @param marca    String, la marca de la Consola
	 * @return boolean true si logra almacenar el objeto, false en caso contrario
	 */
	public boolean almacenarConsola(String codigo, String nombre, double precio, int cantidad, String marca) {

		for (int i = 0; i < almacenamiento.length; i++) {

			if (almacenamiento[i] == null) {

				almacenamiento[i] = new Consola(codigo, nombre, precio, cantidad, marca); //Polimorfismo + Upcasting
				return true;

			} else if (almacenamiento[i].getCodigo().equals(codigo)) {

				return false;
			}

		}

		return false;

	}

	/**
	 * Descripcion: Este metodo permite registrar un Juego en el arreglo
	 * almacenamiento
	 * pre: El arreglo almacenamiento debe estar inicializado
	 * pos: El arreglo almacenamiento queda modificado con el nuevo objeto Juego
	 * 
	 * @param codigo   String, el codigo del Producto
	 * @param nombre   String, el nombre del Producto
	 * @param precio   double, el precio del Producto
	 * @param cantidad int, la cantidad disponible del Producto
	 * @param genero   int, el indice de referencia a la enumeracion Genero del
	 *                 Juego
	 * @return boolean true si logra almacenar el objeto, false en caso contrario
	 */
	public boolean almacenarJuego(String codigo, String nombre, double precio, int cantidad, int genero) {

		for (int i = 0; i < almacenamiento.length; i++) {

			if (almacenamiento[i] == null) {

				almacenamiento[i] = new Juego(codigo, nombre, precio, cantidad, Genero.values()[genero - 1]); //Polimorfismo + Upcasting
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
	 * @param codigo String, el codigo del Producto a buscarProducto
	 * @return Producto, el Producto buscado. null en caso de que no se encuentre el
	 *         Producto
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

	/**
	 * Descripcion: Este metodo permite generar un String con la informacion de un
	 * Producto a partir del codigo
	 * 
	 * @param codigo String, el codigo del Producto a mostrar
	 * @return String con la informacion del Producto. Mensaje de error en caso que
	 *         el Producto no se encuentre registrado
	 */
	public String mostrarProducto(String codigo) {

		Producto temporal = buscarProducto(codigo);

		if (temporal == null) {

			return "El Producto no se encuentra";
		}

		return temporal.toString(); //Polimorfismo + Despacho dinamico

	}

	/**
	 * Descripcion: Este metodo permite crear objetos de prueba
	 */
	public void crearCasosDePrueba() {

		almacenarConsola("1", "PlayStation 6", 7000000, 12, "Sony");
		almacenarJuego("3", "GTA 6", 400000, 20, 3);
		almacenarConsola("2", "Nintendo Switch 2", 3000000, 6, "Nintendo");
		almacenarConsola("4", "Nintendo Switch", 1500000, 12, "Nintendo");

	}

	/**
	 * Descripcion: Este metodo permite modificar el precio de un Producto dado su
	 * codigo
	 * 
	 * @param codigo      String, el codigo del Producto a modificar
	 * @param nuevoPrecio double, el nuevo precio del Producto
	 * @return boolean, true si se modifica el Producto. false en caso contrario
	 */
	public boolean modificarPrecioProducto(String codigo, double nuevoPrecio) {

		return buscarProducto(codigo).setPrecio(nuevoPrecio);

	}

	/**
	 * Descripcion: Este metodo permite borrar un Producto dado su codigo
	 * pre: El arreglo almacenamiento esta inicializado
	 * pos: El arreglo almacenamiento queda modificado
	 * 
	 * @param codigo String, el codigo del Producto a modificar
	 * @return boolean, true si se borra el Producto. false en caso contrario
	 */
	public boolean eliminarProducto(String codigo) {

		int indice = buscarIndiceProducto(codigo);

		if (indice != -1) {

			almacenamiento[indice] = null;
			return true;

		}

		return false;

	}

	/**
	 * Descripcion: Este metodo permite obtener el indice de un Producto dado su
	 * codigo
	 * pre: El arreglo almacenamiento esta inicializado
	 * 
	 * @param codigo String, el codigo del Producto a modificar
	 * @return int, el indice del Producto. -1 en caso de que el Producto no se
	 *         encuentre registrado
	 */
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

	/**
	 * Descripcion: Este metodo permite registrar una Venta de un Producto dado su
	 * codigo y la cantidad a vender
	 * pre: El arreglo almacenamiento esta inicializado
	 * pre: El arreglo ventas esta inicializado
	 * 
	 * @param codigo           String, el codigo del Producto a modificar
	 * @param cantidadProducto int, la cantidad de Producto a vender
	 * @return String, la informacion de la Venta. Mensaje de error en caso que no
	 *         sea posible registrar la venta
	 */
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

	/**
	 * Descripcion: Este metodo permite contar cuantos tipos (Juego o Consola)
	 * existen en el almacenamiento
	 * pre: El arreglo almacenamiento esta inicializado
	 * 
	 * @return String, mensaje con la consulta
	 */
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

	/**
	 * Descripcion: Este metodo permite obtener el nombre del producto con mas
	 * unidades
	 * pre: El arreglo almacenamiento esta inicializado
	 * 
	 * @return String, mensaje con la consulta
	 */
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

	/**
	 * Descripcion: Este metodo permite obtener la marca de Consola con mas unidades
	 * pre: El arreglo almacenamiento esta inicializado
	 * 
	 * @return String, mensaje con la consulta
	 */
	public String consultarMarcaDeConsolaConMasUnidades() {

		String msg = "";
		int acumuladoMarca = 0;
		int maximo = 0;

		String marcaMaxima = "";

		for (int i = 0; i < almacenamiento.length; i++) {

			if (almacenamiento[i] != null) {

				if (almacenamiento[i] instanceof Consola) {

					String marca = ((Consola) almacenamiento[i]).getMarca(); //Polimorfismo + Downcasting

					for (int j = 0; j < almacenamiento.length; j++) {

						if (almacenamiento[j] instanceof Consola) {

							if (((Consola) almacenamiento[j]).getMarca().equals(marca)) { //Polimorfismo + Downcasting

								acumuladoMarca += almacenamiento[j].getCantidadDisponible();

							}

						}

					}

					if (maximo < acumuladoMarca) {

						maximo = acumuladoMarca;
						marcaMaxima = marca;

					}

					acumuladoMarca = 0;

				}

			}

		}

		msg += "La Marca con mas unidades es: " + marcaMaxima + " con " + maximo + " unidades.";

		return msg;
	}

	/**
	 * Descripcion: Este metodo permite obtener una copia ordenada por precio del
	 * arreglo almacenamiento
	 * pre: El arreglo almacenamiento esta inicializado
	 * 
	 * @return Producto[] una copia del arreglo almacenamiento sin null y ordenada
	 *         descendentemente por precio de los productos
	 */
	public Producto[] ordenarAlamacenamiento() {

		// Ubica los null al final del arreglo
		int i = 0;
		for (int j = 0; j < almacenamiento.length; j++) {
			if (almacenamiento[j] != null) {
				almacenamiento[i] = almacenamiento[j];
				i++;
			}

		}

		Producto[] almacenamientoSinNull = Arrays.copyOf(almacenamiento, i); // Crea una copia hasta el último indice no
																				// null
		Arrays.sort(almacenamientoSinNull); // Ordena el arreglo resultante

		return almacenamientoSinNull;

	}

}