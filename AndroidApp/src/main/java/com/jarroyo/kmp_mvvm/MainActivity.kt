package com.jarroyo.kmp_mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jarroyo.kmp_mvvm.ui.main.adapter.GitHubRepoRVAdapter
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.platformName
import com.jarroyo.sharedcode.viewModel.*
import com.jarroyo.sharedcode.viewModel.github.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // View Model
    lateinit var mCounterViewModel: CounterViewModel
    lateinit var mGitHubViewModel: GitHubViewModel

    // RV Adapter
    private var mLayoutManager: LinearLayoutManager? = null
    private var mRvAdapter: GitHubRepoRVAdapter? = null

    private lateinit var countObserver : (counterState: GetCounterState) -> Unit
    private lateinit var getGithubListObserver : (state: GetGitHubRepoListState) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configView()
        initViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCounterViewModel.mGetCounterLiveData.removeObserver(countObserver)
        mGitHubViewModel.mGetGitHubRepoListLiveData.removeObserver(getGithubListObserver)
    }

    private fun configView() {
        val platformName = platformName()
        activity_main_tv.text = platformName


        activity_main_button.setOnClickListener {
            mCounterViewModel.getCounter()
            mGitHubViewModel.getGitHubRepoList("jarroyoesp")
        }
    }

    private fun initViewModel() {
        mCounterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)
        mGitHubViewModel = ViewModelProviders.of(this).get(GitHubViewModel::class.java)
        observeViewModel()
    }

    private fun configRecyclerView(treatmentList: List<GitHubRepo>) {
        if (activity_main_rv.adapter == null) {
            mLayoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false
            )
            activity_main_rv.layoutManager = mLayoutManager

            mRvAdapter = GitHubRepoRVAdapter(treatmentList)

            activity_main_rv.adapter = mRvAdapter

        } else {
            mRvAdapter?.setList(treatmentList)
            mRvAdapter?.notifyDataSetChanged()
        }
    }

    /****************************************************************************
     * OBSERVER
     ***************************************************************************/
    private fun observeViewModel() {
        countObserver = { getCurrentCounterState(mCounterViewModel.mGetCounterLiveData.value) }
        getGithubListObserver = { getGitHubListState(mGitHubViewModel.mGetGitHubRepoListLiveData.value) }

        mCounterViewModel.mGetCounterLiveData.addObserver(countObserver)
        mGitHubViewModel.mGetGitHubRepoListLiveData.addObserver(getGithubListObserver)
    }

    fun getCurrentCounterState(state: GetCounterState) {
        when (state) {
            is SuccessGetCounterState -> {
                hideLoading()
                val response =  state.response as Response.Success
                onSuccess(value = response.data)
            }

            is LoadingGetCounterState -> {
                showLoading()
            }

            is ErrorGetCounterState -> {
                hideLoading()
                val response =  state as Response.Error
                showError(response.message)
            }
        }
    }

    fun getGitHubListState(state: GetGitHubRepoListState) {
        when (state) {
            is SuccessGetGitHubRepoListState -> {
                hideLoading()
                val response =  state.response as Response.Success
                onSuccessGetGitHubList(response.data)
            }

            is LoadingGetGitHubRepoListState -> {
                showLoading()
            }

            is ErrorGetGitHubRepoListState -> {
                hideLoading()
                val response =  state.response as Response.Error
                showError(response.message)
            }
        }
    }

    /**
     * ON SUCCESS
     */
    private fun onSuccess(value: Int) {

        activity_main_tv.text = "Counter = $value"
    }

    private fun onSuccessGetGitHubList(list: List<GitHubRepo>) {
        configRecyclerView(list)
    }

    /**
     * SHOW LOADING
     */
    private fun showLoading() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
    }

    /**
     * HIDE LOADING
     */
    private fun hideLoading() {
    }

    /**
     * SHOW ERROR
     */
    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
