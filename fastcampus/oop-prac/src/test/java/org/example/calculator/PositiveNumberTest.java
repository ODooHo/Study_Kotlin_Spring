package org.example.calculator;


import org.assertj.core.api.Assertions;
import org.example.calculator.PositiveNumber;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositiveNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {0,-1})
    void isPositive(int value) {
        Assertions.assertThatCode(() -> new PositiveNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
