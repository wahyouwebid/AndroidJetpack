package web.id.wahyou.jetpackapp.ui.main.favorite

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import web.id.wahyou.jetpackapp.R
import web.id.wahyou.jetpackapp.ui.main.MainActivity
import web.id.wahyou.jetpackapp.utils.EspressoIdlingResource

class FavoriteFragmentTest {
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun checkTab() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.pager))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pager))
                .perform(ViewActions.swipeLeft())
        Espresso.onView(ViewMatchers.withId(R.id.pager))
                .perform(ViewActions.swipeRight())
        Espresso.onView(ViewMatchers.withId(R.id.pager))
                .perform(ViewActions.swipeLeft())
        Espresso.onView(ViewMatchers.withId(R.id.pager))
                .perform(ViewActions.swipeRight())
    }

    @Test
    fun loadMovies() {

        //load movies
        Espresso.onView(ViewMatchers.withId(R.id.rvMovie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadTvShow() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.pager)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pager)).perform(ViewActions.swipeLeft())
        Espresso.onView(ViewMatchers.withId(R.id.rvTvShow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))

    }

    @Test
    fun loadAndInsertFavoriteMovies() {

        Espresso.onView(ViewMatchers.withId(R.id.rvMovie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite)).perform(ViewActions.click())
        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(R.id.navigation_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite)).perform(ViewActions.click())
        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(R.id.navigation_tvshow)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rvTvShow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.btnFavorite)).perform(ViewActions.click())
        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(R.id.navigation_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.pager)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pager)).perform(ViewActions.swipeLeft())
        Espresso.onView(ViewMatchers.withId(R.id.rvTvShow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.pressBack()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }
}