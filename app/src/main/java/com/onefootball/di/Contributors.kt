package com.onefootball.di

import com.onefootball.ui.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface Contributors {

    @ContributesAndroidInjector
    fun injectMainActivity(): NewsActivity
}
