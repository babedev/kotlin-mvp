package com.babedev.kotlinmvp.domain.api

import com.babedev.kotlinmvp.domain.model.Post
import retrofit2.http.GET
import rx.Observable

/**
 * @author BabeDev
 */
interface PostsApi {

    @GET("posts")
    fun getPosts(): Observable<List<Post>>

}