package com.pixelart.week6weekendwalmartchallenge;

import android.content.Intent;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import com.pixelart.week6weekendwalmartchallenge.ui.mainscreen.MainActivity;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {//7 items in json
    private static final String FILE_NAME = "success_response";


    private ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MockWebServer mockWebServer;

    @Before
    public void setup(){
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start();
            mockWebServer.enqueue(new MockResponse()
                    .setResponseCode(200)
                    .setBody(TestUtil.getStringFromFile(InstrumentationRegistry.getInstrumentation().getContext(), FILE_NAME))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ConstantsKt.setBASE_URL(mockWebServer.url("/").toString());
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testLoadingDataSuccess(){
        CountingIdlingResource countingIdlingResource = activityTestRule.getActivity()
                .getCountingIdlingResource();
        IdlingRegistry.getInstance().register(countingIdlingResource);

        //Local file
        /*Espresso.onView(ViewMatchers.withId(R.id.rvProducts)).check(
                new TestUtil.RecyclerViewItemCountAssertion(7));*/

        Espresso.onView(ViewMatchers.withId(R.id.rvProducts)).check(
                new TestUtil.RecyclerViewItemCountAssertion(1000));

        Espresso.onView(ViewMatchers.withId(R.id.rvProducts)).check(
                ViewAssertions.matches(TestUtil.atPosition(0,
                        ViewMatchers.hasDescendant(ViewMatchers.withText("The Ren & Stimpy Show: Space Cadet Adventures")))));

        Espresso.onView(ViewMatchers.withId(R.id.rvProducts)).perform(RecyclerViewActions
                .scrollToPosition(100));

        Espresso.onView(ViewMatchers.withId(R.id.rvProducts)).perform(RecyclerViewActions
                .actionOnItemAtPosition(80, ViewActions.click()));
    }

}
