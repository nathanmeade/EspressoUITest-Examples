package com.codingwithmitch.espressouitestexamples.util

import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.lang.Exception

/**
        option 1:
        this option is much more difficult to read and is more verbose
 **/
/*
class EspressoIdlingResourceRule : TestRule{
    override fun apply(base: Statement?, description: Description?): Statement {

        return IdlingResourceStatement(base)
    }

    class IdlingResourceStatement(private val base: Statement?): Statement(){

        private val idlingResource = EspressoIdlingResource.countingIdlingResource

        override fun evaluate() {

            IdlingRegistry.getInstance().register(idlingResource)
            try {
                base?.evaluate()
                    ?: throw Exception("Error evaluating test. Base statement is null.")
            }finally {
                IdlingRegistry.getInstance().unregister(idlingResource)
            }
        }

    }
}*/
// option 2: simplified:
class EspressoIdlingResourceRule: TestWatcher() {
    private val idlingResource = EspressoIdlingResource.countingIdlingResource

    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }
}
