package br.edu.ifpb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        this.bankAccount = new BankAccount(100);
    }

    @Test
    @DisplayName("Deve retornar o saldo inicial de 100R$")
    void initialBalanceShouldBe100() {
        assertEquals(100, bankAccount.getBalance());
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
            bankAccount.deposit(amount);
            assertEquals(expected, bankAccount.getBalance());
        }

        @ParameterizedTest
        @ValueSource(doubles = {-10.1,-1.1,0.0})
        @DisplayName("Deve lançar uma IllegalArgumentException para valores menores ou igual a zero")
        void depositShouldThrowExceptionWhenAmountLessOrEqualToZero(double value) {
            assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(value));
        }

    }

    @Nested
    @DisplayName("Testes para o método de saque")
    class WithdrawTest {

        @ParameterizedTest
        @ValueSource(doubles = {-1000.2, -100.2, -10.1, 0.00})
        @DisplayName("Deve lançar uma exceção do tipo IllegalArgumentException com valores menores ou igual a zero")
        void withdrawShouldThrowIllegalArgumentExceptionWhenAmountLessThanOrEqualsToZero(double value) {
            assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(value));
        }

        @ParameterizedTest
        @ValueSource(doubles = {100.00001,100.1,101,102,200,300,4000})
        @DisplayName("Deve lançar uma exceção do tipo IllegalArgumentException com valores de saque maiores que o saldo atual de 100R$")
        void withdrawShouldThrowIllegalArgumentExceptionWhenValueGreaterThanBalance(double value){
            assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(value));
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
            bankAccount.withdraw(amount);
            assertEquals(expected, bankAccount.getBalance());
        }
    }

}