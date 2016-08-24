package com.babedev.kotlinmvp.domain.interactor

import com.babedev.kotlinmvp.domain.api.PostsApi
import com.babedev.kotlinmvp.domain.model.Post
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author BabeDev
 */
interface FindPosts {

    fun execute(observer: Observer<List<Post>>)

}

class FindPostsInteractor
@Inject
constructor(private var api: PostsApi): FindPosts {

    override fun execute(observer: Observer<List<Post>>) {
        api.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}
