import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * A class which helps one to find the possible permutations provided the basic information:
 * 1)pointsNeeded - points needed in order to achieve the user's gpaWanted.
 * 2)numOfTBTCourses - The number of courses to be taken.
 *
 * There are two constructors for this class:
 * -> Permutation(float gpa, int coursesDone, int totalCourses)
 * will be used when the cgpa(current gpa) is known.
 * -> Permutation(int pointsNeeded,int numOfCourses)
 * will be used when the cgpa is unknown.
 *
 * The possible permutation is stored as an ArrayList;Use getPermutation(along with the Permutation object) to obtain it.
 *
 * Explanation of how permutation class uses attributes:
 * Example:
 * Based on a user inputs:
 *    gpaWanted = 6.0
 *    numOfTBTCourses = 8
 *    numOfTCourses = 16
 *    cgpa=5.6
 *    Let numOfTotalCourses - the total number of courses to be taken in the user's degree
 *      numOfTotalCourses = numOfTBTCourses + numOfTCourses
 *
 *    pointsNeeded = Math.round((gpaWanted * (numOfTotalCourses)) - cgpa * numOfTCourses)
 *                =  Math.round((6.0 * (8 + 16)) - 5.6 * 16)
 *                = 54
 *
 * How calculated pointsNeeded is being used here?:
 *        pointsNeeded=54
 *        numOfTBTCourses = 8
 *        calculatePermutations() finds for the possible permutation such that:
 *       for [nHDs,nDs,nCRs,nPs,nFs] where n represents "number of"
 *       nHDs*7 + nDs*6 + nCRs*5 + nFs*0 == 54
 * */
public class Permutation extends GPA{
    /*TODO:
     *1)Score system for possible permutation.
     */

    int pointsNeeded;//
    int numOfTBTCourses;
    private List permutations;

    /**
     * Creates a Permutation object which calculate the possible permutation based
     * on cgpa & other afflicted attributes.When cgpa is known there's no need to
     * create a GPA object(No need to calculate current gpa),one can use this to get
     * permutations directly.
     * @param cgpa - Current gpa
     * @param coursesDone - the number of courses done
     * @param totalCourses - the total number of course in the span of the degree
     * @param gpaWanted - The gpa which the user is trying to achieve.
     * */
    Permutation(float cgpa, int coursesDone, int totalCourses,float gpaWanted){
        super(cgpa, coursesDone,totalCourses);
        numOfTBTCourses= totalCourses-coursesDone;
        pointsNeeded=super.pointsNeeded;
        calculatePointsNeeded(gpaWanted);
        calculatePermutation();
    }


    /**
     * Creates a Permutation object which calculate the possible permutation by
     * calculating GPA before invoking this constructor.Based on results
     * from GPA class pointsNeeded and numOfTBTCourses can be obtained and eventually be used here.
     * (onw would use this when the cgpa is unknown)
     * @param pointsNeeded - points needed in order to attain the gpaWanted
     * @param numOfTBTCourses- the number of courses to be taken
     * */
    Permutation(int pointsNeeded,int numOfTBTCourses){
        super();//using default constructor
        this.numOfTBTCourses= numOfTBTCourses;
        this.pointsNeeded=pointsNeeded;
        calculatePermutation();
    }


    /**
     * Obtains all valid permutation and then updates the arraylist permutations
     * with the help of Stacks
     * permutations:An array list of all required permutations such that each permutation is ordered as [HDs,Ds,CRs,Ps,Fs]
     * */
    public void calculatePermutation(){
        int points=0;
        int sumOfStack=0;
        int[] trackers={0,0,0,0};
        Stack<Integer> stack=new Stack();
        ArrayList permutations=new ArrayList();
        while(trackers[0]!=numOfTBTCourses+1){//while number of hds does'nt exceed the total number of courses
            if(stack.size()==4){
                int val=numOfTBTCourses-sumOfStack;
                stack.push(val);
                if(points==pointsNeeded){//sumOfstack is guaranteed to be equivalent to  numOfCourse
                    permutations.add(stack.toArray()); //converting it to an array for these sake of the test
                }
                stack.pop();//removing the  5th element(fails)
                int numOfPasses=stack.pop();
                points-=numOfPasses*4;//updating the points such that  4th element is removed
                sumOfStack-=(numOfPasses);// updating the sumOfStack
            }
            else {
                int size=stack.size();
                int val=trackers[size];
                if(val<=numOfTBTCourses-sumOfStack  && points<=pointsNeeded) {//when it reaches this line val when added to sumOfstack;
                    sumOfStack+=val;                                                 // the new sumOfstack must'nt be higher than the numOfCourses
                    points+=val*GRADEVALUES[size];
                    ++trackers[size];
                    stack.push(val);
                }
                else {
                    //if tracker's value has reached it's max
                    trackers[size]=0;
                    int temp=stack.pop();
                    points-=temp*GRADEVALUES[size-1];//size -1 due to popping of values from stack
                    sumOfStack-=temp;
                }
            }
        }
        this.permutations=permutations;
    }


    /**
     * Returns the possible permutations
     * @return permutations - An arraylist which contains the possible permutations.*/
    public List getPermutation(){
        return permutations;
    }

}
