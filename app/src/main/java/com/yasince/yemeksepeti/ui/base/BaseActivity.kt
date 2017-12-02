package com.yasince.yemeksepeti.ui.base

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast

import com.yasince.yemeksepeti.R
import kotlinx.android.synthetic.main.appbar.*

open class BaseActivity : AppCompatActivity(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun initToolbar(showBack: Boolean) {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(showBack)
            supportActionBar?.setDisplayShowHomeEnabled(showBack)
            toolbar.setTitleTextColor(Color.WHITE)
        }
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    override fun onStart() {
        super.onStart()
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showServiceError(error: String) {
        if (TextUtils.isEmpty(error)) {
            showMessage(getString(R.string.service_error))
        } else {
            showMessage(error)
        }
    }
}
