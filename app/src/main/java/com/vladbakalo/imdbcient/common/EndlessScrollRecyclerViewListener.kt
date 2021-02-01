package com.vladbakalo.imdbcient.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollRecyclerViewListener(private val linearLayoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var visibleThreshold = 10
    private var currentPage = 1
    private var previousTotalItemCount = 0
    private var loadingState = false
    private var startPageIndex = 1
    private var totalItemCount = 0
    private var lastVisibleItemPosition = 0

    fun reset(){
        currentPage = startPageIndex
        previousTotalItemCount = 0
        loadingState = true
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        totalItemCount = linearLayoutManager.itemCount
        lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()

        //Case 1
        if (totalItemCount < previousTotalItemCount){
            currentPage = startPageIndex
            previousTotalItemCount = totalItemCount

            if (totalItemCount == 0) {
                loadingState = true
            }
        }

        //Case 2
        if (loadingState && (previousTotalItemCount < (totalItemCount - 1))) {
            loadingState = false
            previousTotalItemCount = totalItemCount
        }

        //Case 3
        if (!loadingState && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++
            previousTotalItemCount = totalItemCount

            onLoadMore(currentPage, recyclerView)
            loadingState = true
        }
    }

    abstract fun onLoadMore(page: Int, recycler: RecyclerView)

    companion object{
        private const val TAG = "EndlessScrollListener"
    }
}