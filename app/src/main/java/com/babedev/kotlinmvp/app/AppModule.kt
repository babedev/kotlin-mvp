package com.babedev.kotlinmvp.app

import android.app.Application
import com.babedev.kotlinmvp.domain.api.PostsApi
import com.babedev.kotlinmvp.util.NetworkHelper
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.IOException
import javax.inject.Singleton

/**
 * @author BabeDev
 */
@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://kotlinmvp.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createHttpClient()).build()
    }

    @Provides
    fun providePostsApi(retrofit: Retrofit): PostsApi {
        return retrofit.create<PostsApi>(PostsApi::class.java)
    }

    private fun createHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            message -> Timber.tag("OkHttp").d(message)
        })

        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.cache(Cache(app.cacheDir, 2 * 1024 * 1024.toLong()))
                .addInterceptor(CacheInterceptor())
                .addInterceptor(logging)

        return httpClient.build()
    }

    private inner class CacheInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()

            if (NetworkHelper.isNetworkAvailable(app)) {
                request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
            } else {
                request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
            }

            return chain.proceed(request)
        }
    }
}