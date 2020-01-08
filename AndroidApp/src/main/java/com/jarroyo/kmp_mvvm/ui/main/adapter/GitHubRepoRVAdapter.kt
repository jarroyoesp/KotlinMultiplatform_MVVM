package com.jarroyo.kmp_mvvm.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jarroyo.kmp_mvvm.R
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import kotlinx.android.synthetic.main.item_rv_github_repo_list.view.*

class GitHubRepoRVAdapter(
    private var mList: List<GitHubRepo>? = listOf<GitHubRepo>()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_github_repo_list, parent, false)
        return GitHubViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as GitHubViewHolder
        viewHolder.bind(
            position,
            mList?.get(position)
        )
    }

    class GitHubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            position: Int,
            gitHubRepo: GitHubRepo?
        ) = with(itemView) {
            item_rv_github_repo_list_tv.text = gitHubRepo?.name
        }
    }

    fun setList(list: List<GitHubRepo>) {
        mList = list
    }

    data class Item(val position: Int, val gitHubRepo: GitHubRepo)
}

