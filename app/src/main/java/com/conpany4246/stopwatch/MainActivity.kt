package com.conpany4246.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0
    private var timerTask: Timer? = null
    private var isRunning = false
    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener {
            isRunning = !isRunning

            if(isRunning){
                start()
            }else{
                pause()
            }
        }

        labButton.setOnClickListener(){
            recodLapTime()
        }

        resetFab.setOnClickListener(){
            reset()
        }
    }

    private fun start(){
        floatingActionButton.setImageResource(R.drawable.ic_baseline_pause_24)

        timerTask = timer(period = 10){
            time++
            val sec = time / 100
            val milli = time % 100
            runOnUiThread {
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    private fun pause(){
        floatingActionButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timerTask?.cancel()
    }

    private fun reset(){
        timerTask?.cancel()

        time = 0
        isRunning = false
        floatingActionButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        secTextView.text = "0"
        milliTextView.text = "00"

        lapLayout.removeAllViews()
        lap = 1
    }

    private fun recodLapTime(){
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"

        lapLayout.addView(textView, 0)
        lap++
    }

}