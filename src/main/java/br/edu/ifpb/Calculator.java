package br.edu.ifpb;

public class Calculator {

    public int add(int a, int b){
        return a + b;
    }

    public int subtract(int a, int b){
        return a - b;
    }

    public int multiply(int a, int b){
        return a * b;
    }

    public int divide(int a, int b) throws ArithmeticException{
        if(b == 0){
            throw new ArithmeticException();
        }
        return a / b;
    }

    public int factorial(int a) throws IllegalArgumentException{
        if(a < 0){
            throw new IllegalArgumentException();
        }else if(a == 0 || a == 1) {
            return 1;
        }else{
            return a * factorial(a - 1);
        }
    }
}
