package com.nitinm.jet2articles.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nitinm.jet2articles.R
import com.nitinm.jet2articles.util.Constants
import com.nitinm.jet2articles.util.showToast
import com.nitinm.jet2articles.view.viewModel.ArticlesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var articlesViewModel: ArticlesViewModel
    var articlesAdapter: ArticlesAdapter = ArticlesAdapter(arrayListOf())
    var pageNumber: Int = 1
    var isLoading: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        articlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel::class.java)
        setUpRecyclerView()
        observeViewModel()
        articlesViewModel.CheckDbCountAndCallArticleList(pageNumber)
    }

    private fun setUpRecyclerView() {

        articlesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articlesAdapter

            this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    val totalItemCount = this@apply.layoutManager?.itemCount ?: 0
                    val lastVisibleItemPosition =
                        (this@apply.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                    if (!isLoading && pageNumber != Constants.MAX_PAGES
                        && totalItemCount == lastVisibleItemPosition + 1
                    ) {
                        articlesViewModel.CheckDbCountAndCallArticleList(pageNumber)
                    } else if (pageNumber == Constants.MAX_PAGES && totalItemCount == lastVisibleItemPosition + 1)
                        this@MainActivity.showToast(context.getString(R.string.message_all_caught_up))
                }
            })
        }
    }

    private fun observeViewModel() {

        articlesViewModel.articlesLoadingLiveData.observe(this, Observer { isLoading ->
            this.isLoading = isLoading
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        articlesViewModel.articlesListLiveData.observe(this, Observer { articlesList ->
            articlesAdapter.articleList = articlesList
            pageNumber++
        })

        articlesViewModel.articlesLoadErrorLiveData.observe(this, Observer { isError ->
            if (isError)
                this.showToast(getString(R.string.api_error))
        })
    }
}
