package com.babedev.kotlinmvp.ui.presenter

import com.babedev.kotlinmvp.domain.interactor.FindPosts
import com.babedev.kotlinmvp.domain.model.Post
import rx.Observer
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author BabeDev
 */
@Singleton
class MainPresenter
@Inject
constructor(private val mFindPosts: FindPosts) {

    lateinit var view: MainView

    fun onInit() {
        mFindPosts.execute(object : Observer<List<Post>> {
            override fun onNext(posts: List<Post>) {
                view.showPosts(posts)
            }

            override fun onCompleted() {
                Timber.d("Find posts completed")
            }

            override fun onError(e: Throwable?) {
                Timber.d("Find posts error ${e.toString()}")
            }

        })
    }

    interface MainView {
        fun showPosts(posts: List<Post>)
    }
}