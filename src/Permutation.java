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
       //stack.push(trackers[0]);
      do{
           if(stack.size()==4){
               stack.push(numOfCourses-sumOfStack); //calculating remaining left over
               sumOfStack+=stack.peek();
               System.out.println(points);
               if(pointsNeeded==points){
                   System.out.println("Adding");
                   permutations.add(stack.toArray()); //Converting  stack to array for the sake of the test , can be optimised later
               }
               System.out.println(stack);
               points-=stack.get(3)*GRADEVALUES[3];
               sumOfStack-=(stack.pop()+stack.pop());// 5th  & 4th element being removed from stack & subtracted at the same time
           }
           else{
               int size=stack.size();
               size=(size==0)?1:size+1;
               int val=trackers[size-1];
               if(val!=numOfCourses-sumOfStack+1 && points<=pointsNeeded){
                   sumOfStack+=val;  //updating sumOfStack
                   ++trackers[size-1];
                   points+=val*GRADEVALUES[size-1];
                   stack.push(val);
                  //updating points in accordance
               }
               else{
                   System.out.println("TRACKERS "+ trackers[0]+ " "+ trackers[1] + " "+trackers[2]+ " "+trackers[3]);
                   trackers[size-1]=0;
                   if(size-1==1){
                       stack.clear();
                   }
                   else {
                       System.out.println("Points "+ points);
                       System.out.println("STACK   "+stack);
                       int stackval=stack.pop();
                       sumOfStack-=stackval;
                       points -= stackval * GRADEVALUES[size - 1];//updating points
                   }
               }
           }
       } while(!stack.empty());
       System.out.println(permutations.size());
        this.permutations=permutations;
   }

    public static void main(String[] args) {
        Permutation p=new Permutation(102,16);
        p.calculatePermutation();
        System.out.println(p.permutations.size());
        /*for(Object t : p.permutations){
            for(int x : (int[])t){
                System.out.println(x);
            }

        }*/
    }

   public List getPermutation(){
       return permutations;

   }

}
