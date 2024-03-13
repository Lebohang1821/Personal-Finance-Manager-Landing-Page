package com.geraudluku.randomjokes_chucknorisapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geraudluku.randomjokes_chucknorisapi.R
import kotlinx.android.synthetic.main.search_item.view.*

class CategoriesRecyclerAdapter(
    var results: ArrayList<String>,
    val context: Context,
    private var clickListener: OnItemClickedListener
) : RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoriesViewHolder>() {

    class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.joke

        fun bind(
            category: String,
            context: Context,
            action: OnItemClickedListener,
            position: Int
        ) {
            textView.text = "$position). ${category}"

            textView.setOnClickListener {
                action.onItemClick(category)
            }
        }
    }


    interface OnItemClickedListener {
        fun onItemClick(category: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return CategoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(results[position], context, clickListener, position)
    }

    override fun getItemCount() = results.size

}