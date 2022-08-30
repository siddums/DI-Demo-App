package com.my.sampleapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration.VERTICAL
import com.my.sampleapp.BR
import com.my.sampleapp.R
import com.my.sampleapp.databinding.ActivityMainBinding
import com.my.sampleapp.models.RecyclerList
import com.my.sampleapp.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = makeApiCall()
        setUpBinding(viewModel)
    }

    // setting activity layout to MainActivity using data binding
    fun setUpBinding(viewModel: MainActivityViewModel) {
        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.setVariable(BR.viewmodel, viewModel)
        activityMainBinding.executePendingBindings()

        // initializing recyclerview layout manager
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    // observing data changes after api call
    fun makeApiCall(): MainActivityViewModel {

        // initializing viewmodel
        val viewmodel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewmodel.getRecyclerLiveDataObserver().observe(this, Observer<RecyclerList> {
            if (it != null) {
                viewmodel.setAdapterData(it.items)
            } else {
                Log.e(TAG, "Main:Error while calling api")
            }
        })

        // calling api with parameter query india to fetch details of github repositories belongs to india
        viewmodel.makeApiCall("india")

        return viewmodel
    }
}