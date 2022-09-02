package com.my.sampleapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import com.my.sampleapp.R
import com.my.sampleapp.databinding.ActivityGitRepoListLayoutBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitRepoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGitRepoListLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_git_repo_list_layout);
        //setContentView(R.layout.activity_main2)

    }

    override fun onSupportNavigateUp() =
        findNavController(this, R.id.nav_host_fragment).navigateUp()

}