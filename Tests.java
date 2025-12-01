import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Tests {

  // ======================= PRUEBAS FIBONACCI =======================

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

  // ======================= PRUEBAS ACKERMANN =======================

  @Test
  public void testAckermannCasoBase_m0_n0() {
    long result = ExamenEquipo1.ackermannSegura(0, 0);
    assertEquals(1L, result, "Ackermann(0,0) debe ser 1");
  }

  @Test
  public void testAckermann_m0_n5() {
    long result = ExamenEquipo1.ackermannSegura(0, 5);
    assertEquals(6L, result, "Ackermann(0,5) debe ser 6");
  }

  @Test
  public void testAckermann_m1_n2() {
    long result = ExamenEquipo1.ackermannSegura(1, 2);
    assertEquals(4L, result, "Ackermann(1,2) debe ser 4");
  }

  @Test
  public void testAckermann_m2_n2() {
    long result = ExamenEquipo1.ackermannSegura(2, 2);
    assertEquals(7L, result, "Ackermann(2,2) debe ser 7");
  }

  @Test
  public void testAckermann_m3_n2() {
    long result = ExamenEquipo1.ackermannSegura(3, 2);
    assertEquals(29L, result, "Ackermann(3,2) debe ser 29");
  }

  @Test
  public void testAckermannNoLanzaExcepcionEnLimitePermitido() {
    assertDoesNotThrow(() -> ExamenEquipo1.ackermannSegura(3, 10),
        "No debería lanzar excepción con m=3, n=10");
  }

  @Test
  public void testAckermannLanzaExcepcionConNegativos() {
    assertThrows(IllegalArgumentException.class,
        () -> ExamenEquipo1.ackermannSegura(-1, 0),
        "Debe lanzar IllegalArgumentException si m o n son negativos");
  }

  @Test
  public void testAckermannLanzaExcepcionConValoresDemasiadoGrandes() {
    assertThrows(IllegalArgumentException.class,
        () -> ExamenEquipo1.ackermannSegura(4, 0),
        "Debe lanzar IllegalArgumentException si m > 3");
  }
}
