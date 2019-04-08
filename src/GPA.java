/**
 * A class in which upon retrieval of data it does  any calculation of GPA or recalculation if necessary.
 * This class will store data crucial for the permutation.- totalPointsReqd and numOfCourses.
 **/
public class GPA implements GPAcalc{

    /*TODO:
     * 1)Set up Test Cases for the calc(OPTIONAL)
     * 2)Implement the GPAcalc
     */

    int totalPointsReqd;
    int numOfTBTCourses;//num of courses yet To Be Taken (TBT)
    //All instance variable below are required for totalPointsReqd
    int numOfTCourses;//num of courses Taken already
    float cgpa;//Current GPA
    float estdGPA;
    private boolean calc;//states whether there needs to be any calculation done

    GPA(float[] data){

    }

    public void calculateTotalPoints(){
        /*Calculates Total Points required based on numOfTCourses & cgpa */
        totalPointsReqd=0;
    }



    public GPA() {
        //default constructor
    }


    
    @Override
    public int[] GPAcalc(float[] data) {
        /**
         * @param - float[] - [Estd GPA,numOfcourses,numHDs,numDs,numCRs,numPs]
         * @returns -int[] -[(1),numOfcourses] where (1)=The total number of points required to achieve to Estd GPA
         **/
        return new int[]{0,0};
    }





}
