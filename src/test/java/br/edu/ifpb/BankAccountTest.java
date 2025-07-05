package br.edu.ifpb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        this.bankAccount = new BankAccount(BigDecimal.valueOf(100));
    }

    @Test
    @DisplayName("Deve retornar o saldo inicial de 100R$")
    void initialBalanceShouldBe100() {
        assertEquals(BigDecimal.valueOf(100), bankAccount.getBalance());
    }

    @Nested
    @DisplayName("Testes do Construtor")
    class ConstrutorTest {

        @ParameterizedTest
        @ValueSource(doubles = {100, 100.1, 101, 110, 130, 200, 500, 1000, 1000000})
        @DisplayName("Deve permitir a criação da conta com saldo maior ou igual a 100R$")
        void shouldCreateAccountWithValidAmount(double amount) {

            BankAccount account = new BankAccount(BigDecimal.valueOf(amount));
            assertEquals(BigDecimal.valueOf(amount), account.getBalance());
        }

        @ParameterizedTest
        @ValueSource(doubles = {99.9,99.99999,90,80,1,0.1,0,0.00001, -10, -100, -1000})
        @DisplayName("Deve lançar uma IllegalArgumentException quando o saldo inicial for menor que 100R$")
        void shouldThrowIllegalArgumentExceptionWhenBalanceLessThan100R(double amount) {
            assertThrows(IllegalArgumentException.class, () -> {new BankAccount(BigDecimal.valueOf(amount));});
        }

        @Test
        @DisplayName("Deve lançar uma IllegalArgumentException quando o argumento do construtor for null")
        void shouldThrowIllegalArgumentExceptionWhenArgumentoIsNull() {
            assertThrows(IllegalArgumentException.class, () -> {new BankAccount(null);});
        }
    }

    @Nested
    @DisplayName("Testes para o método de depósito")
    class DepositTest {

        @ParameterizedTest
        @CsvSource({
                "100.1, 200.1",
                "1.1, 101.1",
                "10, 110",
                "1000000, 1000100"
        })
        @DisplayName("Deve aumentar o saldo para os valores esperados")
        void depositShouldIncreaseBalanceToExpected(double amount, double expected) {
            bankAccount.deposit(BigDecimal.valueOf(amount));
            assertEquals(BigDecimal.valueOf(expected), bankAccount.getBalance());
        }

        @ParameterizedTest
        @ValueSource(doubles = {-10.1,-1.1,0.0})
        @DisplayName("Deve lançar uma IllegalArgumentException para valores menores ou igual a zero")
        void depositShouldThrowExceptionWhenAmountLessOrEqualToZero(double value) {
            assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(BigDecimal.valueOf(value)));
        }

        @Test
        @DisplayName("Deve lançar NullPointerException quando o argumento do parametro de depósito for null")
        void shouldThrowNullPointerExceptionWhenArgumentoIsNull() {
            assertThrows(NullPointerException.class, () -> {bankAccount.deposit(null);});
        }


    }

    @Nested
    @DisplayName("Testes para o método de saque")
    class WithdrawTest {

        @ParameterizedTest
        @ValueSource(doubles = {-1000.2, -100.2, -10.1, 0.00})
        @DisplayName("Deve lançar uma exceção do tipo IllegalArgumentException com valores menores ou igual a zero")
        void withdrawShouldThrowIllegalArgumentExceptionWhenAmountLessThanOrEqualsToZero(double value) {
            assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(BigDecimal.valueOf(value)));
        }

        @ParameterizedTest
        @ValueSource(doubles = {100.00001,100.1,101,102,200,300,4000})
        @DisplayName("Deve lançar uma exceção do tipo IllegalArgumentException com valores de saque maiores que o saldo atual de 100R$")
        void withdrawShouldThrowIllegalArgumentExceptionWhenValueGreaterThanBalance(double value){
            assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(BigDecimal.valueOf(value)));
        }

        @ParameterizedTest
        @CsvSource({
                "1, 99",
                "1.1, 98.9",
                "10, 90",
                "10.1, 89.9",
                "40, 60",
                "99, 1",
                "100, 0"
        })
        @DisplayName("Deve permitir o saque de valores válidos")
        void withdrawShouldDecreaseBalance(double amount, double expected) {
            bankAccount.withdraw(BigDecimal.valueOf(amount));
            assertEquals(BigDecimal.valueOf(expected), bankAccount.getBalance());
        }

        @Test
        @DisplayName("Deve lançar uma exceção do tipo NullPointerException quando o argumento do parametro de saque for null")
        void withdrawShouldThrowNullPointerExceptionWhenArgumentoIsNull() {
            assertThrows(NullPointerException.class, () -> bankAccount.withdraw(null));
        }
    }

}