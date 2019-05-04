package com.example.ANU_GPA;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
        int numOfTBTCourses=p.numOfTBTCourses+p.numOfTBTCourses/2;
        int totalPoints;
        ArrayList permutations=new ArrayList();
        for(int nf=0;nf<=p.numOfTBTCourses/2;nf++){
            for(int nhd=0;nhd<=numOfTBTCourses;nhd++){
                for(int nd=0;nd<=numOfTBTCourses;nd++){
                    for(int ncr=0;ncr<=numOfTBTCourses;ncr++){
                        for(int np=0;np<=numOfTBTCourses;np++){
                            totalPoints=nhd * 7 + nd * 6 + ncr * 5 + np * 4;
                            if (((totalPoints/(float)(nhd + nd + ncr + np+nf))*100)/100==gpaWanted
                                    && (p.numOfTBTCourses+nf)==nhd + nd + ncr + np){
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

        Permutation p=new Permutation(4.125f,8,24,5);
        System.out.println(testPermutation(4.125f,8,24,5).size());
        System.out.println(p.getPermutation().size());
        for (Integer[] obj:testPermutation(4.125f,8,24,5))
        {for(Integer val:obj){
            System.out.print(val + " ");
        }
            System.out.println(" ");
        }
        System.out.println("ORIGINAL");
        for (Integer[] obj:p.getPermutation())
        {for(Integer val:obj){
            System.out.print(val + " ");
        }
            System.out.println(" ");
        }

    }
    public static boolean  deepUnorderedArrayChecker(ArrayList<Integer[]>original,ArrayList<Integer[]>test){
        if(original.size()!=test.size()){
            return false;
        }
        else{
      loop:     for(int i=0;i<original.size();i++){
                for(int j=0;j<test.size();j++){
                    if(Arrays.equals(original.get(i),test.get(j))){
                        original.remove(i);
                        i--;
                        continue loop;
                    }
                }
            }
        }
        return original.isEmpty();
    }

    @Test
    public void baseCase() {
        Permutation p=new Permutation(0,0,0,0);
        ArrayList<Integer[]> original=p.getPermutation();
        ArrayList<Integer[]> test=testPermutation(0,0,0,0);
        assertTrue("Wrong result for pointsNeeded: "+p.pointsNeeded+" & numOfCourses: " + p.numOfTBTCourses,deepUnorderedArrayChecker(original,test));
    }


    @Test
    public void test2() {
        Permutation p=new Permutation(4.125f,8,24,5);
        ArrayList<Integer[]> original=p.getPermutation();
        ArrayList<Integer[]> test=testPermutation(4.125f,8,24,5);
        assertTrue("Wrong result for pointsNeeded: "+p.pointsNeeded+" & numOfCourses: " + p.numOfTBTCourses,deepUnorderedArrayChecker(original,test));
    }


    @Test
    public void test3() {
        //  Permutation(float cgpa, int coursesDone, int totalCourses,float gpaWanted)
        Permutation p=new Permutation(6.28f,7,24,6);
        ArrayList<Integer[]> original=p.getPermutation();
        ArrayList<Integer[]> test=testPermutation(6.28f,7,24,6);
        assertTrue("Wrong result for pointsNeeded: "+p.pointsNeeded+" & numOfCourses: " + p.numOfTBTCourses,deepUnorderedArrayChecker(original,test));
    }
}