package com.jovanovic.stefan.livedatademo

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActitivyViewModel: ViewModel() {

    private lateinit var timer: CountDownTimer

    /** timerValue - LiveData object. We are going to set this value from MainActivity */
    var timerValue = MutableLiveData<Long>()

    /** Finished - LiveData object. This value is being observer from MainActivity
     * Here I'm using a variable to get a private MutableLiveData value */
    private var _finished = MutableLiveData<Boolean>()
    val finished: LiveData<Boolean>
        get() = _finished

    /** Seconds - LiveData object. This value is being observer from MainActivity
     * Here I'm using a function to get a private MutableLiveData value */
    private val _seconds = MutableLiveData<Int>()
    fun seconds(): LiveData<Int>{
        return _seconds
    }

    /** Start Timer. This function is triggered from MainActivity. */
    fun startTimer(){
        timer = object : CountDownTimer(timerValue.value!!.toLong(), 1000){
            override fun onFinish() {
                _finished.value = true
            }

            override fun onTick(p0: Long) {
                val timeLeft = p0/1000
                _seconds.value = timeLeft.toInt()
            }
        }.start()
    }

    /** Stop Timer. This function is triggered from MainActivity. */
    fun stopTimer(){
        timer.cancel()
    }

}