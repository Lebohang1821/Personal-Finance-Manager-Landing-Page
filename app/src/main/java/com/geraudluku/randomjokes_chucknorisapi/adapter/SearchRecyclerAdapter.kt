package com.geraudluku.randomjokes_chucknorisapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geraudluku.randomjokes_chucknorisapi.R
import com.geraudluku.randomjokes_chucknorisapi.model.RandomJoke
import kotlinx.android.synthetic.main.search_item.view.*

class SearchRecyclerAdapter(
    var results: ArrayList<RandomJoke>,
    val context: Context,
    private var clickListener: OnItemClickedListener
) : RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {

    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.joke

        fun bind(
            randomJoke: RandomJoke,
            context: Context,
            action: OnItemClickedListener,
            position: Int
        ) {
            textView.text = "$position). ${randomJoke.value}"

            textView.setOnClickListener {
                action.onItemClick(randomJoke)
            }
        }
    }


    interface OnItemClickedListener {
        fun onItemClick(randomJoke: RandomJoke)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(results[position], context, clickListener, position)
    }

    override fun getItemCount() = results.size

}