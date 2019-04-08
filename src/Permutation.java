public class Permutation extends  GPA {
    /*TODO:
    * 1)Set up Test Cases for the permutations
    * 2)Apply a specific permutation algorithm with constraint in accordance to the requirements (Expected overall GPA)
    * */

   private int totalPointsReqd;
   private int numOfcourses;

    int[][] Permutation  (){
      numOfcourses= super.numOfTBTCourses;
      totalPointsReqd=super.totalPointsReqd;
      return getPermutation();
   }

   public int[][] getPermutation(){
       return new int[][]{{0},{0}};
   }

}
