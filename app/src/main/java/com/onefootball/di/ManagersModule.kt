package com.onefootball.di

import com.onefootball.domain.DataSource
import com.onefootball.domain.IDataSource
import com.onefootball.ui.util.schedulers.ISchedulersProvider
import com.onefootball.ui.util.schedulers.SchedulersProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ManagersModule {
    @Binds
    abstract fun bindsDataSource(dataSource: DataSource): IDataSource

    @Binds
    abstract fun bindsSchedulers(schedulersProvider: SchedulersProvider): ISchedulersProvider
}