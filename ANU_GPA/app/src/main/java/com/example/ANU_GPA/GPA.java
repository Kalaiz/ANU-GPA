package com.example.ANU_GPA;

/**
 * A class in which upon retrieval of data it does any calculation of GPA or recalculation if necessary.
 **/
public class GPA {

    /*TODO:
     * 1)Set up Test Cases for the calc
     */


    //All instance variable below are required for pointsNeeded
    int numOfCourses;//total num of courses
    int numOfTCourses;//num of courses Taken already
    float cgpa;//Current GPA
    int currentPoints;//current number of grade points
    //the below variables are related to the calculate total points function
    float gpaWanted;
    int pointsNeeded;
    final int GRADEVALUES[] = {7,6,5,4,0};

    public GPA(int[] grades) {
        //primary constructor
        gpaCalc(grades);
        //total courses is chosen for ease of use- more likely to know how
        //many in total rather than whats left

    }
    public GPA(float gpa, int coursesDone, int totalCourses) {
        //secondary constructor
        cgpa = gpa;
        numOfTCourses = coursesDone;
        numOfCourses = totalCourses;
        currentPoints = (int)(cgpa*numOfTCourses+0.5);
    }

    public void calculatePointsNeeded(float wantedGPA){
        /*Calculates PointsNeeded based on numOfTCourses & cgpa */
        pointsNeeded = (int)(wantedGPA*(numOfCourses)+0.5)-currentPoints;
        gpaWanted = wantedGPA;
    }

    public void gpaCalc(int[] data) {
        //calculates the gpa and number of courses taken from your grades

        float total = 0;
        int tCourses = 0;
        for (int x = 0; x < 5; x++) {
            total = total + (data[x]*GRADEVALUES[x]);
            tCourses = tCourses + data[x];
        }
        cgpa = (tCourses!=0)?total/tCourses:0;//for not producing a NaN
        currentPoints = (int)(total+0.5);
        numOfTCourses = tCourses;
    }

    //the below functions are update functions, used to changes a saved gpa object
    public void addGrades(int[] newGrades) {
        int extraClasses = 0;
        int extraPoints = 0;
        for(int x = 0; x <5; x++) {
            extraClasses = extraClasses + newGrades[x];
            extraPoints = extraPoints + (newGrades[x]*GRADEVALUES[x]);

        }
        cgpa = (cgpa*numOfTCourses+extraPoints)/(numOfTCourses + extraClasses);
        numOfTCourses = numOfTCourses + extraClasses;
        currentPoints = currentPoints + extraPoints;
        if(gpaWanted != 0.0f) {
            calculatePointsNeeded(gpaWanted);
        }
    }

    public void updateCGPA(float newCGPA, int extraCourses) {
        cgpa = newCGPA;
        numOfTCourses = numOfTCourses +extraCourses;
        currentPoints = currentPoints + (int)(cgpa*numOfTCourses + 0.5);
        if(gpaWanted != 0.0f) {
            calculatePointsNeeded(gpaWanted);
        }
    }



    GPA(){}

    //below are the 5 functions to obtain a useful value from the gpa object
    //just in case we decided to make the values private. can delete later
    public float getCGPA() {
        return cgpa;
    }

    public float getgpaWanted() {
        return gpaWanted;
    }

    public int getCourses() {
        return numOfCourses;
    }

    public int getTCourses() {
        return numOfTCourses;
    }

    public int getPointsReq() {
        return pointsNeeded;
    }

    //three functions to set the values of a gpa object. the other two arent
    //needed as there should be no reason to set those manually
    public void setCGPA(float gpa) {
        cgpa = gpa;
    }

    public void setCourses(int courseNum) {
        numOfCourses = courseNum;
    }

    public void setTCourses(int courseNum) {
        numOfTCourses = courseNum;
    }
}
