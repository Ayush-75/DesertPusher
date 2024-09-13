package com.example.desertpusher

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class DessertTimer(lifecycle: Lifecycle):LifecycleEventObserver {
    var secondsCount = 0
//    private var handler = Handler()
//    private lateinit var runnable: Runnable
    private lateinit var job: Job

    init {
        lifecycle.addObserver(this)
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun dummyMethod(){
        Timber.i("I am a dummy method")
    }

//    fun startTimer(){
//        runnable = Runnable{
//            secondsCount++
//            Timber.i("Timer is at : $secondsCount")
//            handler.postDelayed(runnable,1000)
//        }
//
//        handler.postDelayed(runnable,1000)
//    }
//
//    fun stopTimer(){
//        handler.removeCallbacks(runnable)
//    }


    fun startTimer(){
        job = CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                secondsCount++
                Timber.i("Timer is at : $secondsCount")
                delay(1000)
            }
        }
    }

    fun stopTimer(){
        job.cancel()
        Timber.i("Timer is stopped")
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

        when(event){
            Lifecycle.Event.ON_START -> startTimer()
            Lifecycle.Event.ON_STOP -> stopTimer()
            else -> Timber.i("Something else")
        }
    }
}
