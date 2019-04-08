import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PermutationTest {

    Permutation p=new Permutation();

    public List testPermutation(int totalPointsReqd, int numOfCourses){
        ArrayList permutations=new ArrayList();
        for(int hd=0;hd<numOfCourses;hd++){
            for(int d=0;d<numOfCourses-hd;d++){
                for(int cr=0;cr<numOfCourses-d;cr++){
                    for(int p=0;p<totalPointsReqd-cr;p++){
                        if(totalPointsReqd-(hd*7+d*6+cr*5+p*4)==0 && numOfCourses==hd+d+cr+p)
                            permutations.add(new int[]{hd,d,cr,p});
                    }
                }
            }
        }
        return permutations;}


    @Test
    public void baseCase() {
        p.totalPointsReqd=0;
        p.numOfCourses=0;
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(0,0).toArray();
        assertTrue("Unsatisfied condition for totalpointReqd: "+p.totalPointsReqd+" & numOfCourses: " + p.numOfCourses,Arrays.deepEquals(original,test));
    }

    @Test
    public void test2() {
        p.totalPointsReqd=102;
        p.numOfCourses=16;
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(p.totalPointsReqd,p.numOfCourses).toArray();
        assertTrue("Unsatisfied condition for totalpointReqd: "+p.totalPointsReqd+" & numOfCourses: " + p.numOfCourses,Arrays.deepEquals(original,test));
    }

    @Test
    public void test3() {
        p.totalPointsReqd=129;
        p.numOfCourses=20;
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(p.totalPointsReqd,p.numOfCourses).toArray();
        assertTrue("Unsatisfied condition for totalpointReqd: "+p.totalPointsReqd+" & numOfCourses: " + p.numOfCourses,Arrays.deepEquals(original,test));
    }




}