package com.ayankumar.automatictapping

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ayankumar.automatictapping.databinding.ActivityTouchBinding
import java.util.Calendar


@Suppress("DEPRECATION")
class TouchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTouchBinding
    private var mX: Float = 0.0F
    private var mY: Float = 0.0F
    private var mH: Int = -1
    private var mM: Int = -1
    private var mS: Int = -1
    private var mSS: Int = -1

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_touch)
        actionBar?.setHomeButtonEnabled(true)
        binding.clParent.setOnTouchListener { _, motionEvent ->

            mX = motionEvent.x
            mY = motionEvent.y

            println(" X axis value is $mX")
            println(" Y axis value is $mY")


            val c = Calendar.getInstance()

            mH = c.get(Calendar.HOUR_OF_DAY)
            mM = c.get(Calendar.MINUTE)
            mS = c.get(Calendar.SECOND)
            mSS = c.get(Calendar.MILLISECOND)
            onBackPressed()

            false
        }
    }

    override fun onBackPressed() {
        binding.clParent.isEnabled = false
        super.onBackPressed()
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("XCod", mX.toString())
        myEdit.putString("YCod", mY.toString())
        myEdit.putString("HCod", mH.toString())
        myEdit.putString("MCod", mM.toString())
        myEdit.putString("SCod", mS.toString())
        myEdit.putString("SSCod", mSS.toString())
        myEdit.apply()
    }
}