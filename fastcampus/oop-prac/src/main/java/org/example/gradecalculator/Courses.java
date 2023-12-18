package org.example.gradecalculator;

import java.util.List;

public class Courses {
    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {
        double creditPlusGrade = 0;

        return courses.stream()
                .mapToDouble(Course::multiplyCreditAndGrade)
                .sum();

//        for (Course course : courses) {  동일한 코드
//            creditPlusGrade += course.multiplyCreditAndGrade();
//        }
//        return creditPlusGrade;
    }

    public int calculateTotalCredit() {
        int totalCredit = courses.stream()
                .mapToInt(Course::getCredit)
                .sum();
        return totalCredit;
    }
}
