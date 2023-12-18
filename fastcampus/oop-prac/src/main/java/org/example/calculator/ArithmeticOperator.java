package org.example.calculator;

import java.util.Arrays;

public enum ArithmeticOperator {
    ADDITION("+"){
        @Override
        public int arithmeticCalculate(int a, int b) {
            return a + b;
        }
    }, SUBTRACTION("-") {
        @Override
        public int arithmeticCalculate(int a, int b) {
            return a - b;
        }
    }, MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculate(int a, int b) {
            return a * b;
        }
    }, DIVISION("/") {
        @Override
        public int arithmeticCalculate(int a, int b) {
            return a / b;
        }
    };

    private final String operator;

    ArithmeticOperator(String operator) {
        this.operator = operator;
    }


    public abstract int arithmeticCalculate(final int a, final int b);

    public static int calculate(int a, String operator, int b){
        ArithmeticOperator arithmeticOperator = Arrays.stream(values())
                .filter(v -> v.operator.equals(operator))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙 연산이 아닙니다."));
        return arithmeticOperator.arithmeticCalculate(a,b);
    }
}
