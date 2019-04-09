import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Permutation extends  GPA {
    /*TODO:
    *1)Apply a specific permutation algorithm with constraint in accordance to the requirements (Expected overall GPA)
    * */

    int totalPointsReqd;
    int numOfCourses;

    List Permutation(){
      numOfCourses= super.numOfTBTCourses;
      totalPointsReqd=super.totalPointsReqd;
      return getPermutation();
   }

    /**
     * Obtains all valid permutation and returns it as an Arraylist
     * with the help of Stacks
     * @Return: An array list of all required permutation such that each permuatation is ordered as [HDs,Ds,CRs,Ps,F]
     * */
   public List getPermutation(){

       int points=0;
       int sumOfStack=0;
       int[] trackers={0,0,0,0};
       Stack<Integer> stack=new Stack();
       ArrayList permutations=new ArrayList();
       stack.push(trackers[0]);
       while(!stack.empty()){
           if(stack.size()==4){
               stack.push(numOfCourses-sumOfStack); //calculating remaining left over
               if(totalPointsReqd==points){
                   permutations.add(stack.toArray()); //Converting  stack to array for the sake of the test , can be optimised later
               }
               sumOfStack-=stack.pop()-stack.pop();// 5th  & 4th element being removed from stack & subtracted at the same time
           }
           else{
               int val=trackers[stack.size()];
               if(val!=numOfCourses-sumOfStack+1){//+1 so to consider permutation which has zeros in it
                   points+=val*GRADEVALUES[stack.size()];//updating points in accordance
                   sumOfStack+=val;  //updating sumOfStack
                   stack.push(val);
                   trackers[stack.size()]++;
               }
               else{
                   trackers[stack.size()]=0;
                   points-=stack.pop()*GRADEVALUES[stack.size()-1];//updating points
               }

           }
       }
        return permutations;
   }
   

}
