package com.ayankumar.automatictapping

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.ayankumar.automatictapping.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val intent = Intent(this, TouchActivity::class.java)
                startActivity(intent)
            }
        }
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