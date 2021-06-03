package com.dicoding.eigacatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    private val movieDummy = DummyEntries.getMovieDummy()
    private val tvShowDummy = DummyEntries.getTVShowDummy()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResource.idlingResource)
    }

    @After
    fun destroy() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idlingResource)
    }

    @Test
    fun loadMovieFragment() {
        Espresso.onView(withId(R.id.fragment_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.fragment_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieDummy.size))
    }

    @Test
    fun loadMovieDetailActivity() {
        Espresso.onView(withId(R.id.fragment_movie))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.sv_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.sv_detail)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.iv_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.textView_cinema_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_release))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_score))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_synopsis))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadTVShowFragment() {
        Espresso.onView(withText("TV Show")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment_tvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.fragment_tvshow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShowDummy.size))
    }

    @Test
    fun loadTVShowDetailActivity() {
        Espresso.onView(withText("TV Show")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.fragment_tvshow))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(9, ViewActions.click()))
        Espresso.onView(withId(R.id.sv_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.sv_detail)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.iv_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.textView_cinema_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_genre))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_score))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_synopsis))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_creator))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}