package com.yasince.yemeksepeti

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.yasince.yemeksepeti.ui.adapter.UserAdapter
import com.yasince.yemeksepeti.ui.login.LoginActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppFullFlowTest : BaseTest() {

    @get:Rule
    private val activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    private lateinit var usernameError: String
    private lateinit var passwordError: String

    @Before
    fun setUp() {
        usernameError = activityRule.activity.getString(R.string.invalid_username)
        passwordError = activityRule.activity.getString(R.string.invalid_password)
    }


    @Test
    fun testHappyPath() {

        //LOGIN ACTIVITY

        onView(withId(R.id.loginUsernameEt)).check(matches(isDisplayed()))
        onView(withId(R.id.loginPasswordEt)).check(matches(isDisplayed()))
        onView(withId(R.id.loginSubmitBtn)).check(matches(isDisplayed()))

        onView(withId(R.id.loginUsernameEt)).perform(typeText(NAME), closeSoftKeyboard())
        onView(withId(R.id.loginPasswordEt)).perform(typeText(PASSWORD), closeSoftKeyboard())
        onView(withId(R.id.loginSubmitBtn)).perform(click())

        sleepShort()

        //USERS ACTIVITY

        onView(withId(R.id.usersContainerRv)).check(matches(isDisplayed()))

        onView(ViewMatchers.withId(R.id.usersContainerRv))
                .perform(RecyclerViewActions.
                        actionOnItemAtPosition<UserAdapter.UserViewHolder>(USER_INDEX, click()))

        sleepShort()

        //PROFILE ACTIVITY

        onView(withId(R.id.profilePicIv)).check(matches(isDisplayed()))
        onView(withId(R.id.profileNameTv)).check(matches(isDisplayed()))
        onView(withId(R.id.profileBirthDateTv)).check(matches(isDisplayed()))
        onView(withId(R.id.profilePhoneEt)).check(matches(isDisplayed()))
        onView(withId(R.id.profileAddressEt)).check(matches(isDisplayed()))

        onView(withId(R.id.profilePhoneEt)).perform(clearText())
        onView(withId(R.id.profilePhoneEt)).perform(typeText(PHONE), closeSoftKeyboard())
        onView(withId(R.id.profilePhoneEt)).check(matches(withText(PHONE)))

        onView(withId(R.id.profileAddressEt)).perform(clearText())
        onView(withId(R.id.profileAddressEt)).perform(typeText(ADDRESS), closeSoftKeyboard())
        onView(withId(R.id.profileAddressEt)).check(matches(withText(ADDRESS)))

        onView(withContentDescription("Navigate up")).perform(click())

        sleepShort()

        //USERS ACTIVITY

        onView(withId(R.id.usersContainerRv)).check(matches(isDisplayed()))

    }

    @Test
    fun testErrorPath() {

        //check empty name and password
        onView(withId(R.id.loginSubmitBtn)).perform(click())
        onView(withId(R.id.loginUsernameEt)).check(matches(hasErrorText(usernameError)))
        sleepShort()

        //check invalid name
        onView(withId(R.id.loginUsernameEt)).perform(typeText(INVALID_NAME), closeSoftKeyboard())
        onView(withId(R.id.loginSubmitBtn)).perform(click())
        onView(withId(R.id.loginUsernameEt)).check(matches(hasErrorText(usernameError)))
        onView(withId(R.id.loginUsernameEt)).perform(clearText())
        sleepShort()

        //check invalid password
        onView(withId(R.id.loginUsernameEt)).perform(typeText(NAME), closeSoftKeyboard())
        onView(withId(R.id.loginPasswordEt)).perform(typeText(INVALID_PASSWORD), closeSoftKeyboard())
        onView(withId(R.id.loginSubmitBtn)).perform(click())
        onView(withId(R.id.loginPasswordEt)).check(matches(hasErrorText(passwordError)))
        onView(withId(R.id.loginUsernameEt)).perform(clearText())
        onView(withId(R.id.loginPasswordEt)).perform(clearText())
        sleepShort()

        //check invalid name and password
        onView(withId(R.id.loginUsernameEt)).perform(typeText(INVALID_NAME), closeSoftKeyboard())
        onView(withId(R.id.loginPasswordEt)).perform(typeText(INVALID_PASSWORD), closeSoftKeyboard())
        onView(withId(R.id.loginSubmitBtn)).perform(click())
        onView(withId(R.id.loginUsernameEt)).check(matches(hasErrorText(usernameError)))
    }

    companion object {
        val NAME: String = "Lorem"
        val INVALID_NAME: String = "L"
        val PASSWORD: String = "123456789"
        val INVALID_PASSWORD: String = "1"
        val PHONE: String = "123456789"
        val ADDRESS: String = "Lorem"
        val USER_INDEX: Int = 5
    }
}
