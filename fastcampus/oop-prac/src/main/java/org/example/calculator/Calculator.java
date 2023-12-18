package org.example.calculator;

import java.util.List;

public class Calculator {
    private static final List<NewArithmeticOperator> arithmeticOperators = List.of(
            new AdditionOperator(), new SubtractionOperator(),new MultiplicationOperator(), new DivisionOperator()
            );

    public static int calculate(PositiveNumber a, String operator, PositiveNumber b){
        return arithmeticOperators.stream()
                .filter(arithmeticOperators -> arithmeticOperators.supports(operator))
                .map(arithmeticOperators -> arithmeticOperators.calculate(a,b))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 연산자가 아닙니다."));
    }

}
