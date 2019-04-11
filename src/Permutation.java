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


    Permutation(float cgpa,int numOfTCourses,int numOfTBTCourses,float gpaWanted){
      super(cgpa,numOfTCourses,numOfTBTCourses,gpaWanted);
        numOfCourses= super.numOfTBTCourses;
        pointsNeeded=super.pointsNeeded;
   }



    Permutation(int pointsNeeded,int numOfCourses){
        super();//using default constructor
        this.numOfCourses= numOfCourses;
        this.pointsNeeded=pointsNeeded;
        calculatePermutation();

    }


    /**
     * Obtains all valid permutation and returns it as an Arraylist
     * with the help of Stacks
     * @Return: An array list of all required permutation such that each permutation is ordered as [HDs,Ds,CRs,Ps,Fs]
     * */
   public void calculatePermutation(){
       int points=0;
       int sumOfStack=0;
       int[] trackers={0,0,0,0};
       Stack<Integer> stack=new Stack();
       ArrayList permutations=new ArrayList();
       do{
           if(stack.size()==4){
               int val=numOfCourses-sumOfStack;
               stack.push(val);
               sumOfStack+=val;
               if(points==pointsNeeded){//sumOfstack is guarenteed to be equivalent to  numOfCourse
                   permutations.add(stack);
               }
               points-=stack.get(3)*GRADEVALUES[3];//updating the points such that  4th element is removed
               sumOfStack-=(stack.pop()+stack.pop());//removing the 4th(passes) & 5th(fails) from stack & updating the sumOfStack

           }
           else {
               int val=trackers[stack.size()];
               if(val<=numOfCourses-sumOfStack  && points<=pointsNeeded) {//when it reaches this line val when added to sumOfstack;
                         sumOfStack+=val;                                                 // the new sumOfstack must'nt be higher than the numOfCourses
                         points+=val*GRADEVALUES[stack.size()];
                         stack.push(val);
                    ++trackers[stack.size()-1];

               }
               else {
                   //if tracker's value has reached it's max
                   trackers[stack.size()]=0;
                   int temp=stack.pop();
                   points-=temp*GRADEVALUES[stack.size()];
                   sumOfStack-=temp;

               }
               }
           }while(trackers[0]!=numOfCourses+1);
      this.permutations=permutations;}



    public static void main(String[] args) {
        Permutation p=new Permutation(102,16);
        p.calculatePermutation();
        System.out.println(p.permutations.size());
    }

   public List getPermutation(){
       return permutations;

   }

}
