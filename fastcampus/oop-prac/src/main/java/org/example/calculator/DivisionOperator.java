package org.example.calculator;

public class DivisionOperator implements NewArithmeticOperator{

    @Override
    public boolean supports(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber a, PositiveNumber b) {
        if (b.toInt() == 0){
            throw new IllegalArgumentException("0으로는 나눌 수 없습니다.");
        }
        return a.toInt()/b.toInt();
    }
}
