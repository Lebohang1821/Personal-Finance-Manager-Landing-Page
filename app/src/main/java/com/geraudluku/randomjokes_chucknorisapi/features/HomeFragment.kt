package com.geraudluku.randomjokes_chucknorisapi.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.geraudluku.randomjokes_chucknorisapi.R
import com.geraudluku.randomjokes_chucknorisapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init view model
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        navController = findNavController()

        //show random joke
        btn_random.setOnClickListener {
            //get random joke from api
            mainViewModel.randomJoke.observe(viewLifecycleOwner) {
                //show joke in popup with ok button
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Joke")
                builder.setMessage(it.value)
                builder.setPositiveButton("OK") { dialog, which ->
                }
                builder.show()
            }
        }

        //goto joke search fragment
        btn_search.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            navController.navigate(action)
        }

        //goto categories list
        btn_categories.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCategoryFragment()
            navController.navigate(action)
        }
    }

}