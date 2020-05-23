package com.jovanovic.stefan.livedatademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActitivyViewModel::class.java)

        /** Observing seconds() LiveData object from ViewModel */
        viewModel.seconds().observe(this, Observer {
            number_txt.text = it.toString()
        })
        /** Observing finished LiveData object from ViewModel */
        viewModel.finished.observe(this, Observer {
            if(it){
                Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show()
            }
        })

        start_btn.setOnClickListener {
            if(number_input.text.isEmpty() || number_input.text.length < 4){
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.timerValue.value = number_input.text.toString().toLong()
                viewModel.startTimer()
            }

        }
        stop_btn.setOnClickListener {
            number_txt.text = "0"
            viewModel.stopTimer()
        }

    }
}
