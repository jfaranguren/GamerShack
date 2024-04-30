package ui;

import java.util.Scanner;
import model.Controladora;

public class Ejecutable {

	// Atributos
	private Scanner reader;

	// Relaciones
	private Controladora controller;

	public static void main(String[] args) {
		Ejecutable exe = new Ejecutable();
		exe.menu();

	}

	// Contructor
	public Ejecutable() {
		reader = new Scanner(System.in);
		controller = new Controladora();

	}

	public void menu() {

		boolean flag = true;

		do {

			System.out.println("\nBienvenido al Sofware de Inventario de GamerShack");
			System.out.println("1) Registrar Producto");
			System.out.println("2) Consultar Producto por Codigo");
			System.out.println("3) Cambiar precio de Producto registrado");
			System.out.println("4) Borrar Producto registrado");
			System.out.println("5) Realizar una venta");
			System.out.println("6) Consultar composicion del inventario");
			System.out.println("7) Consultar Producto con más unidades disponibles");
			System.out.println("8) Consultar Marca de Consola con más unidades disponibles");
			System.out.println("0) Salir");
			int option = reader.nextInt();

			switch (option) {

				case 1:
					registrarProducto();
					break;

				case 2:
					consultarProducto();
					break;

				case 3:
					modificarPrecioProducto();
					break;

				case 4:
					eliminarProducto();
					break;

				case 5:
					realizarVenta();
					break;

				case 6:
					consultarComposicionInvetario();
					break;

				case 7:
					System.out.println(controller.consultarProductoConMasUnidades());
					break;

				case 8:
					System.out.println(controller.consultarMarcaDeConsolaConMasUnidades());
					break;

				case 0:
					flag = false;
					System.out.println("Gracias por usar nuestros servicios");
					break;

				default:
					System.out.println("Opcion invalida. Intenta nuevamente\n");
					break;
			}

		} while (flag);

	}

	private void registrarProducto() {

		System.out.println("Seleccione el producto a registrar");
		System.out.println("1) Registrar Juego");
		System.out.println("2) Registrar Consola");
		System.out.println("0) Para volver al menu principal");
		int option = reader.nextInt();

		switch (option) {

			case 1:
				registrarJuego();
				break;

			case 2:
				registrarConsola();
				break;
			case 0:
				System.out.println("Volviendo al menu principal\n");
				break;

			default:
				System.out.println("Opcion invalida. Volviendo al menu principal\n");
				break;
		}

	}

	public void registrarJuego() {

		reader.nextLine(); // Correccion del bug del Scanner

		System.out.println("Digite el codigo del producto");
		String codigo = reader.nextLine();

		System.out.println("Digite el nombre del producto");
		String nombre = reader.nextLine();

		System.out.println("Digite el precio del producto");
		double precio = reader.nextDouble();

		System.out.println("Seleccione el genero del producto");
		System.out.println(controller.listaGenero());
		int genero = reader.nextInt();

		System.out.println("Digite la cantidad disponible del producto");
		int cantidad = reader.nextInt();

		boolean resultado = controller.almacenarJuego(codigo, nombre, precio, cantidad, genero);

		if (resultado) {
			System.out.println("Producto registrado exitosamente");
		} else {
			System.out.println("Error, el producto no se ha podido registrar");
		}

	}

	public void registrarConsola() {

		reader.nextLine(); // Correccion del bug del Scanner

		System.out.println("Digite el codigo del producto");
		String codigo = reader.nextLine();

		System.out.println("Digite el nombre del producto");
		String nombre = reader.nextLine();

		System.out.println("Digite el precio del producto");
		double precio = reader.nextDouble();

		reader.nextLine();// Correccion del bug del Scanner

		System.out.println("Digite la marca de la consola");
		String marca = reader.nextLine();

		System.out.println("Digite la cantidad disponible del producto");
		int cantidad = reader.nextInt();

		boolean resultado = controller.almacenarConsola(codigo, nombre, precio, cantidad, marca);

		if (resultado) {
			System.out.println("Producto registrado exitosamente");
		} else {
			System.out.println("Error, el producto no se ha podido registrar");
		}

	}

	public void realizarVenta() {

		reader.nextLine(); // Correccion del bug del Scanner

		System.out.println(controller.listarProductos());

		System.out.println("Digite el codigo del producto");
		String codigo = reader.nextLine();

		System.out.println("Digite la cantidad a vender del producto");
		int cantidadVendida = reader.nextInt();

		System.out.println(controller.realizarVenta(codigo, cantidadVendida));

	}

	public void eliminarProducto() {

		reader.nextLine(); // Correccion del bug del Scanner

		System.out.println(controller.listarProductos());

		System.out.println("Digite el codigo del producto");
		String codigo = reader.nextLine();

		if (controller.eliminarProducto(codigo)) {

			System.out.println("Producto eliminado exitosamente");

		} else {

			System.out.println("El producto no ha podido ser eliminado");

		}

	}

	public void consultarProducto() {

		reader.nextLine(); // Correccion del bug del Scanner

		System.out.println(controller.listarProductos());

		System.out.println("Digite el codigo del producto");
		String codigo = reader.nextLine();

		String msg = controller.mostrarProducto(codigo);

		if (msg.equals("El Producto no se encuentra")) {

			System.out.println(msg);

		} else {

			System.out.println("Los datos del producto son:\n" + msg);
		}

	}

	public void modificarPrecioProducto() {

		reader.nextLine(); // Correccion del bug del Scanner

		System.out.println(controller.listarProductos());

		System.out.println("Digite el codigo del producto");
		String codigo = reader.nextLine();

		String msg = controller.mostrarProducto(codigo);

		if (msg.equals("El Producto no se encuentra")) {

			System.out.println(msg);

		} else {

			System.out.println("Los datos del producto son:\n" + msg);

			System.out.println("Digite el nuevo precio del producto");
			double precio = reader.nextDouble();

			controller.modificarPrecioProducto(codigo, precio);
		}

	}

	public void consultarComposicionInvetario() {

		System.out.println(controller.contarTipoProducto());

	}

}
