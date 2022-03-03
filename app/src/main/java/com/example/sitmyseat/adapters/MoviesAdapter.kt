package com.example.sitmyseat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sitmyseat.R
import com.example.sitmyseat.models.Item
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MListHolder>() {

    inner class MListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MListHolder {
            return MListHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_movie,
                    parent,
                    false
                )
            )
    }

    override fun onBindViewHolder(holder: MListHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.itemView.apply {
            movie.poster_path?.let{
                Glide.with(this).load("https://image.tmdb.org/t/p/original${movie.poster_path}")
                    .into(ivArticleImage)
            }
            textView.text = movie.title
            textView2.text = movie.toString()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}