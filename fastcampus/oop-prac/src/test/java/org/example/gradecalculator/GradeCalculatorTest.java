package org.example.gradecalculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 요구사항
 * 평균학점 계산 방법 - (학점수 + 교과목 평점)의 합계 / 수강신청 총 학점 수
 * 일급 컬렉션 사용
 */
public class GradeCalculatorTest {
    // 학점 계산기 도메인 : 이수한 과목, 학점 계산기 -> 계산기가 과목의 학점을 변수로 가진다?
    // 여러 수업들 -> 과목(코스) 클래스로 정의

    // 이수한 과목을 전달하여 평균 학점 계산 요청 ---> 학점 계산기 -->(학점수 + 교과목 평점)의 합계  -->과목(코스)
    //                                                -->(수강신청 총 학점 수)        -->과목(코스)


    @DisplayName("평균 학점을 계산한다.")
    @Test
    void calculateGradeTest() {
        List<Course> courses = List.of(
                new Course("OOP",3,"A+"),
                new Course("자료구조",3,"A+")
                );

        GradeCalculator gradeCalculator = new GradeCalculator(courses);
        double gradeResult = gradeCalculator.calculateGrade();

        Assertions.assertThat(gradeResult).isEqualTo(4.5);


    }
}
