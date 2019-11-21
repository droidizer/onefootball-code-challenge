package com.onefootball.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onefootball.R
import com.onefootball.ui.adapter.NewsAdapter
import com.onefootball.ui.util.loadingstate.LoadingState
import com.onefootball.ui.viewmodel.NewsViewModel
import com.onefootball.ui.viewmodel.NewsViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    private val newsRecyclerView by lazy {
        findViewById<RecyclerView>(R.id.newsRecyclerView)
    }

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(mutableListOf())
    }

    private val newsViewModel: NewsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
    }

    private val itemDecoration by lazy {
        val margin: Int = resources.getDimension(R.dimen.margin_4).toInt()
        val lateralMargin: Int = resources.getDimension(R.dimen.margin_8).toInt()

        object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.bottom = margin
                outRect.top = margin
                outRect.left = lateralMargin
                outRect.right = lateralMargin
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(newsRecyclerView) {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }
        newsRecyclerView.addItemDecoration(itemDecoration)
        newsViewModel.loadNews()
    }

    override fun onStart() {
        super.onStart()
        newsViewModel.subscribeToNews().observe(this, Observer {
            newsAdapter.setNewsItems(it)
        })

        newsViewModel.subscribeToErrorState().observe(this, Observer {
            if (LoadingState.ERROR_STATE.error == it.error) {
                newsRecyclerView.visibility = View.GONE
                errorLayout.visibility = View.VISIBLE
            }
        })
    }
}

