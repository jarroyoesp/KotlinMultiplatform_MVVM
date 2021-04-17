package com.jarroyo.kmp_mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jarroyo.kmp_mvvm.ui.main.adapter.GitHubRepoRVAdapter
import com.jarroyo.kmp_mvvm.ui.main.adapter.UserRVAdapter
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.db.User
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.platformName
import com.jarroyo.sharedcode.viewModel.*
import com.jarroyo.sharedcode.viewModel.github.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // View Model
    lateinit var mGitHubViewModel: GitHubViewModel

    // RV Adapter
    private var mLayoutManager: LinearLayoutManager? = null
    private var mLayoutManagerUser: LinearLayoutManager? = null
    private var mRvAdapter: GitHubRepoRVAdapter? = null
    private var mUserRvAdapter: UserRVAdapter? = null

    private lateinit var getGithubListObserver : (state: GetGitHubRepoListState) -> Unit
    private lateinit var getUserListObserver : (state: GetUserListState) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configView()
        initViewModel()


    }

    override fun onDestroy() {
        super.onDestroy()
        mGitHubViewModel.mGetGitHubRepoListLiveData.removeObserver(getGithubListObserver)
    }

    private fun configView() {
        activity_main_button.setOnClickListener {
            mGitHubViewModel.getGitHubRepoListMokko("jarroyoesp")
            mGitHubViewModel.getUserList()
        }
        activity_main_button_add.setOnClickListener {
            val name = activity_main_et_user.text.toString()
            if (!name.isNullOrEmpty()) {
                mGitHubViewModel.createUser(name)
            }
        }
    }

    private fun initViewModel() {
        mGitHubViewModel = ViewModelProviders.of(this).get(GitHubViewModel::class.java)
        observeViewModel()
        mGitHubViewModel.getUserList()
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


    private fun configRecyclerViewUser(userList: List<User>) {
        if (activity_main_rv_users.adapter == null) {
            mLayoutManagerUser = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false
            )
            activity_main_rv_users.layoutManager = mLayoutManagerUser

            mUserRvAdapter = UserRVAdapter(userList)

            activity_main_rv_users.adapter = mUserRvAdapter

        } else {
            mUserRvAdapter?.setList(userList)
            mUserRvAdapter?.notifyDataSetChanged()
        }
    }

    /****************************************************************************
     * OBSERVER
     ***************************************************************************/
    private fun observeViewModel() {
        getGithubListObserver = { getGitHubListState(mGitHubViewModel.mGetGitHubRepoListLiveData.value) }
        getUserListObserver = { getUserListObserver(mGitHubViewModel.mGetUserListLiveData.value) }

        mGitHubViewModel.mGetGitHubRepoListLiveData.addObserver(getGithubListObserver)
        mGitHubViewModel.mGetUserListLiveData.addObserver(getUserListObserver)
    }

    fun getUserListObserver(state: GetUserListState) {
        when (state) {
            is SuccessGetUserListState -> {
                hideLoading()
                val response =  state.response as Response.Success
                onSuccessUserList(response.data)
            }

            is LoadingGetUserListState -> {
                showLoading()
            }

            is ErrorGetUserListState -> {
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
    private fun onSuccessUserList(userList: List<User>) {
        configRecyclerViewUser(userList)
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
