package com.onefootball.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onefootball.domain.IDataSource
import com.onefootball.ui.util.schedulers.ISchedulersProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory @Inject constructor(

    private val dataSource: IDataSource, private val schedulersProvider: ISchedulersProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        NewsViewModel(dataSource, schedulersProvider)
                as T
}
