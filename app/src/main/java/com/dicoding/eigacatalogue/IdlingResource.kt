package com.dicoding.eigacatalogue

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun dec() = idlingResource.decrement()
    fun inc() = idlingResource.increment()
}