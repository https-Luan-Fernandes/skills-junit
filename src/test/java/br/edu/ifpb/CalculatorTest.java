package br.edu.ifpb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    static Calculator calc;

    @BeforeAll
    static void setUp() {
        calc = new Calculator();
    }

    @Nested
    @DisplayName("Testes de Adição")
    class AdditionTests{

        @org.junit.jupiter.api.Test
        @DisplayName("Somar 1+2 deve retornar 3")
        void addOnePlusTwoShouldReturnThree() {
            assertEquals(3, calc.add(1,2));
        }

        @ParameterizedTest
        @CsvSource({
                "1, 1, 2",
                "2, 1, 3",
                "4, 2, 6",
                "5, 7, 12"
        })
        @DisplayName("Adição de vários valores deve retornar o valor esperado")
        void addVariousNumbersShouldReturnExpected(int a, int b, int expected) {
            assertEquals(expected, calc.add(a,b));
        }

    }

    @Nested
    @DisplayName("Testes de Subtração")
    class SubtractionTests {

        @org.junit.jupiter.api.Test
        @DisplayName("Subtrair 2-1 deve retornar 1")
        void subtractTwoMinusOneShouldReturnOne() {
            assertEquals(1, calc.subtract(2,1));
        }

    }

    @Nested
    @DisplayName("Testes de Multiplicação")
    class MultiplicationTests {

        @ParameterizedTest
        @CsvSource({
                "1, 2, 2",
                "3, 2, 6",
                "5, 5, 25",
                "4, 2, 8",
                "7, 7, 49"
        })
        @DisplayName("Multiplicação com vários pares de inteiros")
        void multiplyVariousValuesShouldReturnExpected(int a, int b, int expected) {
            assertEquals(expected, calc.multiply(a,b));
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Multiplicar 0*2 deve retornar 0")
        void multiplyZeroTimesTwoShouldReturnZero() {
            assertEquals(0, calc.multiply(0,2));
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Multiplicar 5*0 deve retornar 0")
        void multiplyFiveTimesZeroShouldReturnZero() {
            assertEquals(0, calc.multiply(5,0));
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Multiplicar -1*2 deve retornar -2")
        void multiplyMinusOneTimesTwoShouldReturnMinusTwo() {
            assertEquals(-2, calc.multiply(-1,2));
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Multiplicar (-1)*(-2) deve retornar 2")
        void multiplyMinusOneTimesMinusTwoShouldReturnTwo() {
            assertEquals(2, calc.multiply(-1,-2));
        }
    }

    @Nested
    @DisplayName("Testes de Divisão")
    class DivisionTests{

        @org.junit.jupiter.api.Test
        @DisplayName("Dividir 2/1 deve retornar 2")
        void divideTwoByOneShouldReturnTwo() {
            assertEquals(2, calc.divide(2,1));
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Dividir 2/0 deve lançar uma exceção aritmética")
        void divideByZeroShouldThrowArithmeticException() {
            assertThrows(ArithmeticException.class, () -> calc.divide(2,0));
        }

        @ParameterizedTest
        @CsvSource({
                "2, 1, 2",
                "3, 1, 3",
                "4, 2, 2"
        })
        @DisplayName("Divisão de vários valores deve retornar o valor esperado")
        void divideVariousNumbersShouldReturnExpected(int a, int b, int expected) {
            assertEquals(expected, calc.divide(a,b));
        }
    }

    @Nested
    @DisplayName("Testes de Fatorial")
    class FactorialTests{

        @ParameterizedTest
        @CsvSource({
                "0, 1",
                "1, 1",
                "2, 2",
                "5, 120"
        })
        @DisplayName("Fatorial de vários números positivos deve retornar o esperado")
        void factorialOfVariousValuesShouldReturnExpected(int a, int expected) {
            assertEquals(expected, calc.factorial(a));
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Fatorial de 3 deve retornar 6")
        void factorialOfThreeShouldReturnSix() {
            assertEquals(6, calc.factorial(3));
        }

        @ParameterizedTest
        @DisplayName("Fatorial de 0 e 1 deve retornar 1")
        @ValueSource(ints = {0,1})
        void factorialOfZeroAndOneShouldReturnOne(int value) {
            assertEquals(1, calc.factorial(value));
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Fatorial de -1 deve lançar uma exceção de argumento inválido")
        void factorialOfNegativeNumberShouldThrowIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> {
                calc.factorial(-1);
            });
        }

    }
}