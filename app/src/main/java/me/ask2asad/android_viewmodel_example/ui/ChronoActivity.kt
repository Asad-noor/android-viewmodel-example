package me.ask2asad.android_viewmodel_example.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_chrono.*
import me.ask2asad.android_viewmodel_example.R

class ChronoActivity : AppCompatActivity() {

    private var mChronoActivityViewModel: ChronoActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chrono)

        btnStartTimer.setOnClickListener {
            if(btnStartTimer.text.equals(resources.getString(R.string.start_timer))) {
                btnStartTimer.setText(resources.getString(R.string.stop_timer))

                if(mChronoActivityViewModel == null) {
                    mChronoActivityViewModel = ViewModelProviders.of(this).get(ChronoActivityViewModel::class.java)
                    subscribe()
                } else {
                    mChronoActivityViewModel?.restartTimer()
                }
            } else {
                //btnStartTimer.setText(resources.getString(R.string.start_timer))
                //mChronoActivityViewModel?.cancelTimer()
            }
        }
    }

    private fun subscribe() {
        mChronoActivityViewModel?.getElapsedTime()?.observe(this, Observer { value ->
            val newText: String = resources.getString(R.string.seconds, value)
            (findViewById<View>(R.id.timer_textview) as TextView).text = newText
            Log.d("tttt", "Updating timer")
        })
    }
}
