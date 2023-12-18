package org.example.calculator;

public class AdditionOperator implements NewArithmeticOperator{

    @Override
    public boolean supports(String operator) {
        return "+".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber a, PositiveNumber b) {
        return a.toInt()+b.toInt();
    }
}
