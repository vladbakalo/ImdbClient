package com.vladbakalo.imdbcient.ui.movie_list

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vladbakalo.imdbcient.R
import com.vladbakalo.imdbcient.base.BaseFragment
import com.vladbakalo.imdbcient.common.EndlessScrollRecyclerViewListener
import com.vladbakalo.imdbcient.common.injectViewModel
import com.vladbakalo.imdbcient.data.model.Movie
import com.vladbakalo.imdbcient.databinding.MovieListFragmentBinding
import com.vladbakalo.imdbcient.di.ViewModelFactory
import com.vladbakalo.imdbcient.ui.movie_detailed.MovieDetailedFragment
import com.vladbakalo.imdbcient.ui.movie_list.adapter.MovieListAdapter
import javax.inject.Inject

class MovieListFragment : BaseFragment<MovieListViewModel>(), MovieListAdapter.MovieClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: MovieListFragmentBinding
    private lateinit var searchView: SearchView

    private var movieListAdapter: MovieListAdapter? = null
    private var endlessScrollRecyclerViewListener: EndlessScrollRecyclerViewListener? = null

    override fun provideViewModel(): MovieListViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (::binding.isInitialized.not()) {
            binding = MovieListFragmentBinding.inflate(layoutInflater, container, false)
        }

        setUpListAdapter()
        observeData()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_movie_list, menu)

        val actionMenuItem = menu.findItem(R.id.menuSearch)
        searchView = actionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                actionMenuItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                viewModel.onSearchTextChange(s ?: "")
                return false
            }
        })
    }

    private fun observeData(){
        viewModel.movieListLiveData.observe(viewLifecycleOwner, Observer {
            movieListAdapter?.setData(it)
        })
    }

    private fun setUpListAdapter(){
        if (movieListAdapter == null){
            movieListAdapter = MovieListAdapter(this)

            setUpEndlessScrollListenerToRecycler()
        }

        binding.movieListRvList.adapter = movieListAdapter
    }

    private fun setUpEndlessScrollListenerToRecycler(){
        val layoutManager = binding.movieListRvList.layoutManager as LinearLayoutManager

        endlessScrollRecyclerViewListener = object : EndlessScrollRecyclerViewListener(layoutManager){
            override fun onLoadMore(page: Int, recycler: RecyclerView) {
                viewModel.onLoadMoreMovies(page)
            }
        }

        binding.movieListRvList.addOnScrollListener(endlessScrollRecyclerViewListener!!)
    }

    override fun onMovieClick(movie: Movie) {
        getNavController().navigate(
            R.id.movieDetailedFragment, MovieDetailedFragment.createArguments(
                movie.id
            )
        )
    }

}