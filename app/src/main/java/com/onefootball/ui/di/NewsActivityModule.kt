package com.onefootball.ui.di

import com.onefootball.domain.IDataSource
import com.onefootball.ui.util.schedulers.ISchedulersProvider
import com.onefootball.ui.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class NewsActivityModule {

    @Provides
    fun providesFactory(dataSource: IDataSource, schedulersProvider: ISchedulersProvider) =
        NewsViewModelFactory(dataSource, schedulersProvider)
}
