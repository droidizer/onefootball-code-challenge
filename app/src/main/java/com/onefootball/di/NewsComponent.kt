package com.onefootball.di

import com.onefootball.NewsApplication
import com.onefootball.ui.di.NewsActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        Contributors::class,
        ManagersModule::class,
        NewsActivityModule::class,
        HelpersModule::class
    )
)

interface NewsComponent : AndroidInjector<NewsApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: NewsApplication): Builder

        fun build(): NewsComponent
    }
}