package com.cooolog.domain.interactor

import com.babedev.kotlinmvp.domain.interactor.FindPosts
import com.babedev.kotlinmvp.domain.interactor.FindPostsInteractor
import dagger.Module
import dagger.Provides

/**
 * @author BabeDev
 */
@Module
class InteractorModule {

    @Provides
    internal fun provideFindPostsInteractor(interactor: FindPostsInteractor): FindPosts {
        return interactor
    }

}