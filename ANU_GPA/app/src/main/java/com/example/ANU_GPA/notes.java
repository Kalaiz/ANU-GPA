package com.example.ANU_GPA;

public class notes {

    private String Number_of_courses_done_value ;
    private String Current_GPA_value;
    private String currentPoints;
    private String grades;
    private String title;

    public String getNumber_of_courses_done_value() {
        return Number_of_courses_done_value;
    }

    public void setNumber_of_courses_done_value(String number_of_courses_done_value) {
        Number_of_courses_done_value = number_of_courses_done_value;
    }

    public String getCurrent_GPA_value() {
        return Current_GPA_value;
    }

    public void setCurrent_GPA_value(String current_GPA_value) {
        Current_GPA_value = current_GPA_value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titleInput) {
        title = titleInput;
    }

    public String getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(String currentPoints) {
        this.currentPoints = currentPoints;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }
}
