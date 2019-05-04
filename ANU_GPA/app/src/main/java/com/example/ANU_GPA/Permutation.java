package com.example.ANU_GPA;

import java.util.ArrayList;
import java.util.Stack;

/*Authorship
 CalculateTotalPoints function - Jared
 Rest of the class - Kalai(u6555407)*/

/**
 * A class which helps one to find the possible permutations provided the basic information.
 * 1)pointsNeeded & 2)numOfTBTCourses
 * Explanation of how permutation class uses attributes:
 * Example:
 * Based on a user inputs:
 *    gpaWanted = 6.0
 *    numOfTBTCourses = 8 //Number of To be taken Courses
 *    numOfTCourses = 16 //Number of Total Courses
 *    cgpa=5.6
 *    Let numOfTotalCourses - The total number of courses to be taken in the user's degree.
 *        numOfTotalCourses = numOfTBTCourses + numOfTCourses
 *
 *    pointsNeeded = Math.round((gpaWanted * (numOfTotalCourses)) - cgpa * numOfTCourses)
 *                 = Math.round((6.0 * (8 + 16)) - 5.6 * 16)
 *                 = 54
 *
 * How calculated pointsNeeded is being used here?:
 * Each Possible permutation represented as [nHDs,nDs,nCRs,nPs,nFs] where n is the "number of".
 *        pointsNeeded=54
 *        numOfTBTCourses = 8
 *        calculatePermutations() finds for the possible permutation such that:
 *        nHDs*7 + nDs*6 + nCRs*5 + nFs*0 == 54 && sum[nHDs,nDs,nCRs,nPs,nFs] == numOfTBTCourses
 * */
public class Permutation {

    int numOfTBTCourses; //The number of courses to be taken.
    /*List of possible permutation based on the above 2 attributes in which
      each permutation is ordered as ordered as [HDs,Ds,CRs,Ps,Fs]*/
    private ArrayList<Integer[]> permutations =new ArrayList<>();
    int pointsNeeded;
    float gpaWanted;


    /*Calculates PointsNeeded based on numOfTCourses & cgpa
     * Authorship: Jared  */
    public void calculatePointsNeeded(float cgpa, int coursesDone, int totalCourses,float gpaWanted){
        pointsNeeded = (int)(gpaWanted*(totalCourses)+0.5)-(int)(cgpa*coursesDone+0.5);
        this.gpaWanted=gpaWanted;
    }


    /**
     * Creates a Permutation object which calculate the possible permutation based
     * on cgpa & other afflicted attributes.When cgpa is known there's no need to
     * create a GPA object(No need to calculate current gpa),one can use this to get
     * permutations directly.
     * @param cgpa - Current gpa
     * @param coursesDone - The number of courses done.
     * @param totalCourses - The total number of course in the span of the degree.
     * @param gpaWanted - The gpa which the user is trying to achieve.
     * */
    Permutation(float cgpa, int coursesDone, int totalCourses,float gpaWanted){
        numOfTBTCourses= totalCourses-coursesDone;
        calculatePointsNeeded(cgpa,coursesDone,totalCourses,gpaWanted);

    }



    /**
     * Obtains all valid permutation and then updates the ArrayList permutations.
     * */
    public void calculatePermutation(){
        Grades[] grades=Grades.values();
        int points=0;
        int sumOfStack=0;
        /*trackers will act like a storage which tracks the number of grades whose
          value will be used accordance to the sequence of calculating a permutation*/
        int[] trackers={0,0,0,0};
        //For temporary storage of stack values,numOfPasses,numOfCredits,size of stack.
        int val,numOfPasses,numOfCredits,size;
        Stack<Integer> stack=new Stack();
        while(trackers[0]!=numOfTBTCourses+(numOfTBTCourses/2)+1){ //While number of hds does'nt exceed the total number of courses.
            if(stack.size()==4){
                for(int i=0;i<=numOfTBTCourses/2;i++) {
                    val=i;
                    stack.push(val);
                    sumOfStack += val;
                    if (sumOfStack-val == numOfTBTCourses + val
                            && (((points / (float) sumOfStack) * 100) / 100 == gpaWanted)) {
                        permutations.add(stack.toArray(new Integer[5])); //converting it to an array for these sake of the test
                    }
                    sumOfStack -= val;
                    stack.pop();//removing the  5th element(fails)

                }
                numOfPasses = stack.pop();
                points -= numOfPasses * 4;//updating the points such that  4th element is removed
                sumOfStack -= (numOfPasses);// updating the sumOfStack
            }
            else {
                size=stack.size();
                val=trackers[size];
                /*When it reaches the line below ,val when added to sumOfStack;
                  The resultant must'nt be higher than the numOfTBTCourses*/
                if(val+sumOfStack<=numOfTBTCourses+(numOfTBTCourses/2)) {
                    sumOfStack+=val;
                    points+=val*grades[size].gradePoints;
                    ++trackers[size];
                    stack.push(val);
                }
                else {
                    //If tracker's value has reached it's max
                    trackers[size]=0;
                    val=stack.pop();
                    points-=val*grades[size-1].gradePoints;//size - 1 due to popping of values from stack
                    sumOfStack-=val;
                }
            }
        }
    }


    /**
     * Returns the possible permutations
     * @return permutations - An ArrayList which contains the possible permutations.*/
    public ArrayList<Integer[]> getPermutation(){
        return permutations;
    }

}