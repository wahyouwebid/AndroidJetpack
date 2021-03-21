package web.id.wahyou.jetpackapp.ui.main.tvshow

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import web.id.wahyou.jetpackapp.R
import web.id.wahyou.jetpackapp.ui.main.MainActivity
import web.id.wahyou.jetpackapp.utils.EspressoIdlingResource

class TvShowFragmentTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
        Espresso.onView(Matchers.allOf(withId(R.id.navigation_tvshow))).perform(ViewActions.click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        Espresso.onView(withId(R.id.rvTvShow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))

    }

    @Test
    fun detailTvShow() {
        Espresso.onView(withId(R.id.rvTvShow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(withId(R.id.rvTvShow)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                    ViewActions.click()
                ))

        Espresso.onView(withId(R.id.imgBackground)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.imgPoster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tvDescription)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }
}