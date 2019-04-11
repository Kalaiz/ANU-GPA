/**
 * A class in which upon retrieval of data it does  any calculation of GPA or recalculation if necessary.
 * This class will store data crucial for the permutation.- totalPointsReqd and numOfCourses.
 **/
public class GPA {

    /*TODO:
     * 1)Set up Test Cases for the calc
     * 2)Implement gpaCalc
     */

    final int GRADEVALUES[] = {7,6,5,4,0};
    //All instance variable below are required for totalPointsReqd
    int numOfTBTCourses;//num of courses yet To Be Taken (TBT)
    int numOfTCourses;//num of courses Taken already
    float cgpa;//Current GPA
    private boolean calc;//states whether there needs to be any calculation done
    //the below variables are related to the calculate total points function
    float gpaWanted;
    int pointsNeeded;

    public GPA(float cgpa,int numOfTCourses,int numOfTBTCourses,float gpaWanted) {
        //if cgpa is known
    	this.numOfTCourses=numOfTCourses;
    	this.numOfTBTCourses=numOfTBTCourses;
    	this.cgpa=cgpa;
    	calculateTotalPoints(gpaWanted);
    }

    public GPA(int[] grades,int numOfTBTCourses,float gpaWanted) {
        //if cgpa is not known.
        gpaCalc(grades);
        this.numOfTBTCourses=numOfTBTCourses;
        this.gpaWanted=gpaWanted;

    }

    public void calculateTotalPoints(float wantedGPA){
        /*Calculates Total Points required based on numOfTCourses & cgpa */
        int currentPoints = (int) ((cgpa*numOfTCourses)+0.5);
        pointsNeeded = (int)(wantedGPA*(numOfTCourses+numOfTBTCourses)+0.5)-currentPoints;
        gpaWanted = wantedGPA;
    }

    public void gpaCalc(int[] data) {
    	//calculates the gpa and number of courses taken from your grades
    	float total = 0;
    	int tCourses = 0;
    	for (int x = 0; x < 5; x++) {
    		total = total + (data[x]*GRADEVALUES[x]);
    		tCourses = tCourses+ data[x];
    	}
        cgpa = total/tCourses;
        numOfTCourses = tCourses;
    }
    GPA(){}
    
    //below are the 5 functions to obtain a useful value from the gpa object
    //just in case we decided to make the values private. can delete later
    public float getCGPA() {
    	return cgpa;
    }
    
    public float getgpaWanted() {
    	return gpaWanted;
    }
    
    public int getTBTCourses() {
    	return numOfTBTCourses;
    }
    
    public int getTCourses() {
    	return numOfTCourses;
    }
    
    public int getPointsReq() {
    	return pointsNeeded;
    }
    
    //three functions to set the values of a gpa object. the other two arent
    //needed as there should be no reason to set those manually
    public void setCGPA(float gpa) {
    	cgpa = gpa;
    }
    
    public void setTBTCourses(int courseNum) {
    	numOfTBTCourses = courseNum;
    }
    
    public void setTCourses(int courseNum) {
    	numOfTCourses = courseNum;
    }
}
