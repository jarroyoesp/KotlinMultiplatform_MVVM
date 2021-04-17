package com.jarroyo.kmp_mvvm.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jarroyo.kmp_mvvm.R
import com.jarroyo.sharedcode.db.User
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import kotlinx.android.synthetic.main.item_rv_github_repo_list.view.*

class UserRVAdapter(
    private var mList: List<User>? = listOf<User>()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_github_repo_list, parent, false)
        return UserViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as UserViewHolder
        viewHolder.bind(
            position,
            mList?.get(position)
        )
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            position: Int,
            user: User?
        ) = with(itemView) {
            item_rv_github_repo_list_tv.text = user?.name
        }
    }

    fun setList(list: List<User>) {
        mList = list
    }

    data class Item(val position: Int, val gitHubRepo: GitHubRepo)
}

