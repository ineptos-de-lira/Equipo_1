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
      return ackermann(m - 1, (int) ackermann(m, n - 1));
    }
  }
}
