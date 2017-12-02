package com.yasince.yemeksepeti.ui.login

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.yasince.yemeksepeti.App
import com.yasince.yemeksepeti.R
import com.yasince.yemeksepeti.ui.base.BaseActivity
import com.yasince.yemeksepeti.ui.users.UsersActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginMvpView {

    private var presenter: LoginPresenter<LoginMvpView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initPresenter()
        initUI()
    }

    override fun initPresenter() {
        val dataManager = (application as App).dataManager
        presenter = LoginPresenter(dataManager)
        presenter?.onAttach(this)
    }

    override fun initUI() {
        loginSubmitBtn.setOnClickListener({ onLoginClick() })
        loginPasswordEt.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(view: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onLoginClick()
                }
                return true
            }
        })
    }

    override fun onLoginClick() {
        val username: String = loginUsernameEt.text.toString()
        val password: String = loginPasswordEt.text.toString()
        presenter?.login(username, password)
    }

    override fun openUsersPage() {
        startActivity(UsersActivity.newIntent(this))
    }

    override fun showUsernameError() {
        loginUsernameEt.error = getString(R.string.invalid_username)
    }

    override fun showPasswordError() {
        loginPasswordEt.error = getString(R.string.invalid_password)
    }

    override fun showLoginError(error: String) {
        showServiceError(error)
    }
}
