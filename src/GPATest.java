import org.junit.Test;
import static org.junit.Assert.*;
public class GPATest {
	final int GRADEVALUES[] = {7,6,5,4,0};
	
	@Test
	public void baseCase() {
		int[] testList = {0,0,0,0,0};
		GPA base = new GPA(testList, 0);
		base.calculatePointsNeeded(0);
		assertTrue("cgpa incorrect, cgpa equals : " + base.cgpa + ", should equal 0", base.cgpa == 0);
		assertTrue("taken courses incorrect, taken courses equals : " + base.numOfTCourses + ", should equal 0", base.numOfTCourses == 0);
		assertTrue("course number incorrect, course number equals : " + base.numOfCourses + ", should equal 0", base.numOfCourses == 0);
		assertTrue("wanted gpa incorrect, wanted gpa equals : " + base.gpaWanted + ", should equal 0", base.gpaWanted == 0);
		assertTrue("points needed incorrect, points needed equals : " + base.pointsNeeded + ", should equal 0", base.pointsNeeded == 0);
	}
	
	
	@Test
	public void test2() {
		int[] testList = {1,0,1,2,1};
		//the total of (7+5+(2*8) +0)/5 is 4
		float testGPA = 4;
		//the number of taken classes is 1+0+1+2+1 = 5
		int testTClasses = 5;
		//the total number of classses = 16 + every fail
		GPA base = new GPA(testList, 16);
		int testClasses = 17;
		base.calculatePointsNeeded(6);
		//input wanted gpa is 6, so output should be the same
		float wantedTest = 6;
		//points needed = 17*6 - 4*5
		float neededTest = 82;
		assertTrue("cgpa incorrect, cgpa equals : " + base.cgpa + ", should equal " + testGPA, base.cgpa == testGPA);
		assertTrue("taken courses incorrect, taken courses equals : " + base.numOfTCourses + ", should equal "+ testTClasses, base.cgpa == testTClasses);
		assertTrue("course number incorrect, course number equals : " + base.numOfCourses + ", should equal "+testClasses, base.numOfCourses == testClasses);
		assertTrue("wanted gpa incorrect, wanted gpa equals : " + base.gpaWanted + ", should equal "+wantedTest, base.gpaWanted == wantedTest);
		assertTrue("points needed incorrect, points needed equals : " + base.pointsNeeded + ", should equal "+neededTest, base.pointsNeeded == neededTest);
	}

}
