package com.onefootball.domain

import com.onefootball.model.News
import io.reactivex.Single

interface IDataSource {
    fun getNews(): Single<List<News>>
}
