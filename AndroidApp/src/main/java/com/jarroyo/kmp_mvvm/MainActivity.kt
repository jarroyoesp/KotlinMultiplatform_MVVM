package com.jarroyo.kmp_mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.platformName
import com.jarroyo.sharedcode.viewModel.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // View Model
    lateinit var mCounterViewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configView()
        initViewModel()
    }

    private fun configView() {
        val platformName = platformName()
        activity_main_tv.text = platformName

        activity_main_button.setOnClickListener {
            mCounterViewModel.getCounter()
        }
    }

    private fun initViewModel() {
        mCounterViewModel = CounterViewModel()
        observeCreateLogAbsenceViewModel()
    }

    /****************************************************************************
     * OBSERVER
     ***************************************************************************/
    private fun observeCreateLogAbsenceViewModel() {
        mCounterViewModel.mGetCounterLiveData.addObserver { getCurrentCounterState(it)}
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

    /**
     * ON SUCCESS
     */
    private fun onSuccess(value: Int) {

        activity_main_tv.text = "Counter = $value"
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
