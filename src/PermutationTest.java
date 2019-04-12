import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PermutationTest {

    /**
     * A Brute force way of finding possible permutation given the totalpoints required- derived from factors like Estd GPA.
     * @param: totalPointsReqd - the total number of points required to achieve the estd GPA
     * @param: numOfCourses - the number of courses yet to be taken
     * @Return: An arraylist which contains all the "Required" permutation;permutation in accordance to estd GPA.
     */
    public List testPermutation(int pointsNeeded, int numOfCourses){
        ArrayList permutations=new ArrayList();
        for(int hd=0;hd<numOfCourses;hd++){
            for(int d=0;d<numOfCourses-(hd-1);d++){
                for(int cr=0;cr<numOfCourses-(d-1);cr++){
                    for(int p=0;p<numOfCourses-(cr-1);p++){
                        for(int f=0;f<numOfCourses-(p-1);f++) {
                            if (pointsNeeded - (hd * 7 + d * 6 + cr * 5 + p * 4) == 0 && numOfCourses == hd + d + cr + p + f){
                                permutations.add(new Object[]{hd, d, cr, p, f});
                            }
                        }

                    }
                }
            }
        }
        return permutations;
    }


    @Test
    public void baseCase() {
        Permutation p=new Permutation(0,0);
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(0,0).toArray();
        assertTrue("Wrong result for totalpointReqd: "+p.pointsNeeded+" & numOfCourses: " + p.numOfTBTCourses,Arrays.deepEquals(original,test));
    }

    @Test
    public void test2() {
        Permutation p=new Permutation(102,16);
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(p.pointsNeeded,p.numOfTBTCourses).toArray();
        assertTrue("Wrong result for totalpointReqd: "+p.pointsNeeded+" & numOfCourses: " + p.numOfTBTCourses,Arrays.deepEquals(original,test));
    }

    @Test
    public void test3() {
        Permutation p=new Permutation(129,20);
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(p.pointsNeeded,p.numOfTBTCourses).toArray();
        assertTrue("Wrong result for totalpointReqd: "+p.pointsNeeded+" & numOfCourses: " + p.numOfTBTCourses,Arrays.deepEquals(original,test));
    }





}



