package org.example.gradecalculator;

import java.util.List;

public class GradeCalculator {
    private final Courses courses;
    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public double calculateGrade() {
        double multiplyCreditResult = courses.multiplyCreditAndCourseGrade();


        int totalCredit = courses.calculateTotalCredit();

        return multiplyCreditResult / totalCredit;

    }
}
