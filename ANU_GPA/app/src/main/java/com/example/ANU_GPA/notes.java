package com.example.ANU_GPA;

public class notes {

    private String Number_of_courses_done_value ;
    private String Current_GPA_value;
    private String GPA_want_to_achieve_value;
    private String Courses_left_value;

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

    public String getGPA_want_to_achieve_value() {
        return GPA_want_to_achieve_value;
    }

    public void setGPA_want_to_achieve_value(String GPA_want_to_achieve_value) {
        this.GPA_want_to_achieve_value = GPA_want_to_achieve_value;
    }

    public String getCourses_left_value() {
        return Courses_left_value;
    }

    public void setCourses_left_value(String courses_left_value) {
        Courses_left_value = courses_left_value;
    }
}
