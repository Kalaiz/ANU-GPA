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


   public List getPermutation(){
       int sumOfStack=0;
       int points=0;
       int[] trackers={0,0,0,0};
       int[] gpa ={7,6,5,4};
       Stack<Integer> stack=new Stack();
       ArrayList permutations=new ArrayList();
       stack.addElement(trackers[0]);
       while(!stack.empty()){
           if(stack.size()==4){
               stack.addElement(numOfCourses-sumOfStack); //calculating remaining left over
               if(totalPointsReqd==points){
                   permutations.add(stack.toArray()); //Converting  stack to array for the sake of the test , can be optimised later
               }
               sumOfStack-=stack.pop()-stack.pop();// 5th  & 4th element being removed from stack & subtracted at the same time
           }
           else{
               int val=trackers[stack.size()];
               if(val!=numOfCourses-sumOfStack+1){//+1 so to consider permutation which has zeros in it
                   points+=val*gpa[stack.size()];//updating points in accordance
                   sumOfStack+=val;  //updating sumOfStack
                   stack.addElement(val);
                   trackers[stack.size()]++;
               }
               else{
                   trackers[stack.size()]=0;
                   stack.pop();// or just remove if there's a function available
               }

           }
       }
        return permutations;
   }






}
