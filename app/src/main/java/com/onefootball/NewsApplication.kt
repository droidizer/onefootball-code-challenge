package com.onefootball

import com.onefootball.di.DaggerNewsComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NewsApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerNewsComponent.builder().application(this).build()
    }
}