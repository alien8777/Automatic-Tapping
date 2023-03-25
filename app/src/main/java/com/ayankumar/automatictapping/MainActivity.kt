package com.ayankumar.automatictapping

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ayankumar.automatictapping.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private var mX: Float = 0.0F
    private var mY: Float = 0.0F
    private var mH: Int = -1
    private var mM: Int = -1
    private var mS: Int = -1
    private var mSS: Int = -1

    private lateinit var binding: ActivityMainBinding
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.main.setOnTouchListener { _, motionEvent ->

            mX = motionEvent.x
            mY = motionEvent.y

            println(" X axis value is $mX")
            println(" Y axis value is $mY")


            val c = Calendar.getInstance()

            mH = c.get(Calendar.HOUR_OF_DAY)
            mM = c.get(Calendar.MINUTE)
            mS = c.get(Calendar.SECOND)
            mSS = c.get(Calendar.MILLISECOND)

            updateData()
            false
        }

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val intent = Intent(this, TouchActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mX = event!!.x
        mY = event.y

        println(" X axis value is $mX")
        println(" Y axis value is $mY")


        val c = Calendar.getInstance()

        mH = c.get(Calendar.HOUR_OF_DAY)
        mM = c.get(Calendar.MINUTE)
        mS = c.get(Calendar.SECOND)
        mSS = c.get(Calendar.MILLISECOND)

        updateData()
        return super.onTouchEvent(event)
    }

    private fun updateData()
    {
        binding.xCod.setText(mX.toString())
        binding.yCod.setText(mY.toString())
        binding.hour.setText(mH.toString())
        binding.min.setText(mM.toString())
        binding.sec.setText(mS.toString())
        binding.mili.setText(mSS.toString())
    }

    @SuppressLint("CommitPrefEdits")
    override fun onResume() {
        super.onResume()
        binding.switch1.isChecked = false
        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val editor = sh.edit()
        val s1 = sh.getString("XCod", "")
        val s2 = sh.getString("YCod", "")
        val s3 = sh.getString("HCod", "")
        val s4 = sh.getString("MCod", "")
        val s5 = sh.getString("SCod", "")
        val s6 = sh.getString("SSCod", "")
        binding.xCod.setText(s1)
        binding.yCod.setText(s2)
        binding.hour.setText(s3)
        binding.min.setText(s4)
        binding.sec.setText(s5)
        binding.mili.setText(s6)
        editor.clear()
        editor.apply()
    }
}