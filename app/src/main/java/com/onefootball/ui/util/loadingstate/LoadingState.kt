package com.onefootball.ui.util.loadingstate

import com.onefootball.model.News

import java.util.ArrayList

class LoadingState private constructor(data: List<News>?, currentState: Int, error: Throwable?) :
    BaseViewState<List<News>>() {
    init {
        this.data = data
        this.error = error
        this.currentState = currentState
    }

    companion object {
        var ERROR_STATE = LoadingState(null, BaseViewState.State.FAILED.value, Throwable())
        var SUCCESS_STATE = LoadingState(ArrayList(), BaseViewState.State.SUCCESS.value, null)
    }
}
