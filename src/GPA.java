/**
 * A class in which upon retrieval of data it does  any calculation of GPA or recalculation if necessary.
 * This class will store data crucial for the permutation.- totalPointsReqd and numOfCourses.
 **/
public class GPA {

    /*TODO:
     * 1)Set up Test Cases for the calc
     * 2)Implement gpaCalc
     */

    int totalPointsReqd;
    int numOfTBTCourses;//num of courses yet To Be Taken (TBT)
    //All instance variable below are required for totalPointsReqd
    int numOfTCourses;//num of courses Taken already
    float cgpa;//Current GPA
    float estdGPA;
    private boolean calc;//states whether there needs to be any calculation done


    public void calculateTotalPoints(){
        /*Calculates Total Points required based on numOfTCourses & cgpa */
        totalPointsReqd=0;
    }


    public GPA() {
        //default constructor
    }

    public float gpaCalc(int[] data) {
        return 0;
    }





}
