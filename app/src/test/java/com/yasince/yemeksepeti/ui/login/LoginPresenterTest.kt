package com.yasince.yemeksepeti.ui.login

import com.yasince.yemeksepeti.data.DataManager
import com.yasince.yemeksepeti.ui.BaseTest
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest : BaseTest() {

    private val EMPTY = ""
    private val INVALID_PASSWORD = "1"
    private val VALID_PASSWORD = "1232113"
    private val INVALID_USERNAME = "L"
    private val VALID_USERNAME = "LOREM"

    lateinit var presenter: LoginPresenter<LoginMvpView>

    @Mock
    lateinit var view: LoginMvpView

    @Mock
    lateinit var dataManager: DataManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = LoginPresenter(dataManager)
        presenter.onAttach(view)
    }

    @Test
    fun testOnAttach() {
        assertNotNull(presenter.mvpView)
    }

    @Test
    fun testLoginEmptyNameEmptyPassword() {
        presenter.login(EMPTY, EMPTY)
        verify(presenter.mvpView)?.showUsernameError()
    }

    @Test
    fun testLoginInvalidNameValidPassword() {
        presenter.login(INVALID_USERNAME, VALID_PASSWORD)
        verify(presenter.mvpView)?.showUsernameError()
    }

    @Test
    fun testLoginValidNameInvalidPassword() {
        presenter.login(VALID_USERNAME, INVALID_PASSWORD)
        verify(presenter.mvpView)?.showPasswordError()
    }
//
//    @Test
//    fun testLoginValidNameValidPassword() {
//        presenter.login(VALID_USERNAME, VALID_PASSWORD)
//        sleepLong()
////        verify(presenter.mvpView)?.openUsersPage()
//    }

    @Test
    fun testValidatePassword() {
        assertNotNull(presenter)
        assertFalse(presenter.validatePassword(EMPTY))
        assertFalse(presenter.validatePassword(INVALID_PASSWORD))
        assertTrue(presenter.validatePassword(VALID_PASSWORD))
    }

    @Test
    fun testValidateUsername() {
        assertNotNull(presenter)
        assertFalse(presenter.validateUsername(EMPTY))
        assertFalse(presenter.validateUsername(INVALID_USERNAME))
        assertTrue(presenter.validateUsername(VALID_USERNAME))
    }

}