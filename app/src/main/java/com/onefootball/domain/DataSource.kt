package com.onefootball.domain

import android.content.res.Resources
import com.google.gson.Gson
import com.onefootball.R
import com.onefootball.model.News
import com.onefootball.model.NewsModel
import io.reactivex.Single
import javax.inject.Inject

class DataSource
@Inject constructor(private val resources: Resources, private val gson: Gson) : IDataSource {

    override fun getNews(): Single<List<News>> {
        return Single.fromCallable {
            val jsonString = resources.openRawResource(R.raw.news)
                .bufferedReader().use { it.readText() }
            val newsItems = mutableListOf<News>()

            val newsModel = gson.fromJson(jsonString, NewsModel::class.java)
            newsItems.addAll(newsModel.news)
            newsItems
        }
    }
}
