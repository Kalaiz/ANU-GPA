package com.example.ANU_GPA;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

public class PermutationTest {

    /**
     * A Brute force way of finding possible permutation given the totalpoints required- derived from factors like Estd GPA.
     * @param: pointsNeeded - the total number of points required to achieve the estd GPA
     * @param: numOfTBTCourses - the number of courses yet to be taken
     * @Return: An ArrayList which contains all the "Required" permutation;permutation in accordance to estd GPA.
     */
    public static ArrayList<Integer[]> testPermutation(float cgpa,int coursesDone,int totalCourses,float gpaWanted){
        Permutation p = new Permutation(cgpa,coursesDone,totalCourses,gpaWanted);
        int numOfTBTCourses=p.numOfTBTCourses;
        int pointsNeeded;
        ArrayList permutations=new ArrayList();
        for(int nhd=0;nhd<numOfTBTCourses;nhd++){
            for(int nd=0;nd<numOfTBTCourses-(nhd-1);nd++){
                for(int ncr=0;ncr<numOfTBTCourses-(nd-1);ncr++){
                    for(int np=0;np<numOfTBTCourses-(ncr-1);np++){
                        for(int nf=0;nf<numOfTBTCourses/2;nf++){
                          p.calculatePointsNeeded(cgpa,coursesDone,totalCourses+nf,gpaWanted);
                          pointsNeeded=p.pointsNeeded;
                        if (pointsNeeded - (nhd * 7 + nd * 6 + ncr * 5 + np * 4) == 0 && numOfTBTCourses == nhd + nd + ncr + np) {
                            permutations.add(new Integer[]{nhd, nd, ncr, np,nf});
                        }
                        }
                    }
                }
            }
        }
        return permutations;
    }

    public static void main(String[] args) {
        for(Integer[] obj :testPermutation(6.28f,7,24,6)){
           for(Integer val:obj){
               System.out.print(val + " ");
           }
            System.out.println(" ");
        };
    }
    @Test
    public void baseCase() {
        Permutation p=new Permutation(0,0,0,0);
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(0,0,0,0).toArray();
        assertTrue("Wrong result for pointsNeeded: "+p.pointsNeeded+" & numOfCourses: " + p.numOfTBTCourses,Arrays.deepEquals(original,test));
    }


    @Test
    public void test2() {
        Permutation p=new Permutation(4.125f,8,24,5);
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(4.125f,8,24,5).toArray();
        assertTrue("Wrong result for pointsNeeded: "+p.pointsNeeded+" & numOfCourses: " + p.numOfTBTCourses,Arrays.deepEquals(original,test));
    }


    @Test
    public void test3() {
        //  Permutation(float cgpa, int coursesDone, int totalCourses,float gpaWanted)
        Permutation p=new Permutation(6.28f,7,24,6);
        Object[] original=p.getPermutation().toArray();
        Object[] test=testPermutation(6.28f,7,24,6).toArray();
        assertTrue("Wrong result for pointsNeeded: "+p.pointsNeeded+" & numOfCourses: " + p.numOfTBTCourses,Arrays.deepEquals(original,test));
    }
}