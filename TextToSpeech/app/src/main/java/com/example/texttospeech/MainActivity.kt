package com.example.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var speakbtn : Button
    private lateinit var userText : EditText
    private lateinit var seekBarPitch : SeekBar
    private lateinit var seekBarSpeed : SeekBar
    private lateinit var mTTs : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        speakbtn = findViewById(R.id.speakBtn)
        userText = findViewById(R.id.edTv)
        seekBarPitch = findViewById(R.id.seekBar_pitch)
        seekBarSpeed = findViewById(R.id.seekBar_speed)

        mTTs = TextToSpeech(this){status ->
            if(status == TextToSpeech.SUCCESS)   {
                val result = mTTs.setLanguage(Locale.ENGLISH)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    Log.e("TTs","Language is not supported")
                else
                    speakbtn.isEnabled = true
            }
            else
                Log.e("TTs","Initialization failed")
        }
        speakbtn.setOnClickListener {
            speak()
        }
    }
    private fun speak() {
        val text = userText.text.toString()
        var pitch = seekBarPitch.progress.toFloat()/2
        if (pitch < 0.1)    pitch = 0.1f
        var speed = seekBarSpeed.progress.toFloat()/2
        if (speed < 0.1)    speed = 0.1f
        mTTs.setPitch(pitch)
        mTTs.setSpeechRate(speed)
        mTTs.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onDestroy() {
        if (mTTs != null){
            mTTs.stop()
            mTTs.shutdown()
        }
        super.onDestroy()
    }
}