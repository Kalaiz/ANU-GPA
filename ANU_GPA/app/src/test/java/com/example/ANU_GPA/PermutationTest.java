package com.example.ANU_GPA;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/*Test does not cover negative number;Since by tweaking the
     android app, negative numbers can't be made as an input.
   */
public class PermutationTest {

    private float cgpa;
    private int coursesDone;
    private int totalCourses;
    private float gpaWanted;
    private Permutation p;
    private PermutationGenerator pg;

    @Test
    public void baseCase() {
        cgpa=0;
        coursesDone=0;
        totalCourses=0;
        gpaWanted=0;
        assertion();
    }

    @Test
    public void normalTest() {
        cgpa=4.125f;
        coursesDone=8;
        totalCourses=24;
        gpaWanted=5;
        assertion();
    }

    @Test
    public void extremeTest() {
        //TODO: Make test more vigorous.
        cgpa=9f;
        coursesDone=7;
        totalCourses=80;
        gpaWanted=6;
        assertion();
    }

    public void assertion(){
        p = new Permutation(cgpa, coursesDone, totalCourses, gpaWanted);
        assertTrue("Wrong result for case which does not  consider Num Of Fails;" +
                        "pointsNeeded: "+p.pointsNeeded+" & numOfCourses: " + p.numOfTBTCourses,
                check(false));
        pg=new PermutationGenerator(cgpa, coursesDone, totalCourses, gpaWanted);
        assertTrue("Wrong result for case which  consider Num of Fails;" +
                        "\n cgpa: "+ cgpa +"\n coursesDone: "+coursesDone+ "\n totalCourses: "+
                        totalCourses + "\n gpaWanted: "+ gpaWanted,
                check(true));
    }

    public boolean check(boolean numOfFailsNeeded){
        if(numOfFailsNeeded){
            ArrayList<Integer[]> original=new ArrayList<>();
            while(pg.hasNext()){
                Integer[] output =pg.next();
                if(output!=null){
                    original.add(output);

                }
            }
            ArrayList<Integer[]> test=testPermutationNumOfFails(cgpa,coursesDone,totalCourses,gpaWanted);
            return deepUnorderedArrayChecker(original,test);
        }
        else{
            p.calculatePermutation();
            Object[]original=p.getPermutation().toArray();
            Object[]test=testPermutation(p.pointsNeeded,p.numOfTBTCourses).toArray();
            return Arrays.deepEquals(original,test);
        }
    }


    /**
     * A Brute force way for the case which does not need Number of Fails.
     * It finds possible permutation given the totalPoints required- derived from factors like Estd GPA.
     * @param: pointsNeeded - the total number of points required to achieve the estd GPA
     * @param: numOfTBTCourses - the number of courses yet to be taken
     * @Return: An ArrayList which contains all the "Required" permutation;permutation in accordance to estd GPA.
     */
    public ArrayList testPermutation(int pointsNeeded, int numOfTBTCourses){
        ArrayList permutations=new ArrayList();
        if(cgpa>7|totalCourses>80|gpaWanted>7|coursesDone>80){
            return permutations;
        }
        for(int nhd=0;nhd<numOfTBTCourses;nhd++){
            for(int nd=0;nd<numOfTBTCourses-(nhd-1);nd++){
                for(int ncr=0;ncr<numOfTBTCourses-(nd-1);ncr++){
                    for(int np=0;np<numOfTBTCourses-(ncr-1);np++){
                        if (pointsNeeded - (nhd * 7 + nd * 6 + ncr * 5 + np * 4) == 0 && numOfTBTCourses == nhd + nd + ncr + np){
                            permutations.add(new Integer[]{nhd,nd,ncr,np});

                        }
                    }
                }
            }
        }
        return permutations;
    }

    /**
     *A Brute force way for the case which needs Number of Fails in the results.
     *It finds the possible permutation given the totalpoints required- derived from factors like Estd GPA.
     * @param: pointsNeeded - the total number of points required to achieve the estd GPA
     * @param: numOfTBTCourses - the number of courses yet to be taken
     * @Return: An ArrayList which contains all the "Required" permutation;permutation in accordance to estd GPA.
     */
    public static ArrayList<Integer[]> testPermutationNumOfFails(float cgpa,int coursesDone,int totalCourses,float gpaWanted){
        Permutation p = new Permutation(cgpa,coursesDone,totalCourses,gpaWanted);
        int numOfTBTCourses=p.numOfTBTCourses;
        int totalPoints;
        ArrayList<Integer[]> permutations=new ArrayList();
        if(cgpa>7|totalCourses>80|gpaWanted>7|coursesDone>80){
            return permutations;
        }
        for(int nf=1;nf<p.numOfTBTCourses+3;nf++){
            for(int nhd=0;nhd<=numOfTBTCourses+nf;nhd++){
                for(int nd=0;nd<=numOfTBTCourses+nf;nd++){
                    for(int ncr=0;ncr<=numOfTBTCourses+nf;ncr++){
                        for(int np=0;np<=numOfTBTCourses+nf;np++){
                            totalPoints=nhd * 7 + nd * 6 + ncr * 5 + np * 4;
                            if (((totalPoints/(float)(nhd + nd + ncr + np+nf))*100)/100==gpaWanted
                                    && (p.numOfTBTCourses+nf)==nhd + nd + ncr + np){
                                permutations.add(new Integer[]{nf,nhd, nd, ncr, np});

                            }
                        }
                    }
                }
            }
        }
        return permutations;
    }


    public static boolean  deepUnorderedArrayChecker(ArrayList<Integer[]>original,ArrayList<Integer[]>test){
        if(original.size()!=test.size()){
            return false;
        }
        else{
            loop:for(int i=0;i<original.size();i++){
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

}

