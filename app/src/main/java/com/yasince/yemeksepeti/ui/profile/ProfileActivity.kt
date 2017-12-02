package com.yasince.yemeksepeti.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.yasince.yemeksepeti.App
import com.yasince.yemeksepeti.R
import com.yasince.yemeksepeti.data.network.model.Profile
import com.yasince.yemeksepeti.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity(), ProfileMvpView {

    private var presenter: ProfilePresenter<ProfileMvpView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initToolbar(true)
        initPresenter()
        initUI()
        val userId = intent.getStringExtra(PARAM_USER_ID)
        presenter?.getUserProfile(userId!!)
    }

    override fun initUI() {
    }

    override fun initPresenter() {
        val dataManager = (application as App).dataManager
        presenter = ProfilePresenter(dataManager)
        presenter?.onAttach(this)
    }

    override fun onBackClick() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun getUserProfile(userId: String) {
        presenter?.getUserProfile(userId)
    }

    override fun showUsersError(error: String) {
        showUsersError(error)
    }

    override fun showUserIdError() {
        showMessage(getString(R.string.invalid_user_id))
    }

    override fun loadProfile(profile: Profile) {
        profileNameTv.text = profile.fullName()
        profileBirthDateTv.text = profile.birthDate
        profileAddressEt.setText(profile.address)
        profilePhoneEt.setText(profile.phone)
        Picasso.with(this).load(profile.pic).into(profilePicIv)
    }

    companion object {
        val PARAM_USER_ID: String = "USER ID"

        fun newIntent(context: Context, userId: String): Intent {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra(PARAM_USER_ID, userId)
            return intent
        }
    }
}
