package com.example.ANU_GPA;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Context.MODE_PRIVATE;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void baseCase(){
        String[] testArray = {"0","0","0","0","0","0","0","0","0","0"};
        mainActivityTestCode(testArray);
        Context context = getInstrumentation().getTargetContext();
        SharedPreferences dataTest = context.getSharedPreferences("com.example.ANU_GPA.Data", MODE_PRIVATE);
        //all values are 0
        assertTrue("cgpa incorrect, cgpa equals : " + dataTest.getFloat("cgpa", -1) + ", should equal 0", dataTest.getFloat("cgpa", -1) == 0);
        assertTrue("currentPoints incorrect, currentPoints equals : " + dataTest.getInt("currentPoints", -1) + ", should equal 0", dataTest.getInt("currentPoints", -1) == 0);
        assertTrue("courses incorrect, courses equals : " + dataTest.getInt("numOfTCourses", -1) + ", should equal 0", dataTest.getInt("numOfTCourses", -1) == 0);
    }
    @Test
    public void test1(){
        String[] testArray = new String[10];
        for(int i=0;i<10;i++){
            testArray[i] = "1";
        }
        mainActivityTestCode(testArray);
        Context context = getInstrumentation().getTargetContext();
        SharedPreferences dataTest = context.getSharedPreferences("com.example.ANU_GPA.Data", MODE_PRIVATE);
        //class number is 10, points from grades is 44 and cgpa is 4.4
        assertTrue("cgpa incorrect, cgpa equals : " + dataTest.getFloat("cgpa", -1) + ", should equal 4.4", dataTest.getFloat("cgpa", -1) == (float)4.4);
        assertTrue("currentPoints incorrect, currentPoints equals : " + dataTest.getInt("currentPoints", -1) + ", should equal 44", dataTest.getInt("currentPoints", -1) == 44);
        assertTrue("courses incorrect, courses equals : " + dataTest.getInt("numOfTCourses", -1) + ", should equal 10", dataTest.getInt("numOfTCourses", -1) == 10);
    }
    @Test
    public void randTest(){
        String[] testArray = new String[10];
        int[] intArray = new int[10];
        for(int i=0;i<10;i++){
            int temp = (int)(Math.random()*3+1);
            testArray[i] = Integer.toString(temp);
            intArray[i] = temp;
        }
        int currentPoints = 0;
        int classNumber = 0;
        float cgpa = 0;
        int [] gradeValues = {7,6,5,4,0};
        for(int i = 0; i <5; i++){
            currentPoints = currentPoints+(intArray[i]+ intArray[i+5])*gradeValues[i];
            classNumber = classNumber + intArray[i]+ intArray[i+5];
        }
        cgpa = (float) currentPoints/classNumber;
        mainActivityTestCode(testArray);
        Context context = getInstrumentation().getTargetContext();
        SharedPreferences dataTest = context.getSharedPreferences("com.example.ANU_GPA.Data", MODE_PRIVATE);
        // cgpa, classNumber and currentPoints are test values
        assertTrue("cgpa incorrect, cgpa equals : " + dataTest.getFloat("cgpa", -1) + ", should equal "+ cgpa, dataTest.getFloat("cgpa", -1) == cgpa);
        assertTrue("currentPoints incorrect, currentPoints equals : " + dataTest.getInt("currentPoints", -1) + ", should equal "+ currentPoints, dataTest.getInt("currentPoints", -1) == currentPoints);
        assertTrue("courses incorrect, courses equals : " + dataTest.getInt("numOfTCourses", -1) + ", should equal " + classNumber, dataTest.getInt("numOfTCourses", -1) == classNumber);
    }

    //method of each test
    public void mainActivityTestCode(String[] stringList) {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.linearLayout1),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                1),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.hdEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                1)));
        appCompatEditText.perform(scrollTo(), replaceText(stringList[0]), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.dEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                3)));
        appCompatEditText2.perform(scrollTo(), replaceText(stringList[1]), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.cEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                5)));
        appCompatEditText3.perform(scrollTo(), replaceText(stringList[2]), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.pEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                7)));
        appCompatEditText4.perform(scrollTo(), replaceText(stringList[3]), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.fEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                9)));
        appCompatEditText10.perform(scrollTo(), replaceText(stringList[4]), closeSoftKeyboard());


        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.doneButton), withText("Done"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                10)));
        appCompatButton.perform(scrollTo(), click());

        pressBack();

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.linearLayout3),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                3),
                        isDisplayed()));
        linearLayout2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.linearLayout3),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                3),
                        isDisplayed()));
        linearLayout3.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.hdEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                1)));
        appCompatEditText5.perform(scrollTo(), replaceText(stringList[5]), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.dEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                3)));
        appCompatEditText6.perform(scrollTo(), replaceText(stringList[6]), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.cEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                5)));
        appCompatEditText7.perform(scrollTo(), replaceText(stringList[7]), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.pEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                7)));
        appCompatEditText8.perform(scrollTo(), replaceText(stringList[8]), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.fEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                9)));
        appCompatEditText9.perform(scrollTo(), replaceText(stringList[9]), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.doneButton), withText("Done"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                10)));
        appCompatButton2.perform(scrollTo(), click());

        pressBack();

        ViewInteraction linearLayout4 = onView(
                allOf(withId(R.id.linearLayout1),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        0),
                                1),
                        isDisplayed()));
        linearLayout4.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
