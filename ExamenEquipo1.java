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
				
				case 3: {
					System.out.println("\n--- Conversor de Números Enteros a Romanos ---");
					System.out.print("Introduce un número entero entre 1 y 3999: ");

					if (scanner.hasNextInt()) {
						int numero = scanner.nextInt();

						if (numero >= 1 && numero <= 3999) {
							String romano = convertirARomano(numero);
							System.out.println("Número romano: " + romano);
						} else {
							System.out.println("Error: El número debe estar entre 1 y 3999.");
						}
					} else {
						System.out.println("Error: Debes introducir un número entero válido.");
						scanner.next();
					}

					System.out.println("Volviendo al menú...");
					break;
				}


				case 4: {
					System.out.println("\n--- Torre de Hanói ---");
					System.out.print("Introduce el número de discos (entero entre 1 y 15): ");
					int numDiscos;

					if (scanner.hasNextInt()) {
						numDiscos = scanner.nextInt();
						
						if (numDiscos >= 1 && numDiscos <= 15) {
							System.out.println("\nPasos para mover " + numDiscos + " discos:");
							torreDeHanoi(numDiscos, 'A', 'C', 'B');
							System.out.println("\nTotal de movimientos: " + (Math.pow(2, numDiscos) - 1));
						} else {
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

	 
	public static void torreDeHanoi(int n, char origen, char destino, char auxiliar) {
		if (n == 1) {
			System.out.println("Mover disco 1 de estaca " + origen + " a estaca " + destino);
			return;
		}
		torreDeHanoi(n - 1, origen, auxiliar, destino);

		System.out.println("Mover disco " + n + " de estaca " + origen + " a estaca " + destino);

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

	public static void printSerie(int[] serie) {
		for (int num : serie) {
			System.out.println(num);
		}
	}

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

	public static long ackermann(int m, int n) {
		if (m == 0) {
			return n + 1;
		} else if (m > 0 && n == 0) {
			return ackermann(m - 1, 1);
		} else {
			return ackermann(m - 1, (int) ackermann(m, n - 1));
		}
	}
	public static String convertirARomano(int numero) {
		int[] valores = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] romanos = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

		String resultado = "";

		for (int i = 0; i < valores.length; i++) {
			while (numero >= valores[i]) {
				numero -= valores[i];
				resultado += romanos[i];
			}
		}

		return resultado;
	}
}
