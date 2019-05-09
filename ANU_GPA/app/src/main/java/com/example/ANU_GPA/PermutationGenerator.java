package com.example.ANU_GPA;

import java.util.ArrayList;

public class PermutationGenerator extends Permutation {
    //                           Fs,HDs,Ds,CRs,Ps
    Integer[] currentPermutation={0,0,0,0,0};
    int pointer=0;//will point to the current position.
    int totalCourses=0;
    Grades[] grades=Grades.values();
    float gpaWanted;
    int truthTablePointer;
    int[][][] truthTables;


    PermutationGenerator(float cgpa, int coursesDone, int totalCourses,float gpaWanted){
        super(cgpa,  coursesDone, totalCourses, gpaWanted);
        calculateGPA();

    }

    ArrayList<Integer[]> integerPartitioning(){
        return super.getPermutation();
    }

    Integer[] next(){
        float gpa=0;
        while(hasNext()){
            if(calculateGPA()==gpaWanted) {
                break;
            }
        }
        return currentPermutation;
    }


    float calculateGPA(){
        int calcTCourses=currentPermutation[1]+currentPermutation[2]
                +currentPermutation[3]+currentPermutation[4];
        float gpa=0;
        //Calculating Points
        for(int i=0;i<4;i++){
            gpa+=currentPermutation[i+1]*grades[i].gradePoints;
        }
        return ((gpa/calcTCourses)*100)/100;
    }


    boolean hasNext(){
        /*Max number; of Fails can be totalCourses + 2.
         totalCourses in context the number of actual of courses they are supposed to do.*/
        return super.validData && currentPermutation[0]<totalCourses+3;
    }



}
