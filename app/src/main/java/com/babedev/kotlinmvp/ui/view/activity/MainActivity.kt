package com.babedev.kotlinmvp.ui.view.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.babedev.kotlinmvp.R
import com.babedev.kotlinmvp.app.MyApp
import com.babedev.kotlinmvp.domain.model.Post
import com.babedev.kotlinmvp.ui.adapter.PostsAdapter
import com.babedev.kotlinmvp.ui.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * @author BabeDev
 */
class MainActivity : Activity(), MainPresenter.MainView {

    @Inject
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApp.component.inject(this)

        with(mPresenter) {
            view = this@MainActivity
            onInit()
        }
    }

    override fun showPosts(posts: List<Post>) {
        with(rv_posts) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostsAdapter(this@MainActivity, posts)
        }
    }
}