package com.yasince.yemeksepeti.ui.users

import com.yasince.yemeksepeti.data.DataManager
import com.yasince.yemeksepeti.ui.BaseTest
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersPresenterTest : BaseTest() {

    private lateinit var presenter: UsersPresenter<UsersMvpView>

    @Mock
    private lateinit var view: UsersMvpView

    @Mock
    private lateinit var dataManager: DataManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = UsersPresenter(dataManager)
        presenter.onAttach(view)
    }

    @Test
    fun testOnAttach() {
        Assert.assertNotNull(presenter.mvpView)
    }

    @Test
    fun testGetUsers() {
        presenter.getUsers()
        Mockito.verify(dataManager)?.getUsers(presenter.userListener)
    }
}