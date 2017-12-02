package com.yasince.yemeksepeti.ui.profile

import com.yasince.yemeksepeti.data.DataManager
import com.yasince.yemeksepeti.ui.BaseTest
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProfilePresenterTest : BaseTest() {

    private val EMPTY: String = ""
    private val USER_ID: String = "123"

    private lateinit var presenter: ProfilePresenter<ProfileMvpView>

    @Mock
    private lateinit var view: ProfileMvpView

    @Mock
    private lateinit var dataManager: DataManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = ProfilePresenter(dataManager)
        presenter.onAttach(view)
    }

    @Test
    fun testOnAttach() {
        Assert.assertNotNull(presenter.mvpView)
    }

    @Test
    fun testGetUserProfileWithEmptyId() {
        presenter.getUserProfile(EMPTY)
        verify(presenter.mvpView)?.showUserIdError()
    }

    @Test
    fun testGetUserProfileWithValidId() {
        presenter.getUserProfile(USER_ID)
        verify(dataManager)?.getUserProfile(USER_ID, presenter.userProfileListener)
    }
}