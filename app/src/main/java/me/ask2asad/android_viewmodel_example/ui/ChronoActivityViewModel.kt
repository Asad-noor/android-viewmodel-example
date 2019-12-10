package me.ask2asad.android_viewmodel_example.ui

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class ChronoActivityViewModel: ViewModel() {

    private val ONE_SECOND: Long = 1000

    private val mElapsedTime = MutableLiveData<Long>()

    private var mInitialTime: Long = 0
    private var timer: Timer? = null

    init {
       restartTimer()
    }

    fun getElapsedTime(): LiveData<Long> {
        return mElapsedTime
    }

    fun cancelTimer() {
        timer?.cancel()
    }

    fun restartTimer() {
        mInitialTime = SystemClock.elapsedRealtime()

        // Update the elapsed time every second.
        timer = Timer()
        timer!!.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                // setValue() cannot be called from a background thread so postValue is used.
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND, ONE_SECOND)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}