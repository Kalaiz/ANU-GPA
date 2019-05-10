package com.example.ANU_GPA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class PermutationGenerator extends Permutation {

      /*trackers will act like a storage which tracks the number of grades whose
          value will be used accordance to the sequence of calculating a permutation*/
                   //Fs,HDs,Ds,CRs
    Integer[] trackers={1,0,0,0};
    Stack<Integer> stack = new Stack();
    Grades[] grades=Grades.values();
    int sumOfStack=0;


    PermutationGenerator(float cgpa, int coursesDone, int totalCourses,float gpaWanted){
        super(cgpa,  coursesDone, totalCourses, gpaWanted);
    }


    Integer[] next(){
        int size;
        int val;
        boolean condition;
        int numOfFails;
        while(hasNext()){
                if (stack.size() == 4){
                    numOfFails=stack.get(0);
                    val = numOfTBTCourses+(2*numOfFails) - sumOfStack;
                    stack.push(val);
                    int calcTCourses=numOfFails+stack.get(1)+stack.get(2)
                                     +stack.get(3)+stack.get(4);
                        if (val>=0&&calcTCourses-numOfFails==numOfTBTCourses+numOfFails
                                &&calculateGPA(calcTCourses)==gpaWanted) {
                           Integer[] output= (stack.toArray(new Integer[5])); //converting it to an array for these sake of the test
                        stack.pop();
                        sumOfStack-=stack.pop();
                        return output; }
                        stack.pop();//removing the  5th element(pass)
                    sumOfStack -= stack.pop();// updating the sumOfStack
                } else {
                    size = stack.size();
                    val = trackers[size];
                   condition= (size==0)?trackers[0]<numOfTBTCourses+3:sumOfStack + val < numOfTBTCourses+(trackers[0]-1)*2+1;
                    if (condition) {
                        sumOfStack += val;
                        ++trackers[size];
                        stack.push(val);
                    } else {
                        //If tracker's value has reached it's max
                        trackers[size] = 0;
                        val = stack.pop();
                        sumOfStack -= val;
                    }
                }
        }
    return null;
    }

    float calculateGPA(int calcTCourses){
        float gpa=0;
        //Calculating Points
        for(int i=0;i<4;i++){
            gpa+=stack.get(i+1)*grades[i].gradePoints;
        }
        return ((gpa/calcTCourses)*100)/100;
    }

    boolean hasNext(){
        /*Max number; of Fails can be totalCourses + 2.
         totalCourses in context the number of actual of courses they are supposed to do.*/
        return super.validData && trackers[0]<numOfTBTCourses+3;
    }

    ArrayList<Integer[]> integerPartitioning(){
        return super.getPermutation();
    }



}