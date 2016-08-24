package com.babedev.kotlinmvp.app

import com.babedev.kotlinmvp.ui.view.activity.MainActivity
import com.cooolog.domain.interactor.InteractorModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author BabeDev
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, InteractorModule::class))
interface AppComponent {

    fun inject(activity: MainActivity): MainActivity

}