package com.babedev.kotlinmvp.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babedev.kotlinmvp.R
import com.babedev.kotlinmvp.domain.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

/**
 * @author BabeDev
 */
class PostsAdapter(context: Context, posts: List<Post>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    lateinit var mContext: Context
    lateinit var mPosts: List<Post>

    init {
        mContext = context
        mPosts = posts
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_post, parent, false))
    }

    override fun onBindViewHolder(holder: PostsAdapter.ViewHolder, position: Int) {
        with(mPosts[position]) {
            holder.user.text = user
            holder.detail.text = detail
        }
    }

    override fun getItemCount(): Int {
        return mPosts.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val user = view.user!!
        val detail = view.detail!!
    }
}