import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Permutation  {
    /*TODO:
    *1)Apply a specific permutation algorithm with constraint in accordance to the requirements (Expected overall GPA)
    * */
    final int[] GRADEVALUES={7,6,5,4,0};
    int totalPointsReqd;
    int numOfCourses;

    List Permutation(int numOfCourses,int totalPointsReqd){
      this.numOfCourses= numOfCourses;
      this.totalPointsReqd=totalPointsReqd;
      return getPermutation();
   }

    /**
     * Obtains all valid permutation and returns it as an Arraylist
     * with the help of Stacks
     * @Return: An array list of all required permutation such that each permutation is ordered as [HDs,Ds,CRs,Ps,Fs]
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
               int val=trackers[stack.size()-1];
               if(val!=numOfCourses-sumOfStack){
                   points+=val*GRADEVALUES[stack.size()-1];//updating points in accordance
                   sumOfStack+=val;  //updating sumOfStack
                   stack.push(val);
                   trackers[stack.size()-1]++;
               }
               else{
                   trackers[stack.size()-1]=0;
                   if(stack.size()==1){
                       stack.clear();
                   }
                   else {
                       points -= stack.pop() * GRADEVALUES[stack.size() - 1];//updating points
                   }
               }

           }
       }
        return permutations;
   }


}
