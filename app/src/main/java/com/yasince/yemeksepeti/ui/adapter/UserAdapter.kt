package com.yasince.yemeksepeti.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.yasince.yemeksepeti.R
import com.yasince.yemeksepeti.data.network.model.User
import com.yasince.yemeksepeti.utils.inflate
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val users: ArrayList<User>, private val listener: UserItemListener) :
        RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bindUser(user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflatedView = parent.inflate(R.layout.item_user, false)
        return UserViewHolder(inflatedView, listener)
    }

    override fun getItemCount() = users.size

    class UserViewHolder(v: View, private val listener: UserItemListener) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var user: User? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            listener.onItemClick(v.tag.toString())
        }

        fun bindUser(user: User) {
            this.user = user
            view.tag = user.id
            view.userItemNameTv.text = user.fullName()
            Picasso.with(view.userItemPicIv.context).load(user.pic).into(view.userItemPicIv)
        }
    }
}