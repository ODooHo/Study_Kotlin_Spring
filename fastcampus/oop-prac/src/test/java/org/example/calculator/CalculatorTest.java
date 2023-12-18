package org.example.calculator;

import org.assertj.core.api.Assertions;
import org.example.calculator.Calculator;
import org.example.calculator.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * 요구사항
 * 간단한 사칙연산을 할 수 있다.
 * 양수로만 계산할 수 있다.
 * 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
 * MVC 패턴 기반으로 구현
 */

class CalculatorTest {

    @DisplayName("덧셈 테스트")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void additionTest(int a, String operator, int b, int result) {
        int testResult = Calculator.calculate(new PositiveNumber(a),operator,new PositiveNumber(b));

        assertThat(result).isEqualTo(testResult);
    }

    @DisplayName("뺄셈 테스트")
    @Test
    void subtractionTest() {
        int result = Calculator.calculate(new PositiveNumber(1),"-", new PositiveNumber(2));

        assertThat(result).isEqualTo(-1);
    }

    private static Stream<Arguments> formulaAndResult(){
        return Stream.of(
                arguments(1,"+",2,3),
                arguments(1,"-",2,-1),
                arguments(4,"*",2,8),
                arguments(4,"/",2,2)
        );
    }

}