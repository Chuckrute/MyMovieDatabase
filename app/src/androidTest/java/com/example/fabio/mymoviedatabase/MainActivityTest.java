package com.example.fabio.mymoviedatabase;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fabio.mymoviedatabase.ui.main.MainActivity;
import com.example.fabio.mymoviedatabase.ui.main.MoviesRecyclerViewAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

/**
 * Created by EUROCOM on 05/09/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    IdlingResource mIdlingResource;

    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
     *
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */


    public class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final int expectedCount;

        public RecyclerViewItemCountAssertion(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }


    @Test
    public void searchForName() throws Exception {
        String movieName = "Harry";

        onView(withId(R.id.etMovieName)).perform(click());

        onView(withId(R.id.etMovieName)).perform(typeText(movieName), closeSoftKeyboard());

        onView(withId(R.id.rvMovies)).check(matches(hasSibling(withText(movieName))));
    }


    @Test
    public void scrollView() throws Exception {
        MoviesRecyclerViewAdapter adapter = (MoviesRecyclerViewAdapter) mActivityTestRule.getActivity().rvMovies.getAdapter();
        onView(withId(R.id.rvMovies)).check(new RecyclerViewItemCountAssertion(20));
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.scrollToPosition(adapter.getItemCount()-1));
        onView(withId(R.id.rvMovies)).check(new RecyclerViewItemCountAssertion(40));
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.scrollToPosition(adapter.getItemCount()-1));
        onView(withId(R.id.rvMovies)).check(new RecyclerViewItemCountAssertion(60));
    }


    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
