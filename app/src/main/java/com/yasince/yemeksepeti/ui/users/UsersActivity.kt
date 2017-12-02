package com.yasince.yemeksepeti.ui.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.yasince.yemeksepeti.App
import com.yasince.yemeksepeti.R
import com.yasince.yemeksepeti.data.network.model.User
import com.yasince.yemeksepeti.ui.adapter.UserAdapter
import com.yasince.yemeksepeti.ui.adapter.UserItemListener
import com.yasince.yemeksepeti.ui.base.BaseActivity
import com.yasince.yemeksepeti.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : BaseActivity(), UsersMvpView, UserItemListener {

    private lateinit var adapter: UserAdapter
    private var users: ArrayList<User> = ArrayList()
    private var presenter: UsersPresenter<UsersMvpView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        initToolbar(false)
        initPresenter()
        initUI()
        presenter?.getUsers()
    }

    override fun initUI() {
        adapter = UserAdapter(users, this)
        usersContainerRv.adapter = adapter
        val lm = LinearLayoutManager(this)
        val decor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        usersContainerRv.addItemDecoration(decor)
        usersContainerRv.layoutManager = lm
    }

    override fun initPresenter() {
        val dataManager = (application as App).dataManager
        presenter = UsersPresenter(dataManager)
        presenter?.onAttach(this)
    }

    override fun onItemClick(userId: String) {
        openUserProfilePage(userId)
    }

    override fun loadUsers(users: ArrayList<User>) {
        this.users.clear()
        this.users.addAll(users)
        adapter.notifyDataSetChanged()
    }

    override fun openUserProfilePage(userId: String) {
        startActivity(ProfileActivity.newIntent(this, userId))
    }

    override fun showUsersError(error: String) {
        showServiceError(error)
    }

    override fun noUsersFound() {
        showMessage(getString(R.string.no_users_found))
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, UsersActivity::class.java)
        }
    }
}
