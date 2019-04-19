package com.example.ANU_GPA;

import java.util.ArrayList;
import java.util.Stack;
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
public class Permutation extends GPA {


    int numOfTBTCourses; //The number of courses to be taken.
    /*List of possible permutation based on the above 2 attributes in which
      each permutation is ordered as ordered as [HDs,Ds,CRs,Ps,Fs]*/
    private ArrayList<Integer[]> permutations =new ArrayList<>();


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
        super(cgpa, coursesDone,totalCourses);
        numOfTBTCourses= totalCourses-coursesDone;
        calculatePointsNeeded(gpaWanted);
        calculatePermutation();
    }


    /**
     * Creates a Permutation object which calculate the possible permutation by
     * calculating GPA before invoking this constructor.Based on results
     * from GPA class pointsNeeded and numOfTBTCourses can be obtained and eventually be used here.
     * (one would use this when the cgpa is unknown)
     * @param pointsNeeded - Points needed in order to attain the gpaWanted.
     * @param numOfTBTCourses- The number of courses to be taken.
     * */
    Permutation(int pointsNeeded,int numOfTBTCourses){
        super(); //using default constructor
        this.numOfTBTCourses= numOfTBTCourses;
        this.pointsNeeded=pointsNeeded;
        calculatePermutation();
    }


    /**
     * Obtains all valid permutation and then updates the ArrayList permutations.
     * */
    public void calculatePermutation(){
        int points=0;
        int sumOfStack=0;
        /*trackers will act like a storage which tracks the number of grades whose
          value will be used accordance to the sequence of calculating a permutation*/
        int[] trackers={0,0,0,0};
        int val,numOfPasses,size; //For temporary storage of stack values,numOfPasses,size of stack.
        Stack<Integer> stack=new Stack();
        while(trackers[0]!=numOfTBTCourses+1){ //While number of hds does'nt exceed the total number of courses.
            if(stack.size()==4){
                val=numOfTBTCourses-sumOfStack;
                stack.push(val);
                if(points==pointsNeeded){
                    //Converting it to a new array in order to avoid referencing issues
                    permutations.add(stack.toArray(new Integer[5]));
                }
                stack.pop(); //Removing the 5th element(num of fails)
                numOfPasses=stack.pop();
                points-=numOfPasses*4; //Updating the points such that the 4th element is removed.
                sumOfStack-=(numOfPasses);
            }
            else {
                size=stack.size();
                val=trackers[size];
                /*When it reaches the line below ,val when added to sumOfStack;
                  The resultant must'nt be higher than the numOfTBTCourses*/
                if(val+sumOfStack<=numOfTBTCourses && points<=pointsNeeded) {
                    sumOfStack+=val;
                    points+=val*GRADEVALUES[size];
                    ++trackers[size];
                    stack.push(val);
                }
                else {
                    //If tracker's value has reached it's max
                    trackers[size]=0;
                    val=stack.pop();
                    points-=val*GRADEVALUES[size-1];//size - 1 due to popping of values from stack
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
