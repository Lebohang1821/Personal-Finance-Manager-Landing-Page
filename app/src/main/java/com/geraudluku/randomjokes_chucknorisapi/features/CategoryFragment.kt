package com.geraudluku.randomjokes_chucknorisapi.features

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geraudluku.randomjokes_chucknorisapi.R
import com.geraudluku.randomjokes_chucknorisapi.adapter.CategoriesRecyclerAdapter
import com.geraudluku.randomjokes_chucknorisapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_search.*

class CategoryFragment : Fragment(), CategoriesRecyclerAdapter.OnItemClickedListener {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init view model
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //init recyclerview
        val categoriesRecyclerAdapter = CategoriesRecyclerAdapter(arrayListOf(),requireContext(),this)
        category_recyclerview.adapter = categoriesRecyclerAdapter
        category_recyclerview.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        category_recyclerview.setHasFixedSize(true)

        //get categories from api
        mainViewModel.categories.observe(viewLifecycleOwner) {
            categoriesRecyclerAdapter.results = it
            categoriesRecyclerAdapter.notifyDataSetChanged()
        }

        //listen to incoming categories joke from api
        mainViewModel.randomCatJoke.observe(viewLifecycleOwner) {
            //show joke in popup with ok button
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Joke")
            builder.setMessage(it.value)
            builder.setPositiveButton("OK") { dialog, which ->
            }
            builder.show()
        }
    }

    override fun onItemClick(category: String) {
        //category clicked, fetch and display a random joke
        mainViewModel.setCategory(category)
    }

}