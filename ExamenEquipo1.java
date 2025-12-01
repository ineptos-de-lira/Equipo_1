import java.util.Scanner;

/**
 * Clase principal ExamenEquipo1 que contiene el método main
 * y métodos para generar e imprimir la serie de Fibonacci.
 */
public class ExamenEquipo1 {

  /**
   * Método principal que inicia el programa, solicita al usuario
   * cuántos números de la serie de Fibonacci desea ver, y coordina
   * la generación e impresión de la serie.
   *
   */
  public static void main(String[] args) {
    System.out.println("Vamos a chambear mi gentee");
        
    Scanner scanner = new Scanner(System.in);
    int cantidad;
    System.out.println(" Serie de Fibonacci ");
    System.out.print("Introduce cuántos números de la serie de Fibonacci deseas ver: ");    
    if (scanner.hasNextInt()) {
      cantidad = scanner.nextInt();
    } else {
      System.out.println("Error: Por favor, introduce un número entero válido.");
      scanner.close();
      return;
    }
    scanner.close();

    ExamenEquipo1 programa = new ExamenEquipo1();
    int[] serie = programa.generateFibSerie(cantidad);
    System.out.println(" Serie de Fibonacci ");
    if (serie.length > 0) {
      printSerie(serie); 
    } else {
      System.out.println("No se puede generar la serie con 0 o un número negativo de elementos.");
    }
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
}
