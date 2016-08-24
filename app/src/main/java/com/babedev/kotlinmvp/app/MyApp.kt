package com.babedev.kotlinmvp.app

import android.app.Application
import com.babedev.kotlinmvp.BuildConfig
import timber.log.Timber

/**
 * @author BabeDev
 */
class MyApp : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initLogger()
        initAppComponent()
    }

    private fun initAppComponent() {
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}