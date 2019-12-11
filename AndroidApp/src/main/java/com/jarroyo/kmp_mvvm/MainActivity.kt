package com.jarroyo.kmp_mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jarroyo.sharedcode.platformName
import com.jarroyo.sharedcode.viewModel.CounterViewModel
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
            mCounterViewModel.onCounterButtonPressed()
        }
    }

    private fun initViewModel() {
        mCounterViewModel = CounterViewModel()
        mCounterViewModel.counter.addObserver { counterString -> onDataReceived(counterString) }
    }

    private fun onDataReceived(counterString: String) {
        activity_main_tv.text = "Counter = $counterString"
    }
}
