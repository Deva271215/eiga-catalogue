package com.dicoding.eigacatalogue

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test

class SplashActivityTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(SplashActivity::class.java)
    }

    @Test
    fun loadSplashActivity() {
        Espresso.onView(withId(R.id.splash_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}