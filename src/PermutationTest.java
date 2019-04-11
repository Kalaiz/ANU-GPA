import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PermutationTest {

    Permutation p=new Permutation(0,0);


    /**
     * A Brute force way of finding possible permutation given the totalpoints required- derived from factors like Estd GPA.
     * @param: totalPointsReqd - the total number of points required to achieve the estd GPA
     * @param: numOfCourses - the number of courses yet to be taken
     * @Return: An arraylist which contains all the "Required" permutation;permutation in accordance to estd GPA.
     */
    public static List testPermutation(int totalPointsReqd, int numOfCourses){
        ArrayList permutations=new ArrayList();
        int n=0;
        for(int hd=0;hd<numOfCourses;hd++){
            for(int d=0;d<numOfCourses-(hd-1);d++){
                for(int cr=0;cr<numOfCourses-(d-1);cr++){
                    for(int p=0;p<totalPointsReqd-(cr-1);p++){
                        for(int f=0;f<totalPointsReqd-(p-1);f++) {
                            n++;
                            if (totalPointsReqd - (hd * 7 + d * 6 + cr * 5 + p * 4) == 0 && numOfCourses == hd + d + cr + p + f){
                                permutations.add(new Object[]{hd, d, cr, p, f});
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Approx Number of checks of test method :" + n);
        return permutations;}


    @Test
    public void baseCase() {
        p.pointsNeeded=0;
        p.numOfCourses=0;
        p.calculatePermutation();
        Object[] original=p.getPermutation().toArray();

        Object[] test=testPermutation(0,0).toArray();
        assertTrue("Wrong result for totalpointReqd: "+p.pointsNeeded+" & numOfCourses: " + p.numOfCourses,Arrays.deepEquals(original,test));
    }

    @Test
    public void test2() {
        p.pointsNeeded=102;
        p.numOfCourses=16;
        p.calculatePermutation();
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(p.pointsNeeded,p.numOfCourses).toArray();
        assertTrue("Wrong result for totalpointReqd: "+p.pointsNeeded+" & numOfCourses: " + p.numOfCourses,Arrays.deepEquals(original,test));
    }

    @Test
    public void test3() {
        p.pointsNeeded=129;
        p.numOfCourses=20;
        p.calculatePermutation();
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(p.pointsNeeded,p.numOfCourses).toArray();
        assertTrue("Wrong result for totalpointReqd: "+p.pointsNeeded+" & numOfCourses: " + p.numOfCourses,Arrays.deepEquals(original,test));
    }




}