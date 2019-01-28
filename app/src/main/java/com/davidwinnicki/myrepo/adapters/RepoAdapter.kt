package com.davidwinnicki.myrepo.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.davidwinnicki.myrepo.R
import com.davidwinnicki.myrepo.model.Repo
import kotlinx.android.synthetic.main.repo_list_row.view.*

class RepoAdapter(
    private val reposList: MutableList<Repo>,
    private val clickListener: OnItemClickListener?
) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.repo_list_row, parent, false)
        return RepoViewHolder(itemView)
    }

    override fun getItemCount() = reposList.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(reposList[position], clickListener)

        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(Color.WHITE)
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFAF8FD"))
        }
    }

    fun setData(repos: List<Repo>) {
        reposList.addAll(repos)
        notifyDataSetChanged()
    }

    class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.textViewName
        private val description: TextView = view.textViewDescription

        fun bind(item: Repo, clickListener: OnItemClickListener?) {
            name.text = item.name
            description.text = item.description

            itemView.setOnClickListener {
                clickListener?.onItemClick(item)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Repo)
    }
}
