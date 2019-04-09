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
    final int  GRADEVALUES[] = {7,6,5,4,0};
    int numOfTBTCourses;//num of courses yet To Be Taken (TBT)
    //All instance variable below are required for totalPointsReqd
    int numOfTCourses;//num of courses Taken already
    float cgpa;//Current GPA
    float estdGPA;
    private boolean calc;//states whether there needs to be any calculation done


    public void calculateTotalPoints(){
        /*Calculates Total Points required based on numOfTCourses & cgpa */
        int currentPoints = (int) ((cgpa*numOfTCourses)+0.5);
        totalPointsReqd = (int)(estdGPA*(numOfTCourses+numOfTBTCourses)+0.5)-currentPoints;
        
    }


    public GPA() {
        //default constructor
    }
    
    public static void main(String[] args) {
	}

    public float gpaCalc(int[] data) {
    	float total = 0;
    	int tCourses = 0;
    	for (int x = 0; x < 5; x++) {
    		total = total + (data[x]*GRADEVALUES[x]);
    		//the below line may not be neccessary, was added to make sure 
    		//gpacalc worked without the balue numOfTCources. can be changed
    		tCourses = tCourses+ data[x];
    	}
        return (total/tCourses);
    }





}
