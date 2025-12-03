import java.util.Scanner;

/**
 * Clase principal ExamenEquipo1 que contiene el método main
 */
public class ExamenEquipo1 {

	/**
	 * Método principal que inicia el programa, muestra un menú
	 * y permite ejecutar la serie de Fibonacci o la función de Ackermann.
	 */
	public static void main(String[] args) {
		System.out.println("Vamos a chambear mi gentee");

		Scanner scanner = new Scanner(System.in);
		ExamenEquipo1 programa = new ExamenEquipo1();

		boolean continuar = true;

		while (continuar) {
			int opcion;

			System.out.println("\n----- MENU -----");
			System.out.println("1. Serie de Fibonacci");
			System.out.println("2. Función de Ackermann");
			System.out.println("3. Conversor de numeros enteros a Romanos");
			System.out.println("4. Función de la Torre de Hanoi");
			System.out.println("5. Salir");
			System.out.print("Elige una opción: ");

			if (scanner.hasNextInt()) {
				opcion = scanner.nextInt();
			} else {
				System.out.println("Error: opción inválida. Debes introducir un número entero.");
				scanner.next(); 
				continue; 	 	 
			}

			switch (opcion) {

				case 1: {
					int cantidad;
					System.out.println(" Serie de Fibonacci ");
					System.out.print("Introduce cuántos números de la serie de Fibonacci deseas ver: ");
					if (scanner.hasNextInt()) {
						cantidad = scanner.nextInt();
					} else {
						System.out.println("Error: Por favor, introduce un número entero válido.");
						scanner.next(); 
						break;
					}

					int[] serie = programa.generateFibSerie(cantidad);
					System.out.println(" Serie de Fibonacci ");
					if (serie.length > 0) {
						printSerie(serie);
					} else {
						System.out.println("No se puede generar la serie con 0 o un número negativo de elementos.");
					}
					break;
				}

				case 2: {
					System.out.println("\n--- Función de Ackermann ---");

					System.out.print("Introduce el valor de m (entero, no negativo <= 3): ");
					if (!scanner.hasNextInt()) {
						System.out.println("Error: m debe ser un número entero.");
						scanner.next(); 
						break;
					}
					int m = scanner.nextInt();

					System.out.print("Introduce el valor de n (entero, no negativo <= 10): ");
					if (!scanner.hasNextInt()) {
						System.out.println("Error: n debe ser un número entero.");
						scanner.next(); 
						break;
					}
					int n = scanner.nextInt();

					try {
						long resultado = ackermannSegura(m, n);
						System.out.println("Resultado de Ackermann(" + m + ", " + n + ") = " + resultado);
					} catch (IllegalArgumentException e) {
						System.out.println("Error en los parámetros: " + e.getMessage());
					} catch (StackOverflowError e) {
						System.out.println("Error: se excedió la profundidad de la recursión (StackOverflowError).");
					}
					break;
				}
				
				case 3:
					System.out.println("Agregar funcionalidad de Numeros Romanos");
					System.out.println("Volviendo al menú...");
					break;


				case 4: {
					System.out.println("\n--- Torre de Hanói ---");
					System.out.print("Introduce el número de discos (entero entre 1 y 15): ");
					int numDiscos;

					if (scanner.hasNextInt()) {
						numDiscos = scanner.nextInt();
						
						// Nueva validación de límite
						if (numDiscos >= 1 && numDiscos <= 15) {
							System.out.println("\nPasos para mover " + numDiscos + " discos:");
							// Las estacas se llaman 'Origen', 'Auxiliar' y 'Destino'
							torreDeHanoi(numDiscos, 'A', 'C', 'B');
							// El número total de movimientos es 2^n - 1
							System.out.println("\nTotal de movimientos: " + (Math.pow(2, numDiscos) - 1));
						} else {
							// Mensaje de error para números fuera del rango 1-15
							System.out.println("Error: El límite de discos para resultados reales es de 1 a 15.");
						}
					} else {
						System.out.println("Error: Por favor, introduce un número entero válido.");
						scanner.next();
					}
					break;
				}

				case 5:
					System.out.println("Saliendo del programa...");
					continuar = false;
					break;

				default:
					System.out.println("Opción no válida.");
			}
		}

		scanner.close();
		System.out.println("Programa terminado.");
	}

	 
	/**
	 * Función recursiva para resolver la Torre de Hanói.
	 * * @param n Número de discos a mover.
	 * @param origen Estaca de origen.
	 * @param destino Estaca de destino.
	 * @param auxiliar Estaca auxiliar.
	 */
	public static void torreDeHanoi(int n, char origen, char destino, char auxiliar) {
		if (n == 1) {
			System.out.println("Mover disco 1 de estaca " + origen + " a estaca " + destino);
			return;
		}
		// 1. Mover n-1 discos de Origen a Auxiliar, usando Destino como auxiliar
		torreDeHanoi(n - 1, origen, auxiliar, destino);

		// 2. Mover el disco más grande (n) de Origen a Destino
		System.out.println("Mover disco " + n + " de estaca " + origen + " a estaca " + destino);

		// 3. Mover n-1 discos de Auxiliar a Destino, usando Origen como auxiliar
		torreDeHanoi(n - 1, auxiliar, destino, origen);
	}


	int[] generateFibSerie(int cant) {
		if (cant <= 0) {
			return new int[0];
		}

		try {
			int[] newSerie = new int[cant];
			if (cant >= 1) {
				newSerie[0] = 1;
			}
			if (cant >= 2) {
				newSerie[1] = 1;
			}
			for (int i = 2; i < cant; i++) {
				newSerie[i] = newSerie[i - 1] + newSerie[i - 2];
			}
			return newSerie;
		} catch (NegativeArraySizeException ex) {
			System.out.print("Error (tamaño): " + ex);
			return new int[0];
		}
	}

	/**
	 * Imprime los elementos de la serie de Fibonacci, uno por línea.
	 *
	 * @param serie arreglo con los números de la serie a imprimir
	 */
	public static void printSerie(int[] serie) {
		for (int num : serie) {
			System.out.println(num);
		}
	}

	/**
	 * Método para Ackermann.
	 * Manejo de valores negativos.
	 */
	public static long ackermannSegura(int m, int n) {
		if (m < 0 || n < 0) {
			throw new IllegalArgumentException("m y n deben ser números enteros no negativos.");
		}

		
		if (m > 3 || (m == 3 && n > 10)) {
			throw new IllegalArgumentException(
				"Valores demasiado grandes para Ackermann. Usa m <= 3 y n <= 10 para evitar desbordamiento."
			);
		}

		return ackermann(m, n);
	}

	/**
	 * Función de Ackermann .
	 */
	public static long ackermann(int m, int n) {
		if (m == 0) {
			return n + 1;
		} else if (m > 0 && n == 0) {
			return ackermann(m - 1, 1);
		} else {
			// Nota: Se requiere un cast a (int) aquí para evitar un desbordamiento de pila
            // con valores muy altos, aunque el resultado de Ackermann puede superar el int.
			return ackermann(m - 1, (int) ackermann(m, n - 1));
		}
	}
}