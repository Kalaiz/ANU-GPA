import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Permutation extends GPA{
    /*TODO:
    *1)Apply a specific permutation algorithm with constraint in accordance to the requirements (Expected overall GPA)
    * */

    int pointsNeeded;
    int numOfCourses;
    List permutations;


    Permutation(float cgpa,int numOfTCourses,int numOfTBTCourses,float gpaWanted){//when cgpa is known there's no need to create a GPA object ,
      super(cgpa,numOfTCourses,numOfTBTCourses,gpaWanted);                        //one can use this to get permutations directly
        numOfCourses= super.numOfTBTCourses;
        pointsNeeded=super.pointsNeeded;
        calculatePermutation();
   }


    Permutation(int pointsNeeded,int numOfCourses){ //when the cgpa is unknown ,pointsNeeded and numOfCourses  are extracted manually from GPA object
        super();//using default constructor         //and then a new  permutation object is created.
        this.numOfCourses= numOfCourses;
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
        int n=0;
        while(trackers[0]!=numOfCourses+1){//while number of hds does'nt exceed the total number of courses
            if(stack.size()==4){
                int val=numOfCourses-sumOfStack;
                stack.push(val);
                sumOfStack+=val;
                n++;
                if(points==pointsNeeded){//sumOfstack is guaranteed to be equivalent to  numOfCourse
                    permutations.add(stack.toArray()); //converting it to an array for these sake of the test
                }
                points-=stack.get(3)*4;//updating the points such that  4th element is removed
                sumOfStack-=(stack.pop()+stack.pop());//removing the 4th(passes) & 5th(fails) from stack & updating the sumOfStack
            }
            else {
                int size=stack.size();
                int val=trackers[size];
                n++;
                if(val<=numOfCourses-sumOfStack  && points<=pointsNeeded) {//when it reaches this line val when added to sumOfstack;
                    sumOfStack+=val;                                                 // the new sumOfstack must'nt be higher than the numOfCourses
                    points+=val*GRADEVALUES[size];
                    ++trackers[size];
                    stack.push(val);
                }
                else {
                    //if tracker's value has reached it's max
                    trackers[size]=0;
                    int temp=stack.pop();
                    points-=temp*GRADEVALUES[size-1];//due to popping of values from stack
                    sumOfStack-=temp;
                }
            }
        }
        System.out.println("Approx method checks : "+n);
        this.permutations=permutations;}







   public List getPermutation(){
       return permutations;

   }

}
