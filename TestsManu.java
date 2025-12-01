import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestsManu {

  @Test
    public void testFibConCantidad1() {
    ExamenEquipo1 ex = new ExamenEquipo1();
    int[] result = ex.generateFibSerie(1);
    assertArrayEquals(new int[]{1}, result);
  }

  @Test
    public void testFibConCantidad2() {
    ExamenEquipo1 ex = new ExamenEquipo1();
    int[] result = ex.generateFibSerie(2);
    assertArrayEquals(new int[]{1, 1}, result);
  }

  @Test
    public void testFibConCantidad5() {
    ExamenEquipo1 ex = new ExamenEquipo1();
    int[] result = ex.generateFibSerie(5);
    assertArrayEquals(new int[]{1, 1, 2, 3, 5}, result);
  }

  @Test
    public void testCantidadCero() {
    ExamenEquipo1 ex = new ExamenEquipo1();
    int[] result = ex.generateFibSerie(0);
    assertEquals(0, result.length, "Si la cantidad es 0, el arreglo debe estar vacío");
  }

  @Test
    public void testCantidadNegativa() {
    ExamenEquipo1 ex = new ExamenEquipo1();
    int[] result = ex.generateFibSerie(-5);
    assertEquals(0, result.length, "Si la cantidad es negativa, el arreglo debe estar vacío");
  }

  @Test
    public void testNoLanzaExcepcion() {
    ExamenEquipo1 ex = new ExamenEquipo1();
    assertDoesNotThrow(() -> ex.generateFibSerie(10),
        "El método no debe lanzar excepciones con entradas válidas");
  }
}
