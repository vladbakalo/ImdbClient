package com.vladbakalo.imdbcient.ui.movie_list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vladbakalo.imdbcient.common.getLayoutInflater
import com.vladbakalo.imdbcient.data.model.Movie
import com.vladbakalo.imdbcient.databinding.ItemMovieBinding

class MovieListAdapter(private val listener: MovieClickListener): RecyclerView.Adapter<MovieListAdapter.VHItem>() {

    private var dataList = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHItem {
        val layoutInflater = parent.getLayoutInflater()
        val viewHolder = ItemMovieBinding.inflate(layoutInflater, parent, false)

        return VHItem(viewHolder)
    }

    override fun onBindViewHolder(holder: VHItem, position: Int) {
        val item = dataList[position]

        holder.binding.itemMovieTvTitleText.text = item.title
        holder.binding.itemMovieTvPopularityText.text = "Popularity : ${item.popularity}"

        Picasso.get()
            .load(item.getFormattedImageUrl())
            .centerCrop()
            .fit()
            .into(holder.binding.itemMovieIvImage)

        holder.binding.root.setOnClickListener {
            listener.onMovieClick(item)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(data: List<Movie>){
        dataList.clear()
        dataList.addAll(data)

        notifyDataSetChanged()
    }

    inner class VHItem(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){

    }

    interface MovieClickListener{
        fun onMovieClick(movie: Movie)
    }
}