package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MathUtilsTest {
    private MathUtils mathUtils;

    @BeforeEach
    void setUp() {
        mathUtils = new MathUtils();
    }

    @Test
    void testAdd() {
        assertEquals(15, mathUtils.add(10, 5));
        assertEquals(-5, mathUtils.add(-10, 5));
        assertEquals(0, mathUtils.add(0, 0));
    }

    @Test
    void testSubtract() {
        assertEquals(5, mathUtils.subtract(10, 5));
        assertEquals(-15, mathUtils.subtract(-10, 5));
        assertEquals(0, mathUtils.subtract(0, 0));
    }

    @Test
    void testMultiply() {
        assertEquals(50, mathUtils.multiply(10, 5));
        assertEquals(-50, mathUtils.multiply(-10, 5));
        assertEquals(0, mathUtils.multiply(0, 5));
    }

    @Test
    void testDivide() {
        assertEquals(2, mathUtils.divide(10, 5));
        assertEquals(-2, mathUtils.divide(-10, 5));
        assertEquals(0, mathUtils.divide(0, 5));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(10, 0));
    }
}