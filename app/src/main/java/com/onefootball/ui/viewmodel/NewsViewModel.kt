package com.onefootball.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onefootball.domain.IDataSource
import com.onefootball.model.News
import com.onefootball.ui.util.loadingstate.LoadingState
import com.onefootball.ui.util.schedulers.ISchedulersProvider
import io.reactivex.disposables.Disposables

class NewsViewModel constructor(
    private val dataSource: IDataSource,
    private val schedulerProvider: ISchedulersProvider
) : ViewModel() {

    var itemsLiveData = MutableLiveData<List<News>>()
    var errorStateLiveData = MutableLiveData<LoadingState>()
    private val items: MutableList<News> = mutableListOf()
    private var itemsDisposable = Disposables.disposed()

    fun loadNews() {
        if (itemsDisposable.isDisposed) {
            itemsDisposable = dataSource.getNews()
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getUIScheduler())
                .subscribe({
                    items.addAll(it)
                    LoadingState.SUCCESS_STATE.data = it as MutableList<News>?
                    itemsLiveData.postValue(LoadingState.SUCCESS_STATE.data)
                }, ::handleError)
        }
    }

    fun subscribeToNews(): LiveData<List<News>> {
        return itemsLiveData
    }

    fun subscribeToErrorState(): LiveData<LoadingState> {
        return errorStateLiveData
    }

    private fun handleError(it: Throwable?) {
        LoadingState.ERROR_STATE.error = it
        errorStateLiveData.postValue(LoadingState.ERROR_STATE)
    }

    override fun onCleared() {
        itemsDisposable.dispose()
        super.onCleared()
    }
}
