package com.vladbakalo.imdbcient.ui.movie_detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.vladbakalo.imdbcient.base.BaseFragment
import com.vladbakalo.imdbcient.common.injectViewModel
import com.vladbakalo.imdbcient.databinding.MovieDetailedFragmentBinding
import com.vladbakalo.imdbcient.di.ViewModelFactory
import javax.inject.Inject

class MovieDetailedFragment : BaseFragment<MovieDetailedViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: MovieDetailedFragmentBinding

    override fun provideViewModel(): MovieDetailedViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setMovieId(requireArguments().getLong(KEY_MOVIE_ID))
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (::binding.isInitialized.not()) {
            binding = MovieDetailedFragmentBinding.inflate(inflater, container, false)
        }

        observeData()
        return binding.root
    }

    private fun observeData(){
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
            binding.movieDetailedTvTitleText.text = it.title
            binding.movieDetailedTvPopularityText.text = "Popularity : ${it.popularity}"
            binding.movieDetailedTvPopularityText.text = "Overview : ${it.overview}"

            Picasso.get()
                .load(it.getFormattedImageUrl())
                .centerInside()
                .fit()
                .into(binding.movieDetailedIvImage)
        })
    }

    companion object{

        private const val KEY_MOVIE_ID = "KEY_MOVIE_ID"

        fun createArguments(movieId: Long): Bundle{
            return bundleOf(KEY_MOVIE_ID to movieId)
        }
    }
}