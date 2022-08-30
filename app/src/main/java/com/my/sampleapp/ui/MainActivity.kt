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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        val viewModel = makeApiCall()
        setUpBinding(viewModel)
    }

    fun setUpBinding(viewModel: MainActivityViewModel) {
        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.setVariable(BR.viewmodel, viewModel)
        activityMainBinding.executePendingBindings()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    fun makeApiCall(): MainActivityViewModel {

        val viewmodel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewmodel.getRecyclerLiveDataObserver().observe(this, Observer<RecyclerList> {
            if (it != null) {
                viewmodel.setAdapterData(it.items)
            } else {
                Log.e("TAG", "Main:Error while calling api")
            }
        })
        viewmodel.makeApiCall("india")

        return viewmodel
    }
}